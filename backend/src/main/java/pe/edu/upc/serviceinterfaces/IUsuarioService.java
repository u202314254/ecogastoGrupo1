package pe.edu.upc.serviceinterfaces;

import pe.edu.upc.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> list();
    public void insert(Usuario usuario);
    public Usuario listId(int id);
    public void delete(int id);
    public void update(Usuario usuario);
    Usuario listId(Integer id);
    public List<String[]> listarUsuariosActivos();
}
