package Object;

public class pemesanan {
    private String customerName, ruangan, tanggal, waktuCheckin, durasi;
    private int id, totalHarga;

    public pemesanan(String customerName, String ruangan, String tanggal, String waktuCheckin, String durasi, int id, int totalHarga) {
        this.customerName = customerName;
        this.ruangan = ruangan;
        this.tanggal = tanggal;
        this.waktuCheckin = waktuCheckin;
        this.durasi = durasi;
        this.id = id;
        this.totalHarga = totalHarga;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktuCheckin() {
        return waktuCheckin;
    }

    public void setWaktuCheckin(String waktuCheckin) {
        this.waktuCheckin = waktuCheckin;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
