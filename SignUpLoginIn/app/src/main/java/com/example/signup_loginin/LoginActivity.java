package com.example.signup_loginin;
//Mohit Gupta 1CR18IS091
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;import android.os.Bundle;import android.widget.Button;import android.widget.EditText;import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    int count = 0; // Keep track of login attempts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        Button login = findViewById(R.id.lbutton);
        EditText lusername = findViewById(R.id.lusername);EditText lpassword = findViewById(R.id.lpassword);
        login.setOnClickListener(v -> {
            String uname = lusername.getText().toString();
            String pass = lpassword.getText().toString();
            if (uname.equals(username) && pass.equals(password)) {
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent); }
            else { Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                count++;
                if (count > 2) { login.setEnabled(false);} // disable the login button
            }
        });
    }
}
