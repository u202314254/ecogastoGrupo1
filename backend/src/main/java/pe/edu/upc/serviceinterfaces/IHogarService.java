package pe.edu.upc.serviceinterfaces;

import pe.edu.upc.entities.Hogar;

import java.util.List;

public interface IHogarService {
    public List<Hogar> list();
    public void insert(Hogar hogar);
    public Hogar listId(int id);
    public void delete(int id);
    public void update(Hogar hogar);
}
