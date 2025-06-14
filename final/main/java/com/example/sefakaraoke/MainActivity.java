package com.example.sefakaraoke;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SarkiAdapter adapter;
    private List<Sarki> tumSarkilar;
    private FavoriDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 🔧 Doğru parametre sırası: (Başlık, Kategori, URL)
        tumSarkilar = new ArrayList<>();
        tumSarkilar.add(new Sarki("Sefa - Gülümsee", "Pop", "https://www.w3schools.com/html/mov_bbb.mp4"));
        tumSarkilar.add(new Sarki("Tarkan - Şımarık", "Pop", "https://www.w3schools.com/html/mov_bbb.mp4"));
        tumSarkilar.add(new Sarki("Ajda Pekkan - Yakarım Canını", "Pop", "https://www.w3schools.com/html/mov_bbb.mp4"));
        tumSarkilar.add(new Sarki("Sefa - Gülümsee", "Pop", "https://www.w3schools.com/html/mov_bbb.mp4"));
        tumSarkilar.add(new Sarki("Tarkan - Şımarık", "Pop", "https://www.w3schools.com/html/mov_bbb.mp4"));
        tumSarkilar.add(new Sarki("Ajda Pekkan - Yakarım Canını", "Pop", "https://www.w3schools.com/html/mov_bbb.mp4"));
        dbHelper = new FavoriDBHelper(this);
        adapter = new SarkiAdapter(this, tumSarkilar, dbHelper);
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_kategoriler) {
                startActivity(new Intent(this, KategoriActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_favoriler) {
                startActivity(new Intent(this, FavorilerActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_profil) {
                startActivity(new Intent(this, ProfileActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}
