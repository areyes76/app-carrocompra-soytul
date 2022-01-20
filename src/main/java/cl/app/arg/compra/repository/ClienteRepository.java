package cl.app.arg.compra.repository;

import cl.app.arg.compra.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
