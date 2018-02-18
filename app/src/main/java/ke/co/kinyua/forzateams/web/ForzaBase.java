package ke.co.kinyua.forzateams.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import am.wedo.core.BaseActivity;
import ke.co.kinyua.forzateams.R;
import ke.co.kinyua.forzateams.utils.AppConstants;
import ke.co.kinyua.forzateams.web.result_models.Team;

/**
 * Created by Kinyua on 2/18/18.
 */

public abstract class ForzaBase extends BaseActivity {

    private String event;
    public void postJob(String eventName, Object data) {
        event = eventName;
        if (isNetworkAvailable()) {
            if (eventName.equals(AppConstants.FETCH_TEAMS_SERVICE)) {
                GetTeamsService getTeamsService = new GetTeamsService();
                getTeamsService.execute();
            }
        } else {
            handleFault(eventName, getString(R.string.no_internet_connection));
        }
    }

    public void postJobInSeparateThread(String eventName, Object data) {

        if (isNetworkAvailable()) {
            super.postJobInSeparateThread(eventName, data);
        } else {
            handleFault(eventName, getString(R.string.no_internet_connection));
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;

    }

    class GetTeamsService extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {

        }

        protected String doInBackground(Void... urls) {

            try {
                URL url = new URL(AppConstants.URL_FETCH_TEAMS);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("Error", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "No Data Returned";
                handleFault(event,response);
            }
            Log.i("Info:", response);
            try {
                Gson gson = new Gson();
                handleResult(event, gson.fromJson(response, Team[].class));

            } catch (ParseException e){
                e.printStackTrace();
                handleFault(event, "Data From Source is Corrupted");
            } catch (Exception e){
                e.printStackTrace();
                handleFault(event, "An Unknown Error Occurred");
            }
        }
    }
}
