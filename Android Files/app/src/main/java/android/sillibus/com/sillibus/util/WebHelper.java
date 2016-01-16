package android.sillibus.com.sillibus.util;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by joshua on 10/3/15.
 */
public class WebHelper {
    private static ArrayList<Runnable> queue = new ArrayList<>();

    private static String getPostDataString(HashMap<String, String> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            try {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
            result.append("=");
            try {
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }

        return result.toString();
    }

    public static void performGet(final String urlExtension, final HTTPResponse onPostExecute) {
        new MyTask().setPostExecute(onPostExecute).getRequest().execute(urlExtension);
    }

    public static void performPost(final String urlExtension, HashMap<String, String> postDataParams, final HTTPResponse onPostExecute) {
        new MyTask().setPostExecute(onPostExecute).postRequest(postDataParams).execute(urlExtension);
    }

    public static void performPost(final String urlExtension, String postDataParams, final HTTPResponse onPostExecute) {
        new MyTask().setPostExecute(onPostExecute).postRequest(postDataParams).execute(urlExtension);
    }


    public static Object[] urlEncode(Object... args) {
        ArrayList<String> result = new ArrayList<>();
        for (Object arg : args) {
            try {
                result.add(URLEncoder.encode(String.valueOf(arg), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return args;
            }
        }
        return result.toArray(args);
    }

    private static class MyTask extends AsyncTask<String, Integer, String> {

        private HTTPResponse postExecute;
        private String requestMethod;
        private boolean isPost;
        private String postDataParams;

        @Override
        protected String doInBackground(String... extensions) {
            this.publishProgress();
            URL url;
            String response = "";
            int responseCode = 0;
            try {
                url = new URL(String.format(Paths.BASE_API + "%s", extensions));

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod(requestMethod);


                if (isPost) {
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(postDataParams);

                    writer.flush();
                    writer.close();
                    os.close();
                }

                responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    response = postDataParams;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            recordResults(response, responseCode);
            return response;
        }

        public MyTask getRequest() {
            requestMethod = "GET";
            isPost = false;
            return this;
        }

        public MyTask postRequest(HashMap<String, String> postDataParams) {
            return postRequest(getPostDataString(postDataParams));
        }

        /*   @Override
           protected void onPostExecute(String result) {
               recordResults(result);
               super.onPostExecute(result);
           }
   */
        private void recordResults(final String result, int responseCode) {
            postExecute.httpResponseReceived(result, responseCode);
        }

        public MyTask setPostExecute(HTTPResponse postExecute) {
            this.postExecute = postExecute;
            return this;
        }

        public MyTask postRequest(String postDataParams) {
            this.postDataParams = postDataParams;
            requestMethod = "POST";
            isPost = true;
            return this;
        }
    }
}
