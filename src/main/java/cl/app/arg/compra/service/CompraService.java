package cl.app.arg.compra.service;

import cl.app.arg.compra.model.Compra;
import cl.app.arg.compra.model.Producto;
import cl.app.arg.compra.repository.CompraRepository;
import cl.app.arg.compra.repository.DetalleCompraRepository;

import cl.app.arg.compra.util.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CompraService{
    @Autowired
    CompraRepository compraRepository;
    @Autowired
    DetalleCompraRepository detalleCompraRepository;
    @Autowired
    ProductoService productoService;

    public List<Compra> getAll() {
        List<Compra> compras = new ArrayList<>();
        compraRepository.findAll().forEach(compra -> compras.add(compra));
        return compras;
    }

    public Compra getById(Long idCompra) {
        return compraRepository.findById(idCompra).get();
    }

    
    @Transactional(readOnly = false)
    public Compra save(Compra compra) {
        compra.setFchCrea(new Date());
        compra.setEstado(Estado.Completado.toString());
        compra.setTotal(
                compra.getListaDetalleCompra()
                        .stream()
                        .mapToDouble(detalle -> {
                            // Calcula precio del producto * cantidad
                            Producto producto = productoService.getById(detalle.getProducto().getUUID());
                            if(producto.isDescuento())
                                detalle.setPrecio(producto.getPrecio()/2);
                            else
                                detalle.setPrecio(producto.getPrecio());
                            detalle.setTotal(detalle.getPrecio() * detalle.getCantidad());
                            return detalle.getTotal();
                        })
                        .sum()
        );

        compra.getListaDetalleCompra().forEach(detalle -> productoService.update(detalle.getProducto().getUUID(), detalle.getCantidad()));
        compraRepository.save(compra);
        compra.getListaDetalleCompra().forEach(detalleCompra -> {
            detalleCompra.setCompra(compra);
            detalleCompraRepository.save(detalleCompra);
        });
        return compra;
    }
}
