package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;
@Service
public class IUsuarioServiceImp implements IUsuarioService{

  @Autowired
  UsuarioRepository usuarioRepository;

  public Usuario guardarUsuario(Usuario usuario) {
    String pw = usuario.getContraseña();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
		usuario.setContraseña(encoder.encode(pw));
    return usuarioRepository.save(usuario);
  }

// Método para obtener todos los usuarios
public List<Usuario> obtenerTodosLosUsuarios() {
    return (List<Usuario>) usuarioRepository.findAll();
}

// Método para obtener un usuario por su DNI
public Usuario buscarUsuario(Long id) throws Exception {
  Usuario usuarioEncontrado = new Usuario();
  usuarioEncontrado=usuarioRepository.findById(id).orElse(null);
  return usuarioEncontrado;
}

// Método para eliminar un usuario por su DNI
public void eliminarUsuario(Long dni) {
    usuarioRepository.deleteById(dni);
}
  
} 
