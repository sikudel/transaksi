package ui.ft.ccit.faculty.transaksi.manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;
import ui.ft.ccit.faculty.transaksi.dto.TransactionDetailRequest;
import ui.ft.ccit.faculty.transaksi.dto.TransactionRequest;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import java.time.LocalDateTime;

@Service
public class TransactionManager {

    private final TransaksiRepository transaksiRepository;
    private final DetailTransaksiRepository detailRepository;
    private final BarangRepository barangRepository;

    public TransactionManager(TransaksiRepository transaksiRepository,
            DetailTransaksiRepository detailRepository,
            BarangRepository barangRepository) {
        this.transaksiRepository = transaksiRepository;
        this.detailRepository = detailRepository;
        this.barangRepository = barangRepository;
    }

    @Transactional
    public Transaksi createTransaction(TransactionRequest request) {
        // 1. Validate & Create Header
        if (transaksiRepository.existsById(request.getKodeTransaksi())) {
            throw new IllegalArgumentException("Kode Transaksi " + request.getKodeTransaksi() + " sudah ada");
        }

        Transaksi transaksi = new Transaksi();
        transaksi.setKodeTransaksi(request.getKodeTransaksi());
        transaksi.setIdPelanggan(request.getIdPelanggan());
        transaksi.setIdKaryawan(request.getIdKaryawan());
        transaksi.setTglTransaksi(LocalDateTime.now());

        transaksiRepository.save(transaksi);

        // 2. Process Details
        for (TransactionDetailRequest item : request.getDetails()) {
            Barang barang = barangRepository.findById(item.getIdBarang())
                    .orElseThrow(
                            () -> new IllegalArgumentException("Barang " + item.getIdBarang() + " tidak ditemukan"));

            // Check Stock
            if (barang.getStok() < item.getJumlah()) {
                throw new IllegalArgumentException(
                        "Stok barang " + barang.getNama() + " tidak cukup. Tersedia: " + barang.getStok());
            }

            // Deduct Stock
            barang.setStok((short) (barang.getStok() - item.getJumlah()));
            barangRepository.save(barang);

            // Save Detail
            DetailTransaksi detail = new DetailTransaksi(
                    request.getKodeTransaksi(),
                    item.getIdBarang(),
                    item.getJumlah());
            detailRepository.save(detail);
        }

        return transaksi;
    }
}
