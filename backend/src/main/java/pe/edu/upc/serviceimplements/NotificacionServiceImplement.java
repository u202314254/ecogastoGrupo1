package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Notificacion;
import pe.edu.upc.repositories.INotificacionRepository;
import pe.edu.upc.serviceinterfaces.INotificacionService;

import java.util.List;

@Service
public class NotificacionServiceImplement implements INotificacionService {
    @Autowired
    private INotificacionRepository nR;
    @Override
    public List<Notificacion> list() {
        return nR.findAll();
    }

    @Override
    public void insert(Notificacion notificacion) {
        nR.save(notificacion);
    }

    @Override
    public Notificacion listId(int id) {
        return nR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        nR.deleteById(id);
    }

    @Override
    public void update(Notificacion notificacion) {
        nR.save(notificacion);
    }

    @Override
    public List<String[]> NotificacionesNoLeido() {
        return nR.NotificacionesNoLeido();
    }

    @Override
    public List<String[]> obtenerNotificacionesPorUsuario() {
        return nR.obtenerNotificacionesPorUsuario();
    }
}