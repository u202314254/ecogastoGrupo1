package pe.edu.upc.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "perfil")

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerfil;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "genero", length = 50, nullable = false)
    private String genero;

    @Column(name = "telefono", length = 50, nullable = false)
    private String telefono;
    public Perfil(){}

    public Perfil(int idPerfil, Usuario usuario, String nombre, int edad, String genero, String telefono) {
        this.idPerfil = idPerfil;
        this.usuario = usuario;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
