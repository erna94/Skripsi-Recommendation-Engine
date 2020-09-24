package com.ernchatbot.service;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

class HttpUtil {

    public static HttpClient getHttpClient() {
        HttpClient client = new DefaultHttpClient();
        //Important for android version 9 pie/
        client.getConnectionManager().getSchemeRegistry().register(
                new Scheme("https", SSLSocketFactory.getSocketFactory(), 443)
        );

        return client;
    }
}
