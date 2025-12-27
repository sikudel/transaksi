package ui.ft.ccit.faculty.transaksi.karyawan.view;

public class KaryawanNotFoundException extends RuntimeException {
    public KaryawanNotFoundException(String id) {
        super("Karyawan dengan id " + id + " tidak ditemukan");
    }
}
