package ui.ft.ccit.faculty.transaksi.pelanggan.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PelangganRepository extends JpaRepository<Pelanggan, String> {
    List<Pelanggan> findByNamaContainingIgnoreCase(String nama);

    long countByIdPelangganIn(List<String> ids);
}
