package gt.intecap.apitienda.repository;

import gt.intecap.apitienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
