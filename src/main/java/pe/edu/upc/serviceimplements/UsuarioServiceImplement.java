package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repositories.IUsuarioRepository;
import pe.edu.upc.serviceinterfaces.IUsuarioService;

import java.util.List;
@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uS;

    @Override
    public List<Usuario> list() {return uS.findAll(); }

    @Override
    public void insert(Usuario usuario) {uS.save(usuario);}

    @Override
    public Usuario listId(int id) {
        return uS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        uS.deleteById(id);
    }

    @Override
    public void update(Usuario usuario) {
        uS.save(usuario);
    }

    @Override
    public Usuario listId(Integer id) {
        return uS.findById(id).orElse(null);
    }

    @Override
        public List<String[]> listarUsuariosActivos() {
        return uS.listarUsuariosActivos();
    }

}
