package es.iespuertolacruz.almacen.api;

public class Empresa {
  String cif;
  String nombre;
  String direccion;
  String telefono;
  String correo;

    public Empresa(String cif, String nombre, String direccion, String telefono, String correo) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

  public String getCif() {
    return this.cif;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public String getTelefono() {
    return this.telefono;
  }

  public String getCorreo() {
    return this.correo;
  }
    
}
