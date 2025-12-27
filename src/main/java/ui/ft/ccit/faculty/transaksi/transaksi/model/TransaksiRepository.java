package ui.ft.ccit.faculty.transaksi.transaksi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransaksiRepository extends JpaRepository<Transaksi, String> {
    List<Transaksi> findByIdPelanggan(String idPelanggan);

    long countByKodeTransaksiIn(List<String> ids);
}
