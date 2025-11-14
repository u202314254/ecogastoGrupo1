package pe.edu.upc.dtos;
import pe.edu.upc.entities.Usuario;
import java.time.LocalDateTime;

public class SeguridadDTO {
    private int idSeguridad;
    private Usuario usuario;
    private LocalDateTime ultimoLogin;
    private String ipDispositivos;
    private int intentosFallidos;

    public int getIdSeguridad() {
        return idSeguridad;
    }

    public void setIdSeguridad(int idSeguridad) {
        this.idSeguridad = idSeguridad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public String getIpDispositivos() {
        return ipDispositivos;
    }

    public void setIpDispositivos(String ipDispositivos) {
        this.ipDispositivos = ipDispositivos;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }
}