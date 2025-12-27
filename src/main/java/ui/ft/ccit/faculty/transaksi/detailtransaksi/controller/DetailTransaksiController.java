package ui.ft.ccit.faculty.transaksi.detailtransaksi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.view.DetailTransaksiService;

import java.util.List;

@RestController
@RequestMapping("/api/detail-transaksi")
public class DetailTransaksiController {

    private final DetailTransaksiService service;

    public DetailTransaksiController(DetailTransaksiService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetailTransaksi> list(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null && size == null) {
            return service.getAll();
        }
        return service.getAllWithPagination(page != null ? page : 0, size != null ? size : 10);
    }

    @GetMapping("/{kodeTransaksi}/{idBarang}")
    public DetailTransaksi get(@PathVariable String kodeTransaksi, @PathVariable String idBarang) {
        return service.getById(kodeTransaksi, idBarang);
    }

    @GetMapping("/transaksi/{kodeTransaksi}")
    public List<DetailTransaksi> getByTransaksi(@PathVariable String kodeTransaksi) {
        return service.getByKodeTransaksi(kodeTransaksi);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetailTransaksi create(@RequestBody DetailTransaksi detail) {
        return service.save(detail);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<DetailTransaksi> createBulk(@RequestBody List<DetailTransaksi> detailList) {
        return service.saveBulk(detailList);
    }

    @PutMapping("/{kodeTransaksi}/{idBarang}")
    public DetailTransaksi update(@PathVariable String kodeTransaksi, @PathVariable String idBarang,
            @RequestBody DetailTransaksi detail) {
        return service.update(kodeTransaksi, idBarang, detail);
    }

    @DeleteMapping("/{kodeTransaksi}/{idBarang}")
    public void delete(@PathVariable String kodeTransaksi, @PathVariable String idBarang) {
        service.delete(kodeTransaksi, idBarang);
    }
}
