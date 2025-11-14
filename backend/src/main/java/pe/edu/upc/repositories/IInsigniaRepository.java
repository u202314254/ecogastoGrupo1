package pe.edu.upc.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entities.Insignia;

import java.util.List;

@Repository
public interface IInsigniaRepository extends JpaRepository<Insignia,Integer> {
    @Query(value = "SELECT i.nombre_logro, i.puntos\n" +
            "FROM insignia i\n" +
            "JOIN meta m ON i.id_meta = m.id_meta\n" +
            "WHERE m.id_usuario = m.id_usuario;", nativeQuery = true)
    List<String[]> obtenerInsigniasPorUsuario();

    @Query(value = "SELECT COUNT(i.id_insignia) AS cantidad_insignias\n" +
            "FROM usuario u\n" +
            "JOIN meta m ON u.id_usuario = m.id_usuario\n" +
            "JOIN insignia i ON m.id_meta = i.id_meta\n" +
            "WHERE u.id_usuario = u.id_usuario;", nativeQuery = true)
    List<String[]> contarInsigniasPorUsuario();

    @Query(value = "SELECT i.id_insignia,\n" +
            "       i.nombre_logro,\n" +
            "       i.puntos,\n" +
            "       m.descripcion AS meta_descripcion\n" +
            "FROM insignia i\n" +
            "JOIN meta m ON i.id_meta = m.id_meta\n" +
            "WHERE m.id_meta = m.id_meta;", nativeQuery = true)
    List<String[]> findInsigniasByMeta();
}
