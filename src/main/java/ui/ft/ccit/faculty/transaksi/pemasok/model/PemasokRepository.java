package ui.ft.ccit.faculty.transaksi.pemasok.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PemasokRepository extends JpaRepository<Pemasok, String> {
    List<Pemasok> findByNamaPemasokContainingIgnoreCase(String namaPemasok);

    long countByIdPemasokIn(List<String> ids);
}
