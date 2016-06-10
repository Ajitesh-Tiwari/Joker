package com.ajitesh.android.jokesource;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;


public class JokeSource {
    String url="http://ajitesh-tiwari.github.io/Extras/Jokes.json";

    public Joke getJoke(){
        //return "This is a joke from a Java Library";
        Joke joke=null;
        try {
            JSONArray jsonArray=new JSONArray(getText());
            Random random=new Random();
            JSONObject jokeJSON=jsonArray.getJSONObject(random.nextInt(999));
            joke=new Joke(jokeJSON.getString("joke"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joke;
    }
    public String getText() throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

}
