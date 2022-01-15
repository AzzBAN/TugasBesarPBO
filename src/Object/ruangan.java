package Object;

public class ruangan {
    private String nama, lokasi, besarRuanganm, fasilitas;
    int rating, harga;

    public ruangan(String nama, String lokasi, int rating, String besarRuanganm, String fasilitas, int harga) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.rating = rating;
        this.besarRuanganm = besarRuanganm;
        this.fasilitas = fasilitas;
        this.harga = harga;
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

    public int getHarga() {
        return harga;
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
