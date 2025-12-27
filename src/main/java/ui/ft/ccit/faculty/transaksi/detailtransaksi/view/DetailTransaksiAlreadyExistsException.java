package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;

public class DetailTransaksiAlreadyExistsException extends RuntimeException {
    public DetailTransaksiAlreadyExistsException(DetailTransaksiId id) {
        super("DetailTransaksi dengan kode transaksi " + id.getKodeTransaksi() + " dan id barang " + id.getIdBarang()
                + " sudah ada");
    }
}
