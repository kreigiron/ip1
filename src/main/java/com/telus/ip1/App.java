package com.telus.ip1;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.IOException;
import java.io.StringReader;

/**
 * Esqueleto base para proyecto
 *
 */
public class App
{
    public static void main( String[] args )
    {

        final String bearerToken = getBearerToken();
        final String userName = "ronald_mackay";
        final int count = 10;
        final String apiUrl = "https://api.twitter.com/1.1/statuses/user_timeline.json";
        final String response = getTwittsFromApi(apiUrl, bearerToken, userName, count);
        final String textToSearch = "a";

         // Leemos respuesta de twitter y la procesamos para crear objetos json en base a su estructura, completar
        JSONArray jsonArray = new JSONArray(response);
        // completar lo siguiente
        printResults(textToSearch, jsonArray);  // completar el metodo de la impresion
    }

    private static String getBearerToken() {
        return "AAAAAAAAAAAAAAAAAAAAACVe6AAAAAAAdEpI%2Fo3cbeOonMufkrDcVva8kM8%3DEY9DOQEGIOiizoazuG1GhjtfljspVgUN0auMP5UUZWFl9ybXUo";
    }


    private static void printResults(String textToSearch, JSONArray array) {
        for(int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String text = (String) jsonObject.get("text");
            System.out.println("Texto en indice " + i + ":");
            System.out.println(text);
            System.out.println("------");
        }
    }

    private static String getTwittsFromApi(final String apiUrl, final String bearerToken, final String userName, final int count) {
        final String apiUrlWithParams = apiUrl + "?screen_name="+userName+"&count=" + count;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrlWithParams);
        httpGet.setHeader("Authorization", "Bearer " + bearerToken);

        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)){
            HttpEntity entity1 = response1.getEntity();
            String body = EntityUtils.toString(entity1);

            System.out.println( body );
            return body;
        } catch( IOException ex) {
            System.out.println( ex);
            return "";
        }
    }
}
