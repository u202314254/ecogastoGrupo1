package pe.edu.upc.serviceinterfaces;

import pe.edu.upc.entities.Recurso;

import java.util.List;

public interface IRecursoService {
    public List<Recurso> list();
    public void insert(Recurso recurso);
    public Recurso listId(int id);
    public void delete(int id);
    public void update(Recurso recurso);
    Recurso listId(Integer id);
}
