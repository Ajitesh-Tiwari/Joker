package com.ajitesh.android.jokesource;

import java.io.Serializable;

/**
 * Created by ajitesh on 5/6/16.
 */
public class Joke implements Serializable {
    String jokeText;

    public Joke(String jokeText){
        this.jokeText=jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public String getJokeText() {
        return jokeText;
    }
}
