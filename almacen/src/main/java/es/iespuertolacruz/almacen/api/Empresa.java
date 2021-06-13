package es.iespuertolacruz.almacen.api;

public class Empresa {
  String cif;
  String nombre;
  String direccion;
  String telefono;
  String correo;

  /**
   * Constructor de la clase
   * @param cif de la empresa
   * @param nombre de la empresa
   * @param direccion de la empresa
   * @param telefono de la empresa
   * @param correo de la empresa
   */
  public Empresa(String cif, String nombre, String direccion, String telefono, String correo) {
    this.cif = cif;
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
    this.correo = correo;
  }

  /**
   * Funcion getter del cif
   * @return cif de la empresa
   */
  public String getCif() {
    return this.cif;
  }

  /**
   * Funcion getter del nombre
   * @return nombre de la empresa
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * Funcion getter de la direccion
   * @return direccion de la empresa
   */
  public String getDireccion() {
    return this.direccion;
  }

  /**
   * Funcion getter del telefono
   * @return telefono de la empresa
   */
  public String getTelefono() {
    return this.telefono;
  }

  /**
   * Funcion getter del correo
   * @return correo de la empresa
   */
  public String getCorreo() {
    return this.correo;
  }

  @Override
  /**
   * Funcion toString de la empresa
   * @return informacion de la empresa
   */
  public String toString() {
    return "# CIF: " + this.cif + "\n# Nombre: " + this.nombre
    + "\n# Direccion: " + this.direccion + "\n# Telefono: " + this.telefono
    + "\n# Correo: " + this.correo;
  }

}
