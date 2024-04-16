package ar.edu.unju.edm.service;

import java.util.List;


import ar.edu.unju.edm.model.Usuario;

public interface IUsuarioService {
  public Usuario guardarUsuario(Usuario usuario);

  // Método para obtener todos los usuarios
  public List<Usuario> obtenerTodosLosUsuarios();

  // Método para obtener un usuario por su DNI
  public Usuario buscarUsuario(Long id) throws Exception;

  // Método para eliminar un usuario por su DNI
  public void eliminarUsuario(Long dni);
}
