package com.example.proyectoappsmovilesdavinci;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(v -> {
            // Acá validarías usuario/contraseña si correspondiera
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish(); // evita volver al login con back
        });
    }
}
