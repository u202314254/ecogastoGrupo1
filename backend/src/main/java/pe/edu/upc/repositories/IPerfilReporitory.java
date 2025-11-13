package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entities.Perfil;
@Repository

public interface IPerfilReporitory extends JpaRepository<Perfil,Integer> {
}
