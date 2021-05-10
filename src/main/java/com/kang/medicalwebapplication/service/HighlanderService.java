package com.kang.medicalwebapplication.service;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class HighlanderService {
    private String authority;
    private String clientId;
    private String secret;
    private String scope;


    private IAuthenticationResult getAccessToeknByClientCredentialGrant() throws Exception {

        ConfidentialClientApplication app = ConfidentialClientApplication.builder(clientId, ClientCredentialFactory.createFromSecret(secret)).authority(authority).build();
        ClientCredentialParameters clientCredentialParameters = ClientCredentialParameters.builder(Collections.singleton(scope)).build();
        CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParameters);

        return future.get();
    }

    private String getUsersListFromGraph(String accessToken) throws IOException {
        URL url = new URL("");
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer" +accessToken);
        connection.setRequestProperty("Accept", "application/json");

        int httpResponseCode = connection.getResponseCode();

        if(httpResponseCode == HTTPResponse.SC_OK){
            StringBuilder response;
            try(BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

                    String inputLine;
                    response = new StringBuilder();
                    while (( inputLine = in.readLine())!=null) {
                        response.append(inputLine);
                    }
            }
            return response.toString();
        } else{
            return String.format("Connection returned HTTP code: %s with message: %s", httpResponseCode,connection.getResponseMessage());
        }
    }
}
