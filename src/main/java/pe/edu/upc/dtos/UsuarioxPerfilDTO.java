package pe.edu.upc.dtos;

public class UsuarioxPerfilDTO {
    private int idUsuario;
    private String nombre;
    private String correo;
    private int edad;
    private String distrito;
    private int num_personas;
    private String tipo_hogar;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(int num_personas) {
        this.num_personas = num_personas;
    }

    public String getTipo_hogar() {
        return tipo_hogar;
    }

    public void setTipo_hogar(String tipo_hogar) {
        this.tipo_hogar = tipo_hogar;
    }
}
