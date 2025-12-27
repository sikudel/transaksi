package ui.ft.ccit.faculty.transaksi.karyawan.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import java.util.List;

@Service
@Transactional
public class KaryawanService {

    private final KaryawanRepository repository;

    public KaryawanService(KaryawanRepository repository) {
        this.repository = repository;
    }

    public List<Karyawan> getAll() {
        return repository.findAll();
    }

    public List<Karyawan> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Karyawan getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new KaryawanNotFoundException(id));
    }

    public List<Karyawan> searchByNama(String nama) {
        return repository.findByNamaContainingIgnoreCase(nama);
    }

    public Karyawan save(Karyawan karyawan) {
        if (karyawan.getIdKaryawan() == null || karyawan.getIdKaryawan().isEmpty()) {
            throw new IllegalArgumentException("ID Karyawan tidak boleh kosong");
        }
        if (repository.existsById(karyawan.getIdKaryawan())) {
            throw new KaryawanAlreadyExistsException(karyawan.getIdKaryawan());
        }
        return repository.save(karyawan);
    }

    public List<Karyawan> saveBulk(List<Karyawan> karyawanList) {
        for (Karyawan k : karyawanList) {
            if (repository.existsById(k.getIdKaryawan())) {
                throw new KaryawanAlreadyExistsException(k.getIdKaryawan());
            }
        }
        return repository.saveAll(karyawanList);
    }

    public Karyawan update(String id, Karyawan updated) {
        Karyawan existing = getById(id);
        existing.setNama(updated.getNama());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setGaji(updated.getGaji());
        return repository.save(existing);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new KaryawanNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void deleteBulk(List<String> ids) {
        long count = repository.countByIdKaryawanIn(ids);
        if (count != ids.size()) {
            throw new IllegalArgumentException("Sebagian ID tidak ditemukan");
        }
        repository.deleteAllById(ids);
    }
}
