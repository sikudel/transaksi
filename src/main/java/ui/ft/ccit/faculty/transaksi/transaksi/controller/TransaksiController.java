package ui.ft.ccit.faculty.transaksi.transaksi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransaksiService;

import java.util.List;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    private final TransaksiService service;
    private final ui.ft.ccit.faculty.transaksi.manager.TransactionManager transactionManager;

    public TransaksiController(TransaksiService service,
            ui.ft.ccit.faculty.transaksi.manager.TransactionManager transactionManager) {
        this.service = service;
        this.transactionManager = transactionManager;
    }

    @GetMapping
    public List<Transaksi> list(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null && size == null) {
            return service.getAll();
        }
        return service.getAllWithPagination(page != null ? page : 0, size != null ? size : 10);
    }

    @GetMapping("/{id}")
    public Transaksi get(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/pelanggan/{idPelanggan}")
    public List<Transaksi> getByPelanggan(@PathVariable String idPelanggan) {
        return service.getByPelanggan(idPelanggan);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaksi create(@RequestBody Transaksi transaksi) {
        return service.save(transaksi);
    }

    // Endpoint baru untuk Transaksi Lengkap (Header + Detail + Potong Stok)
    @PostMapping("/full")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaksi createFull(@RequestBody ui.ft.ccit.faculty.transaksi.dto.TransactionRequest request) {
        return transactionManager.createTransaction(request);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Transaksi> createBulk(@RequestBody List<Transaksi> transaksiList) {
        return service.saveBulk(transaksiList);
    }

    @PutMapping("/{id}")
    public Transaksi update(@PathVariable String id, @RequestBody Transaksi transaksi) {
        return service.update(id, transaksi);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @DeleteMapping("/bulk")
    public void deleteBulk(@RequestBody List<String> ids) {
        service.deleteBulk(ids);
    }
}
