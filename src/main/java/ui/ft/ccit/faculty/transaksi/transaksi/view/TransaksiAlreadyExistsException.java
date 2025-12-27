package ui.ft.ccit.faculty.transaksi.transaksi.view;

public class TransaksiAlreadyExistsException extends RuntimeException {
    public TransaksiAlreadyExistsException(String id) {
        super("Transaksi dengan kode " + id + " sudah ada");
    }
}
