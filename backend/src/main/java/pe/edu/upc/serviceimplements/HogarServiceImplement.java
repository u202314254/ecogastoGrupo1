package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Hogar;
import pe.edu.upc.repositories.IHogarRepository;
import pe.edu.upc.serviceinterfaces.IHogarService;

import java.util.List;
@Service
public class HogarServiceImplement implements IHogarService {
    @Autowired
    private IHogarRepository hS;

    @Override
    public List<Hogar> list() {
        return hS.findAll();
    }

    @Override
    public void insert(Hogar hogar) {
        hS.save(hogar);
    }

    @Override
    public Hogar listId(int id) {
        return hS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        hS.deleteById(id);
    }

    @Override
    public void update(Hogar hogar) {
        hS.save(hogar);
    }
}
