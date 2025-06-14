package com.example.sefakaraoke;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sefakaraoke.SarkiAdapter;
import com.example.sefakaraoke.FavoriDBHelper;
import com.example.sefakaraoke.Sarki;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class FavorilerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SarkiAdapter adapter;
    private FavoriDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriler);

        dbHelper = new FavoriDBHelper(this);

        recyclerView = findViewById(R.id.recyclerViewFavoriler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Sarki> favoriSarkilar = dbHelper.getTumFavoriler();
        adapter = new SarkiAdapter(this, favoriSarkilar, dbHelper);
        recyclerView.setAdapter(adapter);

        // ⬇️ Bottom Navigation ekleme
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_favoriler);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_kategoriler) {
                startActivity(new Intent(this, KategoriActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}
