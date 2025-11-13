package pe.edu.upc.dtos;

import java.time.LocalDateTime;

public class AccesosSeguridadDTO {
    private int id_usuario;
    private String correo;
    private LocalDateTime ultimo_login;
    private String ip;
    private int intentos_fallidos;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDateTime getUltimo_login() {
        return ultimo_login;
    }

    public void setUltimo_login(LocalDateTime ultimo_login) {
        this.ultimo_login = ultimo_login;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getIntentos_fallidos() {
        return intentos_fallidos;
    }

    public void setIntentos_fallidos(int intentos_fallidos) {
        this.intentos_fallidos = intentos_fallidos;
    }
}