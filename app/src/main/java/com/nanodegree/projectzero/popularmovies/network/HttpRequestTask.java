package com.nanodegree.projectzero.popularmovies.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.nanodegree.projectzero.popularmovies.constants.NetworkConstants;
import com.nanodegree.projectzero.popularmovies.core.HttpRequestStatusListener;
import com.nanodegree.projectzero.popularmovies.core.HttpRequestTaskController;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Gautam on 28/06/15.
 */
public class HttpRequestTask extends AsyncTask<String, Void, String> {

    private HttpRequestStatusListener mHttpRequestStatusListener = null;

    public HttpRequestTask(HttpRequestTaskController context) {
        this.mHttpRequestStatusListener = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {
        return httpGetRequest(params[0]);

    }


    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        if (response != null) {
            if (!response.equalsIgnoreCase(NetworkConstants.FAILED_HTTP_REQUEST_MESSAGE)) {
                mHttpRequestStatusListener.httpRequestSuccesful(response);
            } else {
                mHttpRequestStatusListener.httpRequestFailed(response);
            }
        } else {
            mHttpRequestStatusListener.httpRequestFailed(response);
        }
    }


    private String httpGetRequest(String url) {
        String mHttpGetResponse = null;
        InputStream mInputStream;

        try {
            HttpClient mHttpClient = new DefaultHttpClient();

            HttpResponse mHttpResponse = mHttpClient.execute(new HttpGet(url));

            mInputStream = mHttpResponse.getEntity().getContent();

            if (mInputStream != null) {
                mHttpGetResponse = convertInputStreamToString(mInputStream);
            } else {
                mHttpGetResponse = NetworkConstants.FAILED_HTTP_REQUEST_MESSAGE;
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return mHttpGetResponse;

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


}
