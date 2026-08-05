package com.example.api_recyclerview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    EditText edUser;
    EditText edPw;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edUser = findViewById(R.id.edUser);
        edPw = findViewById(R.id.edPw);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> login());
    }

    @SuppressLint("StaticFieldLeak")
    public void login(){
        final String username = edUser.getText().toString().trim();
        final String password = edPw.getText().toString().trim();
        new AsyncTask<String, Void, String>(){

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(Login.this);
                loading.setMessage("Loading User Data");
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String username = strings[0];
                String password = strings[1];
                try {

                    URL url = new URL("https://mediadwi.com/api/latihan/login");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(10000); // 10 detik
                    conn.setReadTimeout(10000);    // 10 detik
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String request =
                                    "username="+ username +
                                    "&password="+ password;

                    OutputStream os = conn.getOutputStream();
                    os.write(request.getBytes("UTF-8"));
                    os.flush();
                    os.close();

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())
                    );

                    StringBuilder response = new StringBuilder();

                    String line;

                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }

                    br.close();

                    return response.toString();

                }catch (Exception e){
                    e.printStackTrace();
                    return "ERROR: " + e.toString();
                }
            }

            //dijalankna setelah doinbackground
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                loading.dismiss();

                try {

                    //Menggunakan JSON
                    JSONObject jsonObject = new JSONObject(result);
                    Boolean status = jsonObject.getBoolean("status");
                    String message = jsonObject.getString("message");

                    Toast.makeText(Login.this,("Login Succesfull"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));

                }catch (Exception e){
                    Toast.makeText(Login.this, "error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(username, password);
    }
}