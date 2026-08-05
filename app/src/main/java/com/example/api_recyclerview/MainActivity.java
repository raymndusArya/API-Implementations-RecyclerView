package com.example.api_recyclerview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class List extends AppCompatActivity {

    RecyclerView rvdatauser;
    ArrayList<UserModel> listUser;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvdatauser = (RecyclerView) findViewById(R.id.rvUserData);
        listUser = new ArrayList<>();
        userAdapter = new UserAdapter(List.this, listUser);
        rvdatauser.setLayoutManager(new LinearLayoutManager(this));
        rvdatauser.setAdapter(userAdapter);

        getUsers();
    }

    @SuppressLint("StaticFieldLeak")
    private void getUsers() {
        new AsyncTask<Void, Void, String>(){
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = new ProgressDialog(List.this);
                loading.setMessage("loading data user");
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL url = new URL("https://jsonplaceholder.typicode.com/users");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream())
                    );
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
            protected void onPostExecute(String respons) {
                super.onPostExecute(respons);

                loading.dismiss();

                try {
                    JSONArray jsonArrayUser = new JSONArray(respons);
                    for (int i = 0; i < jsonArrayUser.length(); i++){
                        JSONObject jsonObject = jsonArrayUser.getJSONObject(i);
                        JSONObject address = jsonObject.getJSONObject("address");

                        String alamat =
                                address.getString("street") + ", " +
                                        address.getString("suite") + ", " +
                                        address.getString("city") + ", " +
                                        address.getString("zipcode");

                        UserModel userModel = new UserModel(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("username"),
                                jsonObject.getString("email"),
                                alamat,
                                jsonObject.getString("phone"),
                                jsonObject.getString("website")
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