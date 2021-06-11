package es.iespuertolacruz.almacen.api;

public class Usuario {
    
    String user;
    String password;

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
