package com.example.sefakaraoke;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SarkiAdapter extends RecyclerView.Adapter<SarkiAdapter.SarkiViewHolder> {

    private Context context;
    private List<Sarki> sarkiList;
    private FavoriDBHelper dbHelper;

    public SarkiAdapter(Context context, List<Sarki> sarkiList, FavoriDBHelper dbHelper) {
        this.context = context;
        this.sarkiList = sarkiList;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public SarkiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sarki, parent, false);
        return new SarkiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SarkiViewHolder holder, int position) {
        Sarki sarki = sarkiList.get(position);
        holder.txtBaslik.setText(sarki.getBaslik());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetayActivity.class);
            intent.putExtra("sarkiAdi", sarki.getBaslik());
            intent.putExtra("videoUrl", sarki.getUrl());
            context.startActivity(intent);
        });

        if (dbHelper.favorideMi(sarki.getBaslik())) {
            holder.btnFavori.setImageResource(R.drawable.ic_favori_dolu);
        } else {
            holder.btnFavori.setImageResource(R.drawable.ic_favori_bos);
        }

        holder.btnFavori.setOnClickListener(v -> {
            if (dbHelper.favorideMi(sarki.getBaslik())) {
                dbHelper.favoriSil(sarki.getBaslik());
                holder.btnFavori.setImageResource(R.drawable.ic_favori_bos);
            } else {
                dbHelper.favoriEkle(sarki.getBaslik(), sarki.getKategori(), sarki.getUrl());
                holder.btnFavori.setImageResource(R.drawable.ic_favori_dolu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sarkiList.size();
    }

    public static class SarkiViewHolder extends RecyclerView.ViewHolder {
        TextView txtBaslik;
        ImageButton btnFavori;

        public SarkiViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBaslik = itemView.findViewById(R.id.txtBaslik);
            btnFavori = itemView.findViewById(R.id.btnFavori);
        }
    }
}
