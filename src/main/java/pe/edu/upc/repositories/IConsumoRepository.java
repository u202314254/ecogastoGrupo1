package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entities.Consumo;

import java.util.List;

@Repository

public interface IConsumoRepository extends JpaRepository<Consumo, Integer> {
    @Query(value = "SELECT r.nombre_recurso AS recurso, SUM(c.costo) AS total\n" +
            "FROM consumo c\n" +
            "JOIN recurso r ON c.id_recurso = r.id_recurso\n" +
            "GROUP BY r.nombre_recurso;", nativeQuery = true)
    List<String[]> obtenerGastosPorRecurso();
}
