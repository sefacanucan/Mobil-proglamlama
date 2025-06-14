package com.example.sefakaraoke;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtAdSoyad, txtEmail, txtGender, txtCity;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtAdSoyad = findViewById(R.id.txtAdSoyad);
        txtEmail = findViewById(R.id.txtEmail);
        txtGender = findViewById(R.id.txtGender);
        txtCity = findViewById(R.id.txtCity);
        btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences sp = getSharedPreferences("KullaniciBilgileri", MODE_PRIVATE);

        txtAdSoyad.setText("Ad Soyad: " + sp.getString("adSoyad", ""));
        txtEmail.setText("E-posta: " + sp.getString("email", ""));
        txtGender.setText("Cinsiyet: " + sp.getString("gender", ""));
        txtCity.setText("Şehir: " + sp.getString("city", ""));

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.clear(); // verileri temizle
            editor.apply();

            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
