
package ideas.pizzeriaRemolo.servicios;

import ideas.pizzeriaRemolo.entidades.Foto;
import ideas.pizzeriaRemolo.entidades.Producto;
import ideas.pizzeriaRemolo.errores.ErrorServicio;
import ideas.pizzeriaRemolo.repositorios.ProductoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;
    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public Producto guardarProducto(Producto producto) throws ErrorServicio{
        return productoRepositorio.save(producto);
    }

    @Transactional
    public Producto modificarProducto(String id, Producto producto) throws ErrorServicio{

        Optional<Producto>productoBdOptional=productoRepositorio.findById(id);
        if(productoBdOptional.isPresent()){
            Producto productoBd=productoBdOptional.get();
            productoBd.setNombre(producto.getNombre());
            productoBd.setCategoria(producto.getCategoria());
            productoBd.setPrecio(producto.getPrecio());
            //WARNING: si la foto no trae ID se crea otra
            productoBd.setFoto(producto.getFoto());
            productoRepositorio.save(productoBd);
            return productoBd;
        }else{
            throw new ErrorServicio("No se encuentra el producto");     }
    }

    public Optional<Producto> findById(String id) {
        return productoRepositorio.findById(id);
    }
    public List<Producto> listAll() {
        return productoRepositorio.findAll();
    }
    @Transactional
    public void deleteById(String id) throws ErrorServicio {
        Optional<Producto> optional = productoRepositorio.findById(id);
        if (optional.isPresent()) {
            productoRepositorio.delete(optional.get());
        }else {
            throw new ErrorServicio("El producto " + id + " no fue encontrado");
        }
    }
}
