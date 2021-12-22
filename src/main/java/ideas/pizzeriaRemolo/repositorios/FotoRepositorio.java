
package ideas.pizzeriaRemolo.repositorios;



import ideas.pizzeriaRemolo.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;



   public interface FotoRepositorio extends JpaRepository<Foto, String> {
}
