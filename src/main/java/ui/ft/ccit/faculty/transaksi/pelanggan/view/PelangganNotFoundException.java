package ui.ft.ccit.faculty.transaksi.pelanggan.view;

public class PelangganNotFoundException extends RuntimeException {
    public PelangganNotFoundException(String id) {
        super("Pelanggan dengan id " + id + " tidak ditemukan");
    }
}
