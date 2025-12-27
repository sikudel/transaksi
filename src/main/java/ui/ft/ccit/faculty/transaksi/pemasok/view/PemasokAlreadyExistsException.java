package ui.ft.ccit.faculty.transaksi.pemasok.view;

public class PemasokAlreadyExistsException extends RuntimeException {

    public PemasokAlreadyExistsException(String idPemasok) {
        super("Pemasok dengan id " + idPemasok + " sudah ada");
    }
}
