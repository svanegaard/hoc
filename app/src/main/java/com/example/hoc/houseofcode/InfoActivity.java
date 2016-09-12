package com.example.hoc.houseofcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.LinkedHashMap;

public class InfoActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Retrieve the user from the intent
        User loggedInUser = (User)getIntent().getSerializableExtra("User");

        // Make map of values to show in the list view
        LinkedHashMap<String, String> rows = new LinkedHashMap<String, String>();

        // User info
        rows.put("ID", Integer.toString(loggedInUser.getID()));
        rows.put("Name", loggedInUser.getName());
        rows.put("Email", loggedInUser.getEmail());
        rows.put("Member since", loggedInUser.getMemberSince());
        rows.put("Created at", loggedInUser.getCreatedAt());
        rows.put("Updated at", loggedInUser.getUpdatedAt());
        rows.put("Has access", Boolean.toString(loggedInUser.getHasAccess()));
        rows.put("Authentication token", loggedInUser.getAuthToken());

        // Subscription info
        rows.put("Subscription ID", Integer.toString(loggedInUser.getSubscriptionID()));
        rows.put("Subscription expires", loggedInUser.getSubscriptionExpire());
        rows.put("Subscription created at", loggedInUser.getSubscriptionCreatedAt());
        rows.put("Subscription updated at", loggedInUser.getSubscriptionUpdatedAt());

        // Oat bran
        rows.put("Oat bran ID", Integer.toString(loggedInUser.getOatBranID()));
        rows.put("Oat bran amount", Integer.toString(loggedInUser.getOatBranAmout()));
        rows.put("Oat bran created at", loggedInUser.getOatBranCreatedAt());
        rows.put("Oat bran updated at", loggedInUser.getOatBranUpdatedAt());

        // Get list view and set the adapter
        listView = (ListView) findViewById(R.id.listview);
        InfoAdapter adapter = new InfoAdapter(getApplicationContext(), rows);
        listView.setAdapter(adapter);

    }

}
