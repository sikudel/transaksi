package ui.ft.ccit.faculty.transaksi.dto;

import java.util.List;

public class TransactionRequest {
    private String kodeTransaksi;
    private String idPelanggan;
    private String idKaryawan;
    private List<TransactionDetailRequest> details;

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public List<TransactionDetailRequest> getDetails() {
        return details;
    }

    public void setDetails(List<TransactionDetailRequest> details) {
        this.details = details;
    }
}
