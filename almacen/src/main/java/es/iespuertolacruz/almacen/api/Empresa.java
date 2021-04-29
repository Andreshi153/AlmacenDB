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
}
