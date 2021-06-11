package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.Usuario;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.UsuarioModelo;

public class UsuarioController {
    
    UsuarioModelo usuarioModelo;

    public UsuarioController() throws BbddException, FicheroException {
        usuarioModelo = new UsuarioModelo();
    }

    public boolean validarUsuario(String usuario, String password) throws BbddException {
        Usuario usuarioEncontrado = usuarioModelo.buscar(usuario);
        if(usuarioEncontrado == null) return false;
        else return (password.equals(usuarioEncontrado.getPassword()));
    }
}
