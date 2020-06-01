package com.atos.upsvar.modules.rx.model.ws;

import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by a596190 on 18. 1. 2018.
 */

public class ApiConnection {
    public static final String TAG = "ApiConnection";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private URL url;
    private String response;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    public String connectToApi(String json) {
        OkHttpClient okHttpClient = null;
        okHttpClient = this.createClient();

        RequestBody body = RequestBody.create(JSON, json);
        final Request request = new Request.Builder()
                .url(this.url)
                .post(body)
                .build();

        try {
            Response respo = okHttpClient.newCall(request).execute();
            this.response = respo.body().string();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return response;
    }

    private OkHttpClient createClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);

            OkHttpClient okHttpClient = builder
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
