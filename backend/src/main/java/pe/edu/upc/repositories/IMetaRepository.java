package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entities.Meta;

import java.util.List;

@Repository

public interface IMetaRepository extends JpaRepository<Meta, Integer> {
    @Query(value = "SELECT m.id_meta,\n" +
            "       m.descripcion,\n" +
            "       m.fechainicio,\n" +
            "       m.fechafin,\n" +
            "       m.estado\n" +
            "FROM meta m\n" +
            "WHERE m.id_usuario = m.id_usuario\n" +
            "  AND m.estado = 'Incompleta';", nativeQuery = true)
    List<String[]> findMetasActivasByUsuario();

    @Query(value = "SELECT u.correo AS usuario, COUNT(m.id_meta) AS total_metas " +
            "FROM usuario u " +
            "JOIN Meta m ON u.id_usuario = m.id_usuario " +
            "GROUP BY u.correo",
            nativeQuery = true)
    List<String[]> obtenerMetasPorUsuario();
}
