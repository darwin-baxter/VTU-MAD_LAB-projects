package com.example.signup_loginin;
//Mohit Gupta 1CR18IS091
import androidx.appcompat.app.AppCompatActivity;import android.content.Intent;
import android.os.Bundle;import android.widget.Button;
import android.widget.EditText;import android.widget.Toast;
public class SignupActivity extends AppCompatActivity {
    String username, password;String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText susername = findViewById(R.id.susername);
        EditText spassword = findViewById(R.id.spassword);
        Button signup = findViewById(R.id.sbutton);
        signup.setOnClickListener((v -> {
            username = susername.getText().toString();
            password = spassword.getText().toString();
            if (password.matches(regex)) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.putExtra("username", username);intent.putExtra("password", password);
                startActivity(intent); } else { Toast.makeText(getApplicationContext(),
                        "your password does not match the validation criteria",
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
