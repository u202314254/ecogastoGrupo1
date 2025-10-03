package pe.edu.upc.serviceinterfaces;

import pe.edu.upc.entities.Notificacion;

import java.util.List;

public interface INotificacionService {
    public List<Notificacion> list();
    public void insert(Notificacion notificacion);
    public Notificacion listId(int id);
    public void delete(int id);
    public void update(Notificacion notificacion);
    public List<String[]> NotificacionesNoLeido();
    List<String[]> obtenerNotificacionesPorUsuario();
}
