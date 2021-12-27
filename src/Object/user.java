package Object;

import java.time.LocalDate;

public class user {
    private String nama, username, password, posisi;
    String tglLahir;
    private int id;

    public user(String nama, String username, String password, String posisi, String tglLahir, int id) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.posisi = posisi;
        this.tglLahir = tglLahir;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
