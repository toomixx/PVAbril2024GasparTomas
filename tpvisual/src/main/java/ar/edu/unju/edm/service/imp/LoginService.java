package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;


@Service
public class LoginService implements UserDetailsService{
  @Autowired
  UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
    //busqueda del usuario
    System.out.println(dni);
    Usuario usuarioenc = usuarioRepository.findById(Long.parseLong(dni)).orElseThrow(()-> new UsernameNotFoundException("Usuario invalido"));
    //definir autorizaciones
    List<GrantedAuthority> estados= new ArrayList<>();
    GrantedAuthority grantedAuthority= new SimpleGrantedAuthority(usuarioenc.getTipo());
    estados.add(grantedAuthority);
    //Definir usuario en sesion
    UserDetails userEnSesion = new User(dni,usuarioenc.getContraseña(),estados);
    return userEnSesion;
  }
  
}
