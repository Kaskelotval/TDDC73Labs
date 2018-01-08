package com.example.jensk.lab3;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
class JSONTask extends AsyncTask<String, Void, String> {

    private WordSearcher wordSearcher;

    //AsyncTask extension to handle UI threads
    //Both doInBack and onPostExecute are called automaticlly.


    public JSONTask(WordSearcher wordSearcher)
    {
        this.wordSearcher = wordSearcher;
    }

    //Do this in background. These run in the background so as not to slow down the application
    protected String doInBackground(String... params) {

        String result = "";
        HttpURLConnection connection = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String input;
            while((input=in.readLine()) != null){
                result += input;
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result; //Send this to onPostExecute
    }

    //When background computations are done, run onPostExecute.
    @Override
    protected void onPostExecute(String result) {
        try{
            JSONObject json = new JSONObject(result);
            int id = json.getInt("id");
            JSONArray names = json.getJSONArray("result");

            //Only update if the ID is the latest
                if(id>=wordSearcher.getCurrId())
                {
                    wordSearcher.setCurrId(id);
                    wordSearcher.setSuggestions(names);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}