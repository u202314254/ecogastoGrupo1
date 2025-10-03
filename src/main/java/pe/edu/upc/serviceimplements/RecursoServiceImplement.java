package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Recurso;
import pe.edu.upc.repositories.IRecursoRepository;
import pe.edu.upc.serviceinterfaces.IRecursoService;

import java.util.List;
@Service
public class RecursoServiceImplement implements IRecursoService {
    @Autowired
    private IRecursoRepository rS;

    @Override
    public List<Recurso> list() {
        return rS.findAll();
    }

    @Override
    public void insert(Recurso recurso) {
        rS.save(recurso);
    }

    @Override
    public Recurso listId(int id) {
        return rS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        rS.deleteById(id);
    }

    @Override
    public void update(Recurso recurso) {
        rS.save(recurso);
    }

    @Override
    public Recurso listId(Integer id) {
        return rS.findById(id).orElse(null);
    }
}
