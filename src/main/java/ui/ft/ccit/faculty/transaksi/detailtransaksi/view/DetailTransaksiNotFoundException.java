package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;

public class DetailTransaksiNotFoundException extends RuntimeException {
    public DetailTransaksiNotFoundException(DetailTransaksiId id) {
        super("DetailTransaksi dengan kode transaksi " + id.getKodeTransaksi() + " dan id barang " + id.getIdBarang()
                + " tidak ditemukan");
    }
}
