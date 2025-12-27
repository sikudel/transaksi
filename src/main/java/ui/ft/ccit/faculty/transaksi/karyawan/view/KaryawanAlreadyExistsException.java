package ui.ft.ccit.faculty.transaksi.karyawan.view;

public class KaryawanAlreadyExistsException extends RuntimeException {
    public KaryawanAlreadyExistsException(String id) {
        super("Karyawan dengan id " + id + " sudah ada");
    }
}
