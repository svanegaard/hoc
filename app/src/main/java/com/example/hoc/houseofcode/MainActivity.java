package com.example.hoc.houseofcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class MainActivity extends AppCompatActivity {

    TextView txtMessage;
    EditText txtEmail, txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText)findViewById(R.id.email);
        txtPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login);
        txtMessage = (TextView)findViewById(R.id.message);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get values from EditText
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                // Run login command that requests data from the server
                login(email, password);

            }
        });

    }

    public void login(String email, String password) {

        // Remove possible error message
        txtMessage.setText("");

        // Set raw json data for request
        String someData = "{\"email\":\"" + email +"\", \"password\":\""+ password +"\"}";
        StringEntity entity = null;
        try {
            entity = new StringEntity(someData);
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch(Exception e) {
            // Exception
            txtMessage.setText("");
            return;
        }

        // Create http client
        AsyncHttpClient client = new AsyncHttpClient();

        // Do not make any retries, just go straight to onFailure if something is not working
        client.setMaxRetriesAndTimeout(0, 5000);

        // Call URL to get user data
        client.post(getApplicationContext(), "http://www.svanegaard.dk/json.php", entity, "application/json", new TextHttpResponseHandler(){

                @Override
                public void onSuccess(int statusCode, Header[] headers, String res) {

                    // HTTP status is 200
                    try {

                        // Try to parse JSON - throw exception if not possible
                        JSONObject result = new JSONObject(res);
                        JSONObject result_user = result.getJSONObject("user");

                        Boolean authorized = result_user.getBoolean("success");

                        if(authorized == true){

                            // We successfully logged in - clear the message text view
                            txtMessage.setText("");

                            // Create the user object and fill it with values
                            User loggedInUser = new User();
                            loggedInUser.setID(result_user.getInt("id"));
                            loggedInUser.setAuthToken(result_user.getString("authentication_token"));
                            loggedInUser.setName(result_user.getString("name"));
                            loggedInUser.setEmail(result_user.getString("email"));
                            loggedInUser.setMemberSince(result_user.getString("member_since"));
                            loggedInUser.setCreatedAt(result_user.getString("created_at"));
                            loggedInUser.setUpdatedAt(result_user.getString("updated_at"));
                            loggedInUser.setHasAccess(result_user.getBoolean("has_access"));

                            // Subscription info
                            JSONObject result_subscription = result_user.getJSONObject("subscription");
                            loggedInUser.setSubscriptionID(result_subscription.getInt("id"));
                            loggedInUser.setSubscriptionExpire(result_subscription.getString("expires"));
                            loggedInUser.setSubscriptionCreatedAt(result_subscription.getString("created_at"));
                            loggedInUser.setSubscriptionUpdatedAt(result_subscription.getString("updated_at"));

                            // Oat bran
                            JSONObject result_oat_bran = result_user.getJSONObject("oat_bran");
                            loggedInUser.setOatBranID(result_oat_bran.getInt("id"));
                            loggedInUser.setOatBranAmount(result_oat_bran.getInt("amount"));
                            loggedInUser.setOatBranCreatedAt(result_oat_bran.getString("created_at"));
                            loggedInUser.setOatBranUpdatedAt(result_oat_bran.getString("updated_at"));

                            // Serialize User object and send to Info activity
                            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("User", loggedInUser);
                            intent.putExtras(bundle);
                            startActivity(intent);


                        }else{

                            // Should actually never get here since 200 OK is always authorized, but show message just to make sure
                            txtMessage.setText("Error with your login or password");

                        }

                    }catch(JSONException e){

                        txtMessage.setText("Could not parse JSON");

                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {

                    switch (statusCode) {
                        case 0:
                            txtMessage.setText("Could not connect to server!");
                            break;
                        case 401:

                            // Forbidden - meaning wrong email and/or password
                            // Try to parse message
                            try {
                                JSONObject result = new JSONObject(res);
                                String message = result.getString("message");
                                txtMessage.setText(message);

                            }catch (JSONException e){
                                txtMessage.setText("Could not parse JSON");
                            }

                            break;
                        default:
                            txtMessage.setText("Unknown error");
                            break;
                    }


                }

            }
        );

    }



}
