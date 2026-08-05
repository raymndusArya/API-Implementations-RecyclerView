package com.example.api_recyclerview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvUserData;
    ArrayList<UserModel> listUser;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_list);

        rvUserData = (RecyclerView) findViewById(R.id.rvUserData);
        listUser = new ArrayList<>();
        userAdapter = new UserAdapter(MainActivity.this, listUser);
        rvUserData.setLayoutManager(new LinearLayoutManager(this));
        rvUserData.setAdapter(userAdapter);

        getUsers();
    }

    private void getUsers() {
        new AsyncTask<Void, Void, String>() {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(MainActivity.this);
                loading.setMessage("Loading User Data");
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL url = new URL("https://jsonplaceholder.typicode.com/users");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    br.close();
                    return response.toString();

                } catch (Exception e) {

                }

                return "";
            }

            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                loading.dismiss();

                try {

                    JSONArray jsonArrayUser = new JSONArray(response);
                    for (int i = 0; i < jsonArrayUser.length(); i++) {

                        JSONObject jsonObject = jsonArrayUser.getJSONObject(i);

                        UserModel userModel = new UserModel(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("username"),
                                jsonObject.getString("email")
                        );

                        listUser.add(userModel);
                    }

                    userAdapter.notifyDataSetChanged();

                } catch (Exception e) {

                }
            }


        }.execute();
    }
}