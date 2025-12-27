package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import java.util.List;

@Service
@Transactional
public class PelangganService {

    private final PelangganRepository repository;

    public PelangganService(PelangganRepository repository) {
        this.repository = repository;
    }

    public List<Pelanggan> getAll() {
        return repository.findAll();
    }

    public List<Pelanggan> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Pelanggan getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new PelangganNotFoundException(id));
    }

    public List<Pelanggan> searchByNama(String nama) {
        return repository.findByNamaContainingIgnoreCase(nama);
    }

    public Pelanggan save(Pelanggan pelanggan) {
        if (pelanggan.getIdPelanggan() == null || pelanggan.getIdPelanggan().isEmpty()) {
            throw new IllegalArgumentException("ID Pelanggan tidak boleh kosong");
        }
        if (repository.existsById(pelanggan.getIdPelanggan())) {
            throw new PelangganAlreadyExistsException(pelanggan.getIdPelanggan());
        }
        return repository.save(pelanggan);
    }

    public List<Pelanggan> saveBulk(List<Pelanggan> pelangganList) {
        for (Pelanggan p : pelangganList) {
            if (repository.existsById(p.getIdPelanggan())) {
                throw new PelangganAlreadyExistsException(p.getIdPelanggan());
            }
        }
        return repository.saveAll(pelangganList);
    }

    public Pelanggan update(String id, Pelanggan updated) {
        Pelanggan existing = getById(id);
        existing.setNama(updated.getNama());
        existing.setAlamat(updated.getAlamat());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setJenisPelanggan(updated.getJenisPelanggan());
        return repository.save(existing);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new PelangganNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void deleteBulk(List<String> ids) {
        long count = repository.countByIdPelangganIn(ids);
        if (count != ids.size()) {
            throw new IllegalArgumentException("Sebagian ID tidak ditemukan");
        }
        repository.deleteAllById(ids);
    }
}
