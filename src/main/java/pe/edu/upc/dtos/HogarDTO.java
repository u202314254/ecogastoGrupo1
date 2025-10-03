package pe.edu.upc.dtos;

public class HogarDTO {
    private int idHogar;
    private String distrito;
    private String ubicacion;
    private String tipohogar;
    private int numpersonas;

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
