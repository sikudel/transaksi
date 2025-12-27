package ui.ft.ccit.faculty.transaksi.pemasok.view;

public class PemasokNotFoundException extends RuntimeException {

    public PemasokNotFoundException(String idPemasok) {
        super("Pemasok dengan id " + idPemasok + " tidak ditemukan");
    }
}
