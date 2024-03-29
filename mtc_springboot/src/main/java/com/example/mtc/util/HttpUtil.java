package com.example.mtc.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("ALL")
public final class HttpUtil {

  public static String post(String requestUrl, String accessToken, String params)
          throws Exception {
    String contentType = "application/x-www-form-urlencoded";
    return HttpUtil.post(requestUrl, accessToken, contentType, params);
  }

  public static String post(String requestUrl, String accessToken, String contentType, String params)
          throws Exception {
    String encoding = "UTF-8";
    if (requestUrl.contains("nlp")) {
      encoding = "GBK";
    }
    return HttpUtil.post(requestUrl, accessToken, contentType, params, encoding);
  }

  public static String post(String requestUrl, String accessToken, String contentType, String params, String encoding)
          throws Exception {
    String url = requestUrl + "?access_token=" + accessToken;
    return HttpUtil.postGeneralUrl(url, contentType, params, encoding);
  }

  public static String postGeneralUrl(String generalUrl, String contentType, String params, String encoding)
          throws Exception {
    URL url = new URL(generalUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", contentType);
    connection.setRequestProperty("Connection", "Keep-Alive");
    connection.setUseCaches(false);
    connection.setDoOutput(true);
    connection.setDoInput(true);

    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    out.write(params.getBytes(encoding));
    out.flush();
    out.close();

    connection.connect();
    BufferedReader in = null;
    in = new BufferedReader(
            new InputStreamReader(connection.getInputStream(), encoding));
    StringBuilder result = new StringBuilder();
    String getLine;
    while ((getLine = in.readLine()) != null) {
      result.append(getLine);
    }
    in.close();
    return result.toString();
  }

  public final byte[] getUrl(String u) throws IOException {
    URL url = new URL(u);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setDoOutput(true);
    connection.setDoInput(true);

    connection.connect();
    BufferedReader in = null;
    return null;
  }
}
