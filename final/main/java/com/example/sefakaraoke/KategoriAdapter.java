package com.example.sefakaraoke;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {

    private Context context;
    private List<Kategori> kategoriList;

    public KategoriAdapter(Context context, List<Kategori> kategoriList) {
        this.context = context;
        this.kategoriList = kategoriList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kategori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kategori kategori = kategoriList.get(position);
        holder.txtKategori.setText(kategori.getIsim());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SarkilarActivity.class);
            intent.putExtra("kategori", kategori.getIsim());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKategori = itemView.findViewById(R.id.txtKategori);
        }
    }
}
