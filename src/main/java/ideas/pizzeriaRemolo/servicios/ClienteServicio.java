
package ideas.pizzeriaRemolo.servicios;

import ideas.pizzeriaRemolo.entidades.Cliente;
import ideas.pizzeriaRemolo.errores.ErrorServicio;
import ideas.pizzeriaRemolo.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    public Cliente registrar(String nombre, Long telefono) throws ErrorServicio {

        validar(nombre, telefono);

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);

        cliente.setTelefono(telefono);

        return clienteRepositorio.save(cliente);
    }
  
    @Transactional
  public void validar(String nombre,Long telefono) throws ErrorServicio {
     
     if (nombre.isEmpty()  ) {
      throw new ErrorServicio(" El nombre no puede estar vacío");
    }
   
    if (telefono== null) {
      throw new ErrorServicio(" El telefono no puede estar vacío");
    }
   
  }
}
