package Object;

public class ruangan {
    private String nama, lokasi, besarRuanganm, fasilitas;
    int rating;

    public ruangan(String nama, String lokasi, int rating, String besarRuanganm, String fasilitas) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.rating = rating;
        this.besarRuanganm = besarRuanganm;
        this.fasilitas = fasilitas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBesarRuanganm() {
        return besarRuanganm;
    }

    public void setBesarRuanganm(String besarRuanganm) {
        this.besarRuanganm = besarRuanganm;
    }

    public String getfasilitas() {
        return fasilitas;
    }

    public void setfasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }
}
