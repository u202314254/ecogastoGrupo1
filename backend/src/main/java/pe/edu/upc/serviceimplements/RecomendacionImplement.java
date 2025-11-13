package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dtos.RecomendacionDTOList;
import pe.edu.upc.entities.Recomendacion;
import pe.edu.upc.repositories.IRecomendacionRepository;
import pe.edu.upc.serviceinterfaces.IRecomendacionService;

import java.util.List;
@Service
public class RecomendacionImplement implements IRecomendacionService {
    @Autowired
    private IRecomendacionRepository rS;

    @Override
    public List<Recomendacion> list(){return rS.findAll();}

    @Override
    public void insert(Recomendacion recomendacion){rS.save(recomendacion);}

    @Override
    public Recomendacion listId(int id) {
        return rS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        rS.deleteById(id);
    }

    @Override
    public void update(Recomendacion recomendacion) {
        rS.save(recomendacion);
    }

    @Override
    public List<String[]> findRecomendacionesPorRecurso() {
        return rS.findRecomendacionesPorRecurso();
    }

    @Override
    public List<RecomendacionDTOList> buscarPorCategoria(String categoria) {
        return rS.buscarPorCategoria(categoria);
    }
}
