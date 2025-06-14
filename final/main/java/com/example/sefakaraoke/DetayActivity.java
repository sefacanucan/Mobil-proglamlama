package com.example.sefakaraoke;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetayActivity extends AppCompatActivity {

    private TextView txtBaslik;
    private VideoView videoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        txtBaslik = findViewById(R.id.txtBaslik);
        videoView1 = findViewById(R.id.videoView1);

        String sarkiAdi = getIntent().getStringExtra("sarkiAdi");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        txtBaslik.setText(sarkiAdi != null ? sarkiAdi : "Şarkı");

        if (videoUrl == null || videoUrl.isEmpty()) {
            Toast.makeText(this, "Video URL bulunamadı", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        playMp4(videoView1, videoUrl);
    }

    private void playMp4(VideoView videoView, String url) {
        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();
    }
}
