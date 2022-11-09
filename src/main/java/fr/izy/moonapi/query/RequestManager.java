package fr.izy.moonapi.query;

import fr.izy.moonapi.events.Listener;
import fr.izy.moonapi.events.components.ErrorInRequestEvent;
import fr.izy.moonapi.events.components.PostRequestEvent;
import fr.izy.moonapi.events.components.PreRequestEvent;
import fr.izy.moonapi.exceptions.MoonViolationException;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;

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
     * A fake XSRF-TOKEN (Not sure about what it should be)
     */
    private final String fakeXSRF = "3844e7b2-ac07-4c97-8c72-0fa9f43fdd26";

    private Listener attachedListener;

    /**
     * The baseHeader built with Headers.Builder class see {@link Headers.Builder}
     */
    private final Headers.Builder baseHeader = new Headers.Builder()
            .add("X-CSRF-TOKEN", fakeXSRF)
            .add("X-CSRF-TOKEN", fakeXSRF)
            .add("User-Agent", userAgent)
            .add("Cookie", baseCookie);

    /**
     * An OkHttpClient object used to send requests
     */
    private final OkHttpClient client;

    /**
     * Default constructor instantiate {@link this#client} with a basic configuration of OkHttpClient
     * If you want to change the configuration of the client, use {@link this#RequestManager(OkHttpClient)}
     */
    public RequestManager() {
        this(null, new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .build());
    }

    /**
     * Constructor with a custom OkHttpClient
     * @param client : A valid OkHttpClient
     */
    public RequestManager(OkHttpClient client) {
        this.client = client;
    }

    /**
     * Constructor with only Listener as parameter
     * @param listener : A class that extends Listener
     */
    public RequestManager(Listener listener) {
        this();
        this.attachedListener = listener;
    }

    /**
     * Constructor with a custom OkHttpClient
     * @param client : A valid OkHttpClient
     */
    public RequestManager(Listener listener, OkHttpClient client) {
        this(client);
        this.attachedListener = listener;
    }

    /**
     * This method is used to send a request to Call Of Duty servers and return the response body.
     * @param url : url to send the request to
     * @param authHeader : headers to add to the request (it is the header built by {@link #authenticate(String)}
     * @return : Response object (body)
     */
    private String sendRequest(String url, Headers.Builder authHeader) {
        boolean hasListener = Objects.nonNull(this.attachedListener);
        // building url
        String queryUrl = urlPrefix + url;
        // Send PreRequestEvent
        if(hasListener) attachedListener.callEvent(new PreRequestEvent(queryUrl, (authHeader != null ? authHeader.build() : null)));
        // Making Http request
        okhttp3.Request request = new okhttp3.Request.Builder()
                .get()
                .headers(authHeader == null ? baseHeader.build() : authHeader.build())
                .url(queryUrl)
                .build();
        try (Response response = client.newCall(request).execute()) {
            // Send PostRequestEvent
            if(hasListener) attachedListener.callEvent(new PostRequestEvent(response));
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception exception) {
            // Send ErrorInRequestEvent
            if(hasListener) attachedListener.callEvent(new ErrorInRequestEvent(exception));
            else exception.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to send a request to Call Of Duty servers and return the response body.
     * @param url : url to send the request to
     * @return : Response object (body)
     */
    public String sendRequest(String url) {
        return sendRequest(url, null);
    }

    /**
     * This method is similar to {@link #sendRequest(String, Headers.Builder authHeader)} but it throws a MoonViolationException in case you are not logged in.
     * This is used for public or protected routes.
     * @param url : url to send request to
     * @param authHeader : custom Header, throw a MoonViolationException if not valid
     * @throws MoonViolationException : if user is not logged in
     */
    public String sendRequestWithAuthentication(String url, Headers.Builder authHeader) throws MoonViolationException {
        if(authHeader == null) throw new MoonViolationException("You are not logged in.");
        return sendRequest(url, authHeader);
    }

    /**
     * This method is used to authenticate a user and return the header to use for authenticated requests.
     * see {@link #sendRequestWithAuthentication(String, Headers.Builder authHeader)}
     * @param ssoToken : sso token to authenticate (can be everything)
     * @return A valid header for authenticated requests
     */
    public Headers.Builder authenticate(String ssoToken) {
        if(ssoToken == null || ssoToken.trim().length() == 0) return null;

        Headers.Builder headersBuilder = new Headers.Builder();

        headersBuilder.add("X-XSRF-TOKEN", fakeXSRF);
        headersBuilder.add("X-CSRF-TOKEN", fakeXSRF);
        headersBuilder.add("Atvi-Auth", ssoToken);
        headersBuilder.add("ACT_SSO_COOKIE", ssoToken);
        headersBuilder.add("atkn", ssoToken);
        headersBuilder.add("cookie", baseCookie + "ACT_SSO_COOKIE="+ ssoToken + ";XSRF-TOKEN=" + fakeXSRF +  ";API_CSRF_TOKEN=" + fakeXSRF + ";ACT_SSO_EVENT=\"LOGIN_SUCCESS:1644346543228\";ACT_SSO_COOKIE_EXPIRY=1645556143194;comid=cod;ssoDevId=63025d09c69f47dfa2b8d5520b5b73e4;tfa_enrollment_seen=true;gtm.custom.bot.flag=human;");
        return headersBuilder;
    }
}
