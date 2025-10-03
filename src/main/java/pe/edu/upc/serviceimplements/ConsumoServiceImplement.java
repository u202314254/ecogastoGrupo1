package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Consumo;
import pe.edu.upc.repositories.IConsumoRepository;
import pe.edu.upc.serviceinterfaces.IConsumoService;

import java.util.List;
@Service
public class ConsumoServiceImplement implements IConsumoService {
    @Autowired
    private IConsumoRepository cS;

    @Override
    public List<Consumo> list(){return cS.findAll(); }

    @Override
    public void insert(Consumo consumo){cS.save(consumo);}

    @Override
    public Consumo listId(int id) {
        return cS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        cS.deleteById(id);
    }

    @Override
    public void update(Consumo consumo) {
        cS.save(consumo);
    }

    @Override
    public List<String[]> obtenerGastosPorRecurso() {
        return cS.obtenerGastosPorRecurso();
    }
}
