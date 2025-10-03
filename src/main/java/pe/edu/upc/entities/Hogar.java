package pe.edu.upc.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Hogar")
public class Hogar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHogar;

    @Column(name = "distrito", length = 50, nullable = false)
    private String distrito;

    @Column(name = "ubicacion", length = 100, nullable = false)
    private String ubicacion;

    @Column(name = "tipohogar", length = 50, nullable = false)
    private String tipohogar;

    @Column(name = "numpersonas", nullable = false)
    private int numpersonas;

    public Hogar() {}

    public Hogar(int idHogar, String distrito, String ubicacion, String tipohogar, int numpersonas) {
        this.idHogar = idHogar;
        this.distrito = distrito;
        this.ubicacion = ubicacion;
        this.tipohogar = tipohogar;
        this.numpersonas = numpersonas;
    }

    public int getIdHogar() {
        return idHogar;
    }

    public void setIdHogar(int idHogar) {
        this.idHogar = idHogar;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipohogar() {
        return tipohogar;
    }

    public void setTipohogar(String tipohogar) {
        this.tipohogar = tipohogar;
    }

    public int getNumpersonas() {
        return numpersonas;
    }

    public void setNumpersonas(int numpersonas) {
        this.numpersonas = numpersonas;
    }

}
