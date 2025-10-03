package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entities.Hogar;
@Repository
public interface IHogarRepository extends JpaRepository<Hogar,Integer> {
}
