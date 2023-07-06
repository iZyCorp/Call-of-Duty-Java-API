package io.github.izycorp.codapi.query;

import io.github.izycorp.codapi.events.Listener;
import io.github.izycorp.codapi.events.components.ErrorInRequestEvent;
import io.github.izycorp.codapi.events.components.PostRequestEvent;
import io.github.izycorp.codapi.events.components.PreRequestEvent;
import io.github.izycorp.codapi.exceptions.MoonViolationException;
import okhttp3.*;

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
    private String sendRequest(String url, Headers.Builder providedHeader) throws MoonViolationException {
        boolean hasListener = Objects.nonNull(this.attachedListener);
        // building url
        final String queryUrl = urlPrefix + url;
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
        try (Response response = client.newCall(request).execute()) {
            // Send PostRequestEvent
            if (hasListener) attachedListener.callEvent(new PostRequestEvent(response));
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception exception) {
            // Send ErrorInRequestEvent
            if (hasListener) attachedListener.callEvent(new ErrorInRequestEvent(exception));
            else throw new MoonViolationException(exception.getMessage());
        }
        return null;
    }

    /**
     * This method is used to send a request to Call Of Duty servers and return the response body.
     *
     * @param url : url to send the request to
     * @return : Response object (body)
     */
    public String sendRequest(String url) throws MoonViolationException {
        return sendRequest(url, baseHeader);
    }

    /**
     * This method is similar to {@link #sendRequest(String, Headers.Builder authHeader)} but it throws a MoonViolationException in case you are not logged in.
     * This is used for public or protected routes.
     *
     * @param url        : url to send request to
     * @param authHeader : custom Header, throw a MoonViolationException if not valid
     * @return String : response body
     */
    public String sendRequestWithAuthentication(String url, Headers.Builder authHeader) throws MoonViolationException {
        return sendRequest(url, authHeader);
    }

    /**
     * <p>
     * This method is used to authenticate a user and return the header to use for authenticated requests.
     * see {@link #sendRequestWithAuthentication(String, Headers.Builder authHeader)}
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
     *     <li>Right click and inspect the page, go to 'Storage' -> 'Cookies' and search for <strong>ACT_SSO_COOKIE</strong></li>
     *
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
}
