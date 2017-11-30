package com.example.vs00481543.weatherandnews.dal;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by VS00481543 on 23-11-2017.
 */

public class HttpHandler {

    public HttpHandler()
    {

    }

    public String makeServiceCall(String reqUrl) throws IOException {
        String res=null;

        URL url=new URL(reqUrl);
        HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream=new BufferedInputStream(httpURLConnection.getInputStream());
        res=convertStreamToString(inputStream);

        return res;
    }

    public String convertStreamToString(InputStream str)
    {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(str));
        StringBuilder sb=new StringBuilder();
        String i;

        try{
            while((i=bufferedReader.readLine())!=null)
            {
                sb.append(i).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                str.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();

    }
}
