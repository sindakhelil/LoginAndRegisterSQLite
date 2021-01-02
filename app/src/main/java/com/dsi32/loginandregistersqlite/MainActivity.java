package com.dsi32.loginandregistersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText editmail,editpass,editconfirm;
    Button boutonreg,boutonlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        editmail = (EditText) findViewById(R.id.id_mail);
        editpass = findViewById(R.id.id_pass);
        editconfirm = (EditText) findViewById(R.id.id_confpass);
        boutonreg = (Button) findViewById(R.id.button_reg);
        boutonlog = (Button) findViewById(R.id.button_login);
        boutonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        boutonreg.setOnClickListener(v -> {
            String editmaile = editmail.getText().toString();
            String editpasse = editpass.getText().toString();
            String editconfirme = editconfirm.getText().toString();
            if (editmaile.equals("") || editpasse.equals("") || editconfirme.equals("")) {
                Toast.makeText(getApplicationContext(), "les champs sont vides", Toast.LENGTH_LONG).show();
            } else {
                if (editpasse.equals(editconfirme)) {
                    Boolean checkmail = db.checkmail(editmaile);
                    if (checkmail == true) {
                        Boolean insert = db.insert(editmaile, editpasse);
                        if (insert == true) {
                            Toast.makeText(getApplicationContext(), "Inscription réussite", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Email existant", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                Toast.makeText(getApplicationContext(), "Mot passe invalide", Toast.LENGTH_LONG).show();
            }

        });

    }
}