package com.ajitesh.android.joker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ajitesh.myapplication.backend.myApi.MyApi;
import com.example.ajitesh.myapplication.backend.myApi.model.Joke;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ajitesh.android.jokedisplay.JokeActivity;


import java.io.IOException;

/**
 * Created by ajitesh on 5/6/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    ProgressDialog progress;

    public EndpointsAsyncTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress= new ProgressDialog(context);
        progress.setMessage("Preparing a delicious Joke");
        progress.show();
    }


    @Override
    protected String doInBackground(Void ... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://build-it-bigger-1333.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            Joke joke=myApiService.sayJoke().execute();
            if(joke==null){
                return null;
            }
            else{
                return joke.getJokeText();
            }
        } catch (IOException e) {
            Log.d("Exception",e.toString());
            return null;
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        progress.hide();
    }


    @Override
    protected void onPostExecute(String joke) {
        //Toast.makeText(context, joke.getJokeText(), Toast.LENGTH_LONG).show();
        progress.hide();
        if(joke!=null){
            Intent intent=new Intent(context, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY,joke);
            context.startActivity(intent);
        }
        else{
            Toast.makeText(context,"Unable to Connect..!",Toast.LENGTH_SHORT).show();
        }
    }
}