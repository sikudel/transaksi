package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;

import java.util.List;

@Service
@Transactional
public class DetailTransaksiService {

    private final DetailTransaksiRepository repository;

    public DetailTransaksiService(DetailTransaksiRepository repository) {
        this.repository = repository;
    }

    public List<DetailTransaksi> getAll() {
        return repository.findAll();
    }

    public List<DetailTransaksi> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public DetailTransaksi getById(String kodeTransaksi, String idBarang) {
        DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
        return repository.findById(id)
                .orElseThrow(() -> new DetailTransaksiNotFoundException(id));
    }

    public List<DetailTransaksi> getByKodeTransaksi(String kodeTransaksi) {
        return repository.findByKodeTransaksi(kodeTransaksi);
    }

    public DetailTransaksi save(DetailTransaksi detail) {
        DetailTransaksiId id = new DetailTransaksiId(detail.getKodeTransaksi(), detail.getIdBarang());
        if (repository.existsById(id)) {
            throw new DetailTransaksiAlreadyExistsException(id);
        }
        return repository.save(detail);
    }

    public List<DetailTransaksi> saveBulk(List<DetailTransaksi> detailList) {
        // Simple iteration for existence check could be added here
        return repository.saveAll(detailList);
    }

    public DetailTransaksi update(String kodeTransaksi, String idBarang, DetailTransaksi updated) {
        DetailTransaksi existing = getById(kodeTransaksi, idBarang);
        existing.setJumlah(updated.getJumlah());
        return repository.save(existing);
    }

    public void delete(String kodeTransaksi, String idBarang) {
        DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
        if (!repository.existsById(id)) {
            throw new DetailTransaksiNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
