package fr.charlin.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UsersDetailsActivity extends AppCompatActivity {

    TextView username,email,phone;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_details);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            user = (User) intent.getSerializableExtra("data");
            String usernamedata = user.getUsername();
            String useremail = user.getEmail();
            String userphone = user.getPhone();

            username.setText(getText(R.string.username) + usernamedata);
            email.setText(getText(R.string.email)+ useremail);
            phone.setText(getText(R.string.phone)+ userphone);
        }
    }
}