package ui.ft.ccit.faculty.transaksi.transaksi.view;

public class TransaksiNotFoundException extends RuntimeException {
    public TransaksiNotFoundException(String id) {
        super("Transaksi dengan kode " + id + " tidak ditemukan");
    }
}
