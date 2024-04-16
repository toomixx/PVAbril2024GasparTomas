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

import ar.edu.unju.edm.model.Cancha;
import ar.edu.unju.edm.service.ICanchaService;

@Controller
public class CanchaController {
  @Autowired
  private ICanchaService canchaService;

  @GetMapping("/gestion_canchas")
  public ModelAndView mostrarGestionCanchas() {
      ModelAndView modelAndView = new ModelAndView("gestion_canchas");
      List<Cancha> canchas = canchaService.obtenerTodasLasCanchas();
      modelAndView.addObject("canchas", canchas);
      return modelAndView;
  }

  @GetMapping("/eliminar_cancha")
  public ModelAndView eliminarCancha(@RequestParam Long codigo) {
      ModelAndView modelAndView = new ModelAndView("redirect:/gestion_canchas");
      canchaService.eliminarCancha(codigo);
      return modelAndView;
  }
  
  @GetMapping("/registro_cancha")
  public ModelAndView mostrarFormularioRegistroCancha() {
    ModelAndView vista=new ModelAndView("registro_cancha");
    vista.addObject("cancha",new Cancha());
    vista.addObject("band",true);
    return vista;
  }

  @PostMapping("/guardar_cancha")
  public ModelAndView guardarCancha(@ModelAttribute("cancha") Cancha cancha) {
      canchaService.guardarCancha(cancha);
      return new ModelAndView("redirect:/gestion_canchas");
  }
  @GetMapping("/modificar_cancha/{codigo}")
  public ModelAndView mostrarFormularioModificar(@PathVariable(name="codigo") Long id) throws Exception {
    Cancha cancha = new Cancha();
    cancha=canchaService.obtenerCanchaPorCodigo(id);
    ModelAndView model = new ModelAndView("registro_cancha");
    model.addObject("cancha", cancha);
    model.addObject("band",false);
    return model;
  }
  @PostMapping("/modificar_cancha")
  public ModelAndView modUser(@ModelAttribute("cancha") Cancha cancha){
    canchaService.guardarCancha(cancha);
    ModelAndView vista = new ModelAndView("gestion_canchas");
    List<Cancha> canchas = canchaService.obtenerTodasLasCanchas();
    vista.addObject("canchas", canchas);
    return vista;
  }
}
