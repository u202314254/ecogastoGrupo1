package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Rol;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repositories.IRolRepository;
import pe.edu.upc.repositories.IUsuarioRepository;
import pe.edu.upc.serviceinterfaces.IRolService;

import java.util.List;
@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private IRolRepository rS;

    @Autowired
    private IUsuarioRepository uR;

    @Override
    public List<Rol> list(){return rS.findAll();}

    @Override
    public void insert(Rol rol){rS.save(rol);}

    @Override
    public Rol listId(int id) {
        return rS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        Rol rol = rS.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // 2. Obtenemos al "padre" (Usuario)
        Usuario usuario = rol.getUsuario();

        // 3. Rompemos la relación en la lista del "padre" (Sincronización)
        if (usuario != null) {
            usuario.getRoles().remove(rol);
            // No necesitas un uR.save(usuario) aquí si la relación
            // está marcada como (cascade = CascadeType.ALL)
            // o si la sesión de Hibernate gestiona el cambio.
        }

        // 4. Ahora sí, eliminamos el "hijo"
        rS.delete(rol);
    }

    @Override
    public void update(Rol rol) {
        rS.save(rol);
    }
}
