package ui.ft.ccit.faculty.transaksi.dto;

public class TransactionDetailRequest {
    private String idBarang;
    private Short jumlah;

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public Short getJumlah() {
        return jumlah;
    }

    public void setJumlah(Short jumlah) {
        this.jumlah = jumlah;
    }
}
