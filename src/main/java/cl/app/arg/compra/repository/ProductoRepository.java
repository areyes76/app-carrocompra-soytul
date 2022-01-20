package cl.app.arg.compra.repository;

import cl.app.arg.compra.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, String> {
}
