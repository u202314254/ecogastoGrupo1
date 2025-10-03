package pe.edu.upc.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.entities.Insignia;
import pe.edu.upc.repositories.IInsigniaRepository;
import pe.edu.upc.serviceinterfaces.IInsigniaService;

import java.util.List;

@Service
public class InsigniaServiceImplement implements IInsigniaService {
    @Autowired
    private IInsigniaRepository iR;

    @Override
    public List<Insignia> list() {
        return iR.findAll();
    }

    @Override
    public void insert(Insignia insignia) {
        iR.save(insignia);
    }

    @Override
    public Insignia listId(int id) {
        return iR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);
    }

    @Override
    public void update(Insignia insignia) {
        iR.save(insignia);
    }

    @Override
    public List<String[]> obtenerInsigniasPorUsuario() {
        return iR.obtenerInsigniasPorUsuario();
    }

    @Override
    public List<String[]> contarInsigniasPorUsuario() {
        return iR.contarInsigniasPorUsuario();
    }

    @Override
    public List<String[]> findInsigniasByMeta() {
        return iR.findInsigniasByMeta();
    }

}