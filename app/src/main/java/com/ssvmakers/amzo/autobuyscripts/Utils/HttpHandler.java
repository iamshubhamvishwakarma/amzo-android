package com.ssvmakers.amzo.autobuyscripts.Utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {
    private static final String TAG = "HttpHandler";

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuilder.append(readLine);
                    stringBuilder.append(10);
                } else {
                    try {
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        try {
            inputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String makeServiceCall(String str) {
        String str2;
        StringBuilder stringBuilder;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            return convertStreamToString(new BufferedInputStream(httpURLConnection.getInputStream()));
        } catch (MalformedURLException e) {
            str2 = TAG;
            stringBuilder = new StringBuilder("MalformedURLException: ");
            str = e.getMessage();
            stringBuilder.append(str);
            Log.e(str2, stringBuilder.toString());
            return null;
        } catch (ProtocolException e2) {
            str2 = TAG;
            stringBuilder = new StringBuilder("ProtocolException: ");
            str = e2.getMessage();
            stringBuilder.append(str);
            Log.e(str2, stringBuilder.toString());
            return null;
        } catch (IOException e3) {
            str2 = TAG;
            stringBuilder = new StringBuilder("IOException: ");
            str = e3.getMessage();
            stringBuilder.append(str);
            Log.e(str2, stringBuilder.toString());
            return null;
        } catch (Exception e4) {
            str2 = TAG;
            stringBuilder = new StringBuilder("Exception: ");
            str = e4.getMessage();
            stringBuilder.append(str);
            Log.e(str2, stringBuilder.toString());
            return null;
        }
    }
}
