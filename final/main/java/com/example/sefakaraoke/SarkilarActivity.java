package com.example.sefakaraoke;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SarkilarActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SarkiAdapter adapter;
    private FavoriDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarkilar);

        recyclerView = findViewById(R.id.recyclerViewSarkilar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new FavoriDBHelper(this);

        // Kategori adı Intent ile alınıyor
        String kategoriAdi = getIntent().getStringExtra("kategoriAdi");

        if (kategoriAdi != null) {
            setTitle(kategoriAdi); // Üst başlık olarak kategori adı gösterilsin
        }

        // Veritabanından kategoriye göre şarkıları çek
        List<Sarki> sarkiList = dbHelper.kategoriyeGoreSarkilar(kategoriAdi);

        // Adapteri ayarla (3 parametreli yapıcı)
        adapter = new SarkiAdapter(this, sarkiList, dbHelper);
        recyclerView.setAdapter(adapter);
    }
}
