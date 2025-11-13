package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.entities.Seguridad;

import java.util.List;

public interface ISeguridadRepository extends JpaRepository<Seguridad, Integer> {
    @Query(value = "select u.id_usuario, u.correo,\n" +
            "s.ultimo_login, s.ip_dispositivos,\n" +
            "s.intentos_fallidos\n" +
            "from usuario u join seguridad s \n" +
            "on u.id_usuario = s.id_usuario\n" +
            "where s.intentos_fallidos >= 3\n" +
            "order by s.intentos_fallidos desc", nativeQuery = true)
    public List<String[]> listarAccesos();
}
