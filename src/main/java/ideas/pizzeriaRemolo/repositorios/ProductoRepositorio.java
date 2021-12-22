
package ideas.pizzeriaRemolo.repositorios;

import ideas.pizzeriaRemolo.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository <Producto,String>{
    
}
