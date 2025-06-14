package com.example.sefakaraoke;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KategoriAdapter adapter;
    private List<Kategori> kategoriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        recyclerView = findViewById(R.id.recyclerViewKategoriler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        kategoriList = new ArrayList<>();
        kategoriList.add(new Kategori("Pop"));
        kategoriList.add(new Kategori("Rock"));
        kategoriList.add(new Kategori("Jazz"));
        kategoriList.add(new Kategori("Rap"));

        adapter = new KategoriAdapter(this, kategoriList);
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_kategoriler);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_favoriler) {
                startActivity(new Intent(this, FavorilerActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}
