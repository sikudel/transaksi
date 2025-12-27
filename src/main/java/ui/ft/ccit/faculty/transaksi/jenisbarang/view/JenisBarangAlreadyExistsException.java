package ui.ft.ccit.faculty.transaksi.jenisbarang.view;

public class JenisBarangAlreadyExistsException extends RuntimeException {

    public JenisBarangAlreadyExistsException(Byte idJenisBarang) {
        super("JenisBarang dengan id " + idJenisBarang + " sudah ada");
    }
}
