package pe.edu.upc.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Recurso")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecurso;

    @Column(name = "nombreRecurso", length = 50, nullable = false)
    private String nombreRecurso;

    public Recurso() {}

    public Recurso(int idRecurso, String nombreRecurso) {
        this.idRecurso = idRecurso;
        this.nombreRecurso = nombreRecurso;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }
}
