package ideas.pizzeriaRemolo.controllers;

import ideas.pizzeriaRemolo.entidades.Producto;
import ideas.pizzeriaRemolo.errores.ErrorServicio;
import ideas.pizzeriaRemolo.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("productos")
public class ProductoController {

	@Autowired
	private ProductoServicio productoService;

	@GetMapping
	public List<Producto> getAllProductos() {
		return productoService.listAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> getProductoById(@PathVariable String id) {
		Optional<Producto> producto = productoService.findById(id);
		if(!producto.isPresent()){
			return new ResponseEntity<>("El producto " + id + " no fue encontrado", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(producto.get(), HttpStatus.OK);
	}

	@PostMapping
	public Producto saveProducto(@RequestBody Producto producto) {
		//return productoService.saveProducto(producto);
		return new Producto();
	}

	@PutMapping(path = "/{id}")
	public Producto updateProducto(@PathVariable String id, @RequestBody Producto producto) {
		//return productoService.updateProducto(id, producto);
		return new Producto();
	}

	@DeleteMapping(path = "/{id}")
	public boolean deleteProducto(@PathVariable String id) throws ErrorServicio {
		productoService.deleteById(id);
		return true;
	}
}
