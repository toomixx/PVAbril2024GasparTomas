package ar.edu.unju.edm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {

  @Autowired
  IUsuarioService usuarioService;

  @GetMapping({ "/index", "/home", "/", "/login" })
  public ModelAndView getInicio() {
    Usuario usuario = new Usuario();
    ModelAndView model = new ModelAndView("index");
    model.addObject("usuario", usuario);
    return model;
  }

  @GetMapping("/registro")
  public ModelAndView mostrarFormularioRegistro() {
    Usuario usuario = new Usuario();
    ModelAndView model = new ModelAndView("registro");
    model.addObject("usuario", usuario);
    model.addObject("band",true);
    return model;
  }

  @PostMapping("/registro")
  public ModelAndView registrarUsuario(@ModelAttribute Usuario usuario, @RequestParam String confirmPassword) {
    ModelAndView modelAndView = new ModelAndView();

    if (!usuario.getContraseña().equals(confirmPassword)) {
      modelAndView.setViewName("registro");
      modelAndView.addObject("error", "Las contraseñas no coinciden");
      return modelAndView;
    }
    // Aquí puedes implementar la lógica para guardar el usuario en la base de datos
    usuarioService.guardarUsuario(usuario);

    modelAndView.setViewName("redirect:/");
    return modelAndView;
  }

  @GetMapping("/admin")
  public ModelAndView mostrarHomeAdmin() {
    return new ModelAndView("home-admin");
  }

  @GetMapping("/visitante")
  public ModelAndView mostrarHomeVisitante() {
    return new ModelAndView("home-visitante");
  }

  @GetMapping("/gestion_usuarios")
  public ModelAndView mostrarGestionUsuarios() {
    ModelAndView modelAndView = new ModelAndView("gestion_usuarios");
    List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
    modelAndView.addObject("usuarios", usuarios);
    return modelAndView;
  }
  @GetMapping("/modificar/{dni}")
  public ModelAndView mostrarFormularioModificar(@PathVariable(name="dni") Long id) throws Exception {
    Usuario usuario = new Usuario();
    usuario=usuarioService.buscarUsuario(id);
    ModelAndView model = new ModelAndView("registro");
    model.addObject("usuario", usuario);
    model.addObject("band",false);
    return model;
  }
  @PostMapping("/modificar")
  public ModelAndView modUser(@ModelAttribute("usuario") Usuario user){
    usuarioService.guardarUsuario(user);
    ModelAndView vista = new ModelAndView("gestion_usuarios");
    List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
    vista.addObject("usuarios", usuarios);
    return vista;
  }
  @GetMapping("/eliminar_usuario")
    public ModelAndView eliminarUsuario(@RequestParam Long dni) {
        ModelAndView modelAndView = new ModelAndView("redirect:/gestion_usuarios");
        usuarioService.eliminarUsuario(dni);
        return modelAndView;
    }
}
