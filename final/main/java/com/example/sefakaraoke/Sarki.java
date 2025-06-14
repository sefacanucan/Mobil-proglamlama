public class Sarki {
    private String baslik;
    private String kategori;
    private String videoUrl;
    private String kapakUrl;

    public Sarki(String baslik, String kategori, String videoUrl, String kapakUrl) {
        this.baslik = baslik;
        this.kategori = kategori;
        this.videoUrl = videoUrl;
        this.kapakUrl = kapakUrl;
    }

    public String getBaslik() {
        return baslik;
    }

    public String getKategori() {
        return kategori;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getKapakUrl() {
        return kapakUrl;
    }
}
