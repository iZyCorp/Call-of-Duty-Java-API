package io.github.izycorp.codapi.query;

import io.github.izycorp.codapi.events.Listener;
import io.github.izycorp.codapi.events.components.ErrorInRequestEvent;
import io.github.izycorp.codapi.events.components.PostRequestEvent;
import io.github.izycorp.codapi.events.components.PreRequestEvent;
import io.github.izycorp.codapi.exceptions.CodRequestException;
import okhttp3.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author iZy
 * @version 1.0
 * @since 1.0
 *
 * <h2>RequestManager</h2>
 * This class is used to manage requests and responses from the API
 */
public class RequestManager {

    /**
     * The base URL of the API
     */
    public static final String BASE_URL = "https://www.callofduty.com/api/papi-client/";
    public static final String PROFILE_BASE_URL = "https://profile.callofduty.com/";

    /**
     * The User-Agent used to send requests
     */
    private final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";

    /**
     * The base cookie content without authentication
     */
    private final String baseCookie = "new_SiteId=cod;ACT_SSO_LOCALE=en_US;country=US;";

    /**
     * A fake XSRF-TOKEN, this is basically used against CSRF attacks. A proper XCSRF token can be fetched from a request,
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

    private final Headers.Builder authHeader = baseHeader;

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
    public RequestManager(final OkHttpClient client) {
        this.client = client;
    }

    /**
     * Constructor with only Listener as a parameter
     *
     * @param listener : A class that extends Listener
     */
    public RequestManager(final Listener listener) {
        this();
        this.attachedListener = listener;
    }

    /**
     * Constructor with a custom OkHttpClient
     *
     * @param listener : A class that extends Listener
     * @param client   : A valid OkHttpClient
     */
    public RequestManager(final Listener listener, final OkHttpClient client) {
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
    private String sendRequest(final String baseUrl, final String url, final Headers.Builder providedHeader) throws CodRequestException {
        final boolean hasListener = Objects.nonNull(this.attachedListener);
        // building url
        final String queryUrl = baseUrl + url;
        // Send PreRequestEvent
        if (hasListener)
            attachedListener.callEvent(new PreRequestEvent(queryUrl, (providedHeader != null ? providedHeader.build() : null)));
        // Making Http request
        assert providedHeader != null;
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .method("GET", null)
                .headers(providedHeader.build())
                .url(queryUrl)
                .build();
        try (final Response response = client.newCall(request).execute()) {
            // Send PostRequestEvent
            if (hasListener) attachedListener.callEvent(new PostRequestEvent(response));
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception exception) {
            // Send ErrorInRequestEvent
            if (hasListener) attachedListener.callEvent(new ErrorInRequestEvent(exception));
            else throw new CodRequestException(exception.getMessage());
        }
        return null;
    }

    private String sendRequest(final String url, final Headers.Builder providedHeader) throws CodRequestException {
        return this.sendRequest(BASE_URL, url, providedHeader);
    }

    /**
     * This method is used to send a request to Call Of Duty servers and return the response body.
     *
     * @param url : url to send the request to
     * @return : Response object (body)
     */
    public String sendRequest(final String url) throws CodRequestException {
        return sendRequest(url, baseHeader);
    }

    /**
     * This method is similar to {@link #sendRequest(String, Headers.Builder authHeader)} but it throws a CodRequestException in case you are not logged in.
     * This is used for public or protected routes.
     *
     * @param url        : url to send request to
     * @return String: response body
     */
    public String sendRequestWithAuthentication(final String url) throws CodRequestException {
        return sendRequest(url, authHeader);
    }

    public String sendRequestWithAuthentication(final String baseUrl, final String url) throws CodRequestException {
        return sendRequest(baseUrl, url, authHeader);
    }

    /**
     * <p>
     * This method is used to authenticate a user and return the header to use for authenticated requests.
     * see {@link #sendRequestWithAuthentication(String)}
     * </p>
     *
     * <br>
     *
     * <b>IMPORTANT NOTE:</b>
     * Since Activision implemented a captcha system to their authentication page, and since there is no other 'proper'
     * way to authenticate using an API route, you'll have to fetch your SSO token by yourself.
     * SSO tokens expire every 14 days.
     * <br>
     * To do so, you can follow those steps:
     * <ol>
     *     <li>Go to <a href="https://profile.callofduty.com/cod/login">https://profile.callofduty.com/cod/login</a></li>
     *     <li>Authenticate using your credentials</li>
     *     <li>Right-click and inspect the page, go to 'Storage' then 'Cookies' and search for <strong>ACT_SSO_COOKIE</strong></li>
     * </ol>
     * @param ssoToken : sso token to authenticate (Follow below steps to get it)
     * @return A valid header for authenticated requests
     */
    public Headers.Builder authenticate(final String ssoToken) {
        if (ssoToken == null || ssoToken.trim().isEmpty()) {
            return baseHeader;
        }

        this.authHeader
                .add("XSRF-TOKEN", fakeXCSRF)
                .add("CSRF-TOKEN", fakeXCSRF)
                .add("ACT_SSO_COOKIE", ssoToken)
                .add("ACT_SSO_REMEMBER_ME", ssoToken)
                .add("cookie", baseCookie + "ACT_SSO_COOKIE=" + ssoToken + ";XSRF-TOKEN=" + fakeXCSRF + ";API_CSRF_TOKEN=" + fakeXCSRF + ";ACT_SSO_EVENT=\"LOGIN_SUCCESS:1644346543228\";ACT_SSO_COOKIE_EXPIRY=1645556143194;comid=cod;ssoDevId=63025d09c69f47dfa2b8d5520b5b73e4;tfa_enrollment_seen=true;gtm.custom.bot.flag=human;");

        return this.authHeader;

    }
}
