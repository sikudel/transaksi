package ui.ft.ccit.faculty.transaksi.transaksi.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import java.util.List;

@Service
@Transactional
public class TransaksiService {

    private final TransaksiRepository repository;

    public TransaksiService(TransaksiRepository repository) {
        this.repository = repository;
    }

    public List<Transaksi> getAll() {
        return repository.findAll();
    }

    public List<Transaksi> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Transaksi getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransaksiNotFoundException(id));
    }

    public List<Transaksi> getByPelanggan(String idPelanggan) {
        return repository.findByIdPelanggan(idPelanggan);
    }

    public Transaksi save(Transaksi transaksi) {
        if (transaksi.getKodeTransaksi() == null || transaksi.getKodeTransaksi().isEmpty()) {
            throw new IllegalArgumentException("Kode Transaksi tidak boleh kosong");
        }
        if (repository.existsById(transaksi.getKodeTransaksi())) {
            throw new TransaksiAlreadyExistsException(transaksi.getKodeTransaksi());
        }
        return repository.save(transaksi);
    }

    public List<Transaksi> saveBulk(List<Transaksi> transaksiList) {
        for (Transaksi t : transaksiList) {
            if (repository.existsById(t.getKodeTransaksi())) {
                throw new TransaksiAlreadyExistsException(t.getKodeTransaksi());
            }
        }
        return repository.saveAll(transaksiList);
    }

    public Transaksi update(String id, Transaksi updated) {
        Transaksi existing = getById(id);
        existing.setTglTransaksi(updated.getTglTransaksi());
        existing.setIdPelanggan(updated.getIdPelanggan());
        existing.setIdKaryawan(updated.getIdKaryawan());
        return repository.save(existing);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new TransaksiNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void deleteBulk(List<String> ids) {
        long count = repository.countByKodeTransaksiIn(ids);
        if (count != ids.size()) {
            throw new IllegalArgumentException("Sebagian ID tidak ditemukan");
        }
        repository.deleteAllById(ids);
    }
}
