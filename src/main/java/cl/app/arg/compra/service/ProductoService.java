package cl.app.arg.compra.service;


import cl.app.arg.compra.model.Producto;
import cl.app.arg.compra.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;


    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        productoRepository.findAll().forEach(producto -> productos.add(producto));
        return productos;
    }


    public Producto getById(String idProducto) {
        return productoRepository.findById(idProducto).get();
    }


    @Transactional(readOnly = false)
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }


    @Transactional(readOnly = false)
    public Producto update(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Metodo que actualiza el stock de producto, cuando se realiza la compra.
     * @param idProducto
     * @param cantidad
     */
    public void update(String idProducto, Long cantidad) {
        Producto producto = this.getById(idProducto);
        producto.setCantidad(producto.getCantidad() - cantidad);
        this.update(producto);
    }

}
