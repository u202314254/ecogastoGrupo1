package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Seguridad;
import pe.edu.upc.repositories.ISeguridadRepository;
import pe.edu.upc.serviceinterfaces.ISeguridadService;

import java.util.List;
@Service
public class SeguridadServiceImplement implements ISeguridadService {

    @Autowired
    private ISeguridadRepository sS;

    @Override
    public List<Seguridad> list() {
        return sS.findAll();
    }

    @Override
    public void insert(Seguridad seguridad) {
        sS.save(seguridad);

    }

    @Override
    public Seguridad listId(int id) {
        return sS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        sS.deleteById(id);

    }

    @Override
    public void update(Seguridad seguridad) {
        sS.save(seguridad);

    }

    @Override
    public Seguridad listId(Integer id) {
        return sS.findById(id).orElse(null);
    }

    @Override
    public List<String[]> listarAccesos() {
        return sS.listarAccesos();
    }


}