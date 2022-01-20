package cl.app.arg.compra.repository;


import cl.app.arg.compra.model.DetalleCompra;
import org.springframework.data.repository.CrudRepository;

public interface DetalleCompraRepository extends CrudRepository<DetalleCompra, Long> {
}
