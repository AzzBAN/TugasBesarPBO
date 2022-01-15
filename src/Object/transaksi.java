package Object;

public class transaksi {
    private String customerName, ruangan;
    private int id, totalHarga;

    public transaksi(String customerName, String ruangan, int id, int totalHarga) {
        this.customerName = customerName;
        this.ruangan = ruangan;
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
