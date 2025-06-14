package com.example.sefakaraoke;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editAdSoyad, editEmail, editPassword;
    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale;
    private Spinner spinnerCities;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editAdSoyad = findViewById(R.id.editTextAdSoyad);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        spinnerCities = findViewById(R.id.spinnerCities);
        btnRegister = findViewById(R.id.btnRegister);

        // Şehir listesini spinner'a ekle
        String[] sehirler = {"İstanbul", "Ankara", "İzmir", "Bursa", "Antalya", "Adana", "Trabzon", "Eskişehir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sehirler);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCities.setAdapter(adapter);

        btnRegister.setOnClickListener(view -> {
            String adSoyad = editAdSoyad.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            String gender = "";
            if (selectedGenderId == R.id.radioMale) {
                gender = "Erkek";
            } else if (selectedGenderId == R.id.radioFemale) {
                gender = "Kadın";
            }

            String city = spinnerCities.getSelectedItem().toString();

            if (adSoyad.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show();
            } else {
                // Bilgileri SharedPreferences ile kaydet
                SharedPreferences sp = getSharedPreferences("KullaniciBilgileri", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("adSoyad", adSoyad);
                editor.putString("email", email);
                editor.putString("password", password); // giriş için
                editor.putString("gender", gender);
                editor.putString("city", city);
                editor.apply();

                Toast.makeText(this, "Kayıt başarılı!", Toast.LENGTH_LONG).show();

                // Giriş ekranına yönlendir
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
