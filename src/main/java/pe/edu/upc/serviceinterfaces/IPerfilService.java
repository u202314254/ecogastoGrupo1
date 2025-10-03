package pe.edu.upc.serviceinterfaces;

import pe.edu.upc.entities.Perfil;

import java.util.List;

public interface IPerfilService {
    public List<Perfil> list();
    public void insert(Perfil perfil);
    public Perfil listId(int id);
    public void delete(int id);
    public void update(Perfil perfil);
    Perfil listId(Integer id);
}
