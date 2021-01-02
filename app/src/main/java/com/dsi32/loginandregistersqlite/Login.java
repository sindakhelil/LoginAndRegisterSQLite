package com.dsi32.loginandregistersqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    DatabaseHelper db;
    EditText editmail;
    EditText editpass;
    Button boutonlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        editmail = (EditText) findViewById(R.id.id_mail);
        editpass = findViewById(R.id.id_pass);
        boutonlog = (Button) findViewById(R.id.button_login);
        boutonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editmail.getText().toString();
                String  password = editpass.getText().toString();
                Boolean checkmailpass = db.emailpassword(email,password);
                if (checkmailpass==true){
                    Toast.makeText(getApplicationContext(),"login est valide",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Email ou mot de passe invalides !",Toast.LENGTH_LONG).show();
                }
            }
        });
}
}