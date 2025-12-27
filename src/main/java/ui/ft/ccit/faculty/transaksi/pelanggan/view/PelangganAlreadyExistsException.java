package ui.ft.ccit.faculty.transaksi.pelanggan.view;

public class PelangganAlreadyExistsException extends RuntimeException {
    public PelangganAlreadyExistsException(String id) {
        super("Pelanggan dengan id " + id + " sudah ada");
    }
}
