
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
    public Producto guardarProducto(MultipartFile archivo,String categoria, String nombre,Double precio) throws ErrorServicio{

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);

        Foto foto=fotoServicio.guardar(archivo);
        producto.setFoto(foto);

        return productoRepositorio.save(producto);
    }

    @Transactional
    public void modificarProducto(MultipartFile archivo,String id,String categoria, String nombre,Double precio) throws ErrorServicio{

        Optional<Producto>respuesta=productoRepositorio.findById(id);
        if(respuesta.isPresent()){
            Producto producto=respuesta.get();
            producto.setNombre(nombre);
            producto.setCategoria(categoria);
            producto.setPrecio(precio);

            String idFoto = null;
            if (producto.getFoto() != null) {
                idFoto = producto.getFoto().getId();
            }

            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            producto.setFoto(foto);

            productoRepositorio.save(producto);
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
    public void deleteById(String id) {
        Optional<Producto> optional = productoRepositorio.findById(id);
        if (optional.isPresent()) {
            productoRepositorio.delete(optional.get());
        }
    }
}
