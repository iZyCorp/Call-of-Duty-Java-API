package io.github.izycorp.codapi.query;

import io.github.izycorp.codapi.events.Listener;
import io.github.izycorp.codapi.events.components.ErrorInRequestEvent;
import io.github.izycorp.codapi.events.components.PostRequestEvent;
import io.github.izycorp.codapi.events.components.PreRequestEvent;
import io.github.izycorp.codapi.exceptions.CodServerException;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h1>RequestManager</h1>
 * This class is used to manage requests and responses from the API
 */
public class RequestManager {

    /**
     * The base URL of the API
     */
    private final String urlPrefix = "https://my.callofduty.com/api/papi-client/";

    /**
     * The base URL of the telescopic API
     */
    private final String telescopeUrlPrefix = "https://telescope.callofduty.com/api/ts-api/";

    private final String telescopeLoginUri = "https://wzm-ios-loginservice.prod.demonware.net/v1/login/uno/?titleID=7100&client=shg-cod-jup-bnet";

    /**
     * The User-Agent used to send requests
     */
    private final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";

    /**
     * The base cookie content without authentication
     */
    private final String baseCookie = "new_SiteId=cod;ACT_SSO_LOCALE=en_US;country=US;";

    /**
     * A fake XSRF-TOKEN, this is basically used against CSRF attacks. A proper XCSRF token can be fetched from a request
     * but this step is not necessary since we can use a fake one.
     */
    private final String fakeXCSRF = "3844e7b2-ac07-4c97-8c72-0fa9f43fdd26";

    private Listener attachedListener;

    /**
     * The baseHeader built with Headers.Builder class see {@link Headers.Builder}
     */
    private final Headers.Builder baseHeader = new Headers.Builder()
            .add("X-CSRF-TOKEN", fakeXCSRF)
            .add("X-CSRF-TOKEN", fakeXCSRF)
            .add("User-Agent", userAgent)
            .add("Cookie", baseCookie);

    /**
     * The base telescope header built with Headers.Builder class see {@link Headers.Builder}
     */
    private final Headers.Builder baseTelescopeHeaders = new Headers.Builder()
            .add("Accept", "application/json, text/plain, */*")
            .add("Accept-Language", "en-GB,en;q=0.9,en-US;q=0.8,fr;q=0.7,nl;q=0.6,et;q=0.5")
            .add("Cache-Control", "no-cache")
            .add("Pragma", "no-cache")
            .add("Sec-CH-UA", "\"Chromium\";v=\"118\", \"Microsoft Edge\";v=\"118\", \"Not=A?Brand\";v=\"99\"")
            .add("Sec-CH-UA-Mobile", "?0")
            .add("Sec-CH-UA-Platform", "\"Windows\"")
            .add("Sec-Fetch-Dest", "empty")
            .add("Sec-Fetch-Mode", "cors")
            .add("Sec-Fetch-Site", "same-site");

    /**
     * An OkHttpClient object used to send requests
     */
    private final OkHttpClient client;

    /**
     * Default constructor instantiate {@link RequestManager#client} with a basic configuration of OkHttpClient
     * If you want to change the configuration of the client, use {@link RequestManager#RequestManager(OkHttpClient)}
     */
    public RequestManager() {
        this(null, new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .build());
    }

    /**
     * Constructor with a custom OkHttpClient
     *
     * @param client : A valid OkHttpClient
     */
    public RequestManager(OkHttpClient client) {
        this.client = client;
    }

    /**
     * Constructor with only Listener as parameter
     *
     * @param listener : A class that extends Listener
     */
    public RequestManager(Listener listener) {
        this();
        this.attachedListener = listener;
    }

    /**
     * Constructor with a custom OkHttpClient
     *
     * @param listener : A class that extends Listener
     * @param client   : A valid OkHttpClient
     */
    public RequestManager(Listener listener, OkHttpClient client) {
        this(client);
        this.attachedListener = listener;
    }

    /**
     * This method is used to send a request to Call Of Duty servers and return the response body.
     *
     * @param url            : url to send the request to
     * @param providedHeader : headers to add to the request (it is the header built by {@link #authenticate(String)}
     * @return : Raw response body
     */
    private String sendRequest(String url, Headers.Builder providedHeader, String method) throws CodServerException {
        boolean hasListener = Objects.nonNull(this.attachedListener);

        // Send PreRequestEvent
        if (hasListener)
            attachedListener.callEvent(new PreRequestEvent(url, (providedHeader != null ? providedHeader.build() : null)));

        // Making Http request
        assert providedHeader != null;
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .method(method, null)
                .headers(providedHeader.build())
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Send PostRequestEvent
            if (hasListener) attachedListener.callEvent(new PostRequestEvent(response));
            return Objects.requireNonNull(response.body()).string();

        } catch (Exception exception) {
            // Send ErrorInRequestEvent
            if (hasListener) attachedListener.callEvent(new ErrorInRequestEvent(exception));
            else throw new CodServerException(exception.getMessage());
        }
        return null;
    }

    /**
     * This method is used to send a request to Call Of Duty servers and return the response body.
     *
     * @param url : url to send the request to
     * @return : Response object (body)
     */
    public String sendRequest(final String url) throws CodServerException {
        return sendRequest(urlPrefix + url, baseHeader, "GET");
    }

    /**
     * This method is similar to {@link #sendRequest(String url, Headers.Builder authHeader, String method)} but it throws a MoonViolationException in case you are not logged in.
     * This is used for public or protected routes.  This should be use for older titles only! (everything before mw2)
     *
     * @param url        : url to send request to
     * @param authHeader : custom Header, throw a MoonViolationException if not valid
     * @return String: response body
     */
    public String sendRequestWithAuthentication(final String url, final Headers.Builder authHeader, final String method) throws CodServerException {
        return sendRequest(urlPrefix + url, authHeader, method);
    }

    /**
     * Same purpose as  {@link #sendRequestWithAuthentication(String, Headers.Builder, String)} but for newer titles.
     * (everything after mw2)
     */
    public String sendTelescopeRequest(final String url) throws CodServerException {
        return sendRequest(telescopeUrlPrefix + url, baseTelescopeHeaders, "GET");
    }

    /**
     * <p>
     * This method is used to authenticate a user and return the header to use for authenticated requests.
     * see {@link #sendRequestWithAuthentication(String url, Headers.Builder authHeader, String method)}
     * </p>
     *
     * <br>
     *
     * <b>IMPORTANT NOTE:</b>
     * Since Activision implemented a captcha system to their authentication page and since there is no other 'proper'
     * way to authenticate using an API route, you'll have to fetch your SSO token by yourself.
     * <br>
     * To do so, you can follow those steps:
     * <ol>
     *     <li>Go to <a href="https://profile.callofduty.com/cod/login">https://profile.callofduty.com/cod/login</a></li>
     *     <li>Authenticate using your credentials</li>
     *     <li>Right click and inspect the page, go to 'Storage' then 'Cookies' and search for <strong>ACT_SSO_COOKIE</strong></li>
     * </ol>
     * @param ssoToken : sso token to authenticate (Follow below steps to get it)
     * @return A valid header for authenticated requests
     */
    public Headers.Builder authenticate(String ssoToken) {
        if (ssoToken == null || ssoToken.trim().isEmpty()) {
            return baseHeader;
        }

        return baseHeader
                .add("XSRF-TOKEN", fakeXCSRF)
                .add("CSRF-TOKEN", fakeXCSRF)
                .add("ACT_SSO_COOKIE", ssoToken)
                .add("ACT_SSO_REMEMBER_ME", ssoToken)
                .add("cookie", baseCookie + "ACT_SSO_COOKIE=" + ssoToken + ";XSRF-TOKEN=" + fakeXCSRF + ";API_CSRF_TOKEN=" + fakeXCSRF + ";ACT_SSO_EVENT=\"LOGIN_SUCCESS:1644346543228\";ACT_SSO_COOKIE_EXPIRY=1645556143194;comid=cod;ssoDevId=63025d09c69f47dfa2b8d5520b5b73e4;tfa_enrollment_seen=true;gtm.custom.bot.flag=human;");

    }

    /**
     * <p>
     *     This  is  the new method  used to  authenticate for newer cod titles.
     * </p
     */
    public void telescopeAuthenticate(final String username, final String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be null or empty");
        }

        boolean hasListener = Objects.nonNull(this.attachedListener);

        final JSONObject auth = new JSONObject();
        auth.put("email", username);
        auth.put("password", password);

        final JSONObject body = new JSONObject();
        body.put("platform", "ios");
        body.put("hardwareType", "ios");
        body.put("auth", auth);
        body.put("version", 1492);

        final RequestBody requestBody = RequestBody.create(body.toString(), MediaType.parse("application/json; charset=utf-8"));

        final Request request = new Request.Builder()
                .url(telescopeLoginUri)
                .headers(baseTelescopeHeaders.build())
                .post(requestBody)
                .build();

        if (hasListener) {
            attachedListener.callEvent(new PreRequestEvent(telescopeLoginUri, baseTelescopeHeaders.build()));
        }

        try (final Response response = client.newCall(request).execute()) {
            final int statusCode = response.code();

            if (statusCode == 200) {
                final String responseBody = Objects.requireNonNull(response.body()).string();
                final JSONObject jsonResponse = new JSONObject(responseBody);
                final String unoToken = jsonResponse.getJSONObject("umbrella").getString("accessToken");

                baseTelescopeHeaders.add("Authorization", "Bearer " + unoToken);

            } else if (statusCode == 403) {

                final String errorBody = Objects.requireNonNull(response.body()).string();
                final JSONObject errorResponse = new JSONObject(errorBody);
                final String errorMsg = errorResponse.getJSONObject("error").getString("msg");

                if (hasListener) attachedListener.callEvent(new ErrorInRequestEvent(new CodServerException("Error Logging In: " + errorMsg)));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
