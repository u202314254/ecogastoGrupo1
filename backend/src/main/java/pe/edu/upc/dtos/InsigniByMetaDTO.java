package pe.edu.upc.dtos;

public class InsigniByMetaDTO {
    private int idInsignia;
    private String nombreLogro;
    private String tipo;
    private int puntos;
    private String metaDescripcion;

    public int getIdInsignia() {
        return idInsignia;
    }

    public void setIdInsignia(int idInsignia) {
        this.idInsignia = idInsignia;
    }

    public String getNombreLogro() {
        return nombreLogro;
    }

    public void setNombreLogro(String nombreLogro) {
        this.nombreLogro = nombreLogro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getMetaDescripcion() {
        return metaDescripcion;
    }

    public void setMetaDescripcion(String metaDescripcion) {
        this.metaDescripcion = metaDescripcion;
    }
}
