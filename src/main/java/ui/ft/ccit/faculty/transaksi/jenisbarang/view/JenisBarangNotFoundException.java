package ui.ft.ccit.faculty.transaksi.jenisbarang.view;

public class JenisBarangNotFoundException extends RuntimeException {

    public JenisBarangNotFoundException(Byte idJenisBarang) {
        super("JenisBarang dengan id " + idJenisBarang + " tidak ditemukan");
    }
}
