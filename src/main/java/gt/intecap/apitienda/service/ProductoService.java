package gt.intecap.apitienda.service;

import gt.intecap.apitienda.model.Producto;
import gt.intecap.apitienda.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto no encontrado: " + id));
    }

    public Producto crear(Producto producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto existente = obtener(id);
        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        obtener(id);
        repository.deleteById(id);
    }
}
