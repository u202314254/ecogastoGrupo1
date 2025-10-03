package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.entities.Usuario;

import java.util.List;

@Repository

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Usuario findOneByUsername(String username);

    @Query("select count(u.username) from Usuario u where u.username =u.username")
    public int buscarUsername(@Param("username") String nombre);

    @Transactional
    @Modifying
    @Query(value = "insert into rol (tipo, id_usuario) VALUES (:tipo, :id_usuario)", nativeQuery = true)
    public void insRol(@Param("tipo") String authority, @Param("id_usuario") int id_usuario);

    @Query(value = "select u.id_usuario, u.correo,\n" +
            "p.nombre, p.edad, p.distrito, \n" +
            "p.num_personas, p.tipohogar\n" +
            "from usuario u join perfil p \n" +
            "on u.id_usuario = p.id_usuario\n" +
            "where u.estado = true\n" +
            "order by p.nombre", nativeQuery = true)
    public List<String[]> listarUsuariosActivos();

}
