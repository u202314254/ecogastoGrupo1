package pe.edu.upc.serviceinterfaces;

import pe.edu.upc.entities.Insignia;

import java.util.List;

public interface IInsigniaService {
    public List<Insignia> list();
    public void insert(Insignia insignia);
    public Insignia listId(int id);
    public void delete(int id);
    public void update(Insignia insignia);
    List<String[]> obtenerInsigniasPorUsuario();
    List<String[]> contarInsigniasPorUsuario();
    List<String[]> findInsigniasByMeta();
}