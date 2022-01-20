package cl.app.arg.compra.controller;

import cl.app.arg.compra.model.Producto;
import cl.app.arg.compra.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return new ResponseEntity<List<Producto>>(this.productoService.getAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/load")
    public ResponseEntity load() {

        for (int i=1;i<50;i++){
            Producto p = new Producto();
            p.setUUID(java.util.UUID.randomUUID().toString());
            p.setDescuento(true);
            p.setNombre("PRODUCTO-00"+ i );
            p.setPrecio((double) (i * 1000));
            p.setCantidad((long) (10 * i));
            productoService.save(p);
        }
        return new ResponseEntity("BAKAN", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") String idProducto) {
        return new ResponseEntity<Producto>(this.productoService.getById(idProducto), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        return new ResponseEntity<Producto>(this.productoService.save(producto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        return new ResponseEntity<Producto>(this.productoService.update(producto), HttpStatus.ACCEPTED);
    }
}
