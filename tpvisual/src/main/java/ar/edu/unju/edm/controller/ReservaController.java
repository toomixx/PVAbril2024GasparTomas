package ar.edu.unju.edm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Cancha;
import ar.edu.unju.edm.model.Reserva;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.ICanchaService;
import ar.edu.unju.edm.service.IReservaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class ReservaController {
    @Autowired
    private IReservaService reservaService;

    @GetMapping("/gestion_reservas_admin")
    public ModelAndView mostrarGestionReservasAdmin() {
        ModelAndView modelAndView = new ModelAndView("gestion_reservas_admin");
        List<Reserva> reservas = reservaService.obtenerTodasLasReservas();
        modelAndView.addObject("reservas", reservas);
        return modelAndView;
    }

    @GetMapping("/eliminar_reserva")
    public ModelAndView eliminarReserva(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/gestion_reservas_admin");

        // Obtener la reserva a eliminar
        Reserva reserva = reservaService.obtenerReservaPorId(id);

        // Obtener la cancha asociada a la reserva
        Cancha cancha = reserva.getCancha();

        // Actualizar el estado de reserva de la cancha a false
        switch (reserva.getHorario()) {
            case "1":
                cancha.setReserva1(false);
                break;
            case "2":
                cancha.setReserva2(false);
                break;
            case "3":
                cancha.setReserva3(false);
                break;
        }

        // Guardar la actualización de la cancha
        canchaService.guardarCancha(cancha);

        // Eliminar la reserva
        reservaService.eliminarReserva(id);

        return modelAndView;
    }

    @GetMapping("/gestion_reservas_visitante")
    public ModelAndView mostrarGestionReservasVisitante() throws NumberFormatException, Exception {
        ModelAndView modelAndView = new ModelAndView("gestion_reservas_visitante");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName();
        Usuario usuario=usuarioService.buscarUsuario(Long.parseLong(dni));
        List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(usuario);
        modelAndView.addObject("reservas", reservas);
        return modelAndView;
    }

    @GetMapping("/eliminar_reserva_visitante")
    public ModelAndView eliminarReservaVisitante(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/gestion_reservas_visitante");

        // Obtener la reserva a eliminar
        Reserva reserva = reservaService.obtenerReservaPorId(id);

        // Obtener la cancha asociada a la reserva
        Cancha cancha = reserva.getCancha();

        // Actualizar el estado de reserva de la cancha a false
        switch (reserva.getHorario()) {
            case "1":
                cancha.setReserva1(false);
                break;
            case "2":
                cancha.setReserva2(false);
                break;
            case "3":
                cancha.setReserva3(false);
                break;
        }

        // Guardar la actualización de la cancha
        canchaService.guardarCancha(cancha);

        // Eliminar la reserva
        reservaService.eliminarReserva(id);
        return modelAndView;
    }

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICanchaService canchaService;

    @GetMapping("/seleccion_usuario_cancha")
    public ModelAndView mostrarFormularioSeleccionUsuarioCancha() {
        ModelAndView modelAndView = new ModelAndView("seleccion_usuario_cancha");
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        List<Cancha> canchas = canchaService.obtenerTodasLasCanchas();
        modelAndView.addObject("usuarios", usuarios);
        modelAndView.addObject("canchas", canchas);
        return modelAndView;
    }

    @PostMapping("/seleccion_horario")
    public ModelAndView mostrarHorariosDisponibles(@RequestParam Long dni, @RequestParam Long codigo) throws Exception {
        ModelAndView modelAndView = new ModelAndView("horarios_disponibles");
        Usuario usuario = usuarioService.buscarUsuario(dni);
        Cancha cancha = canchaService.obtenerCanchaPorCodigo(codigo);
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("cancha", cancha);
        return modelAndView;
    }

    @PostMapping("/guardar_reserva")
    public String guardarReserva(@RequestParam Long dni, @RequestParam Long codigo, @RequestParam String horario)
            throws Exception {
        // Buscar el usuario y la cancha por sus respectivos códigos
        Usuario usuario = usuarioService.buscarUsuario(dni);
        Cancha cancha = canchaService.obtenerCanchaPorCodigo(codigo);

        // Crear y guardar la reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setCancha(cancha);
        
        

        // Actualizar el estado de reserva de la cancha
        switch (horario) {
            case "1":
                reserva.setHorario(cancha.getHorario1());
                cancha.setReserva1(true);
                break;
            case "2":
                reserva.setHorario(cancha.getHorario2());
                cancha.setReserva2(true);
                break;
            case "3":
                reserva.setHorario(cancha.getHorario3());
                cancha.setReserva3(true);
                break;
        }
        reservaService.guardarReserva(reserva);
        canchaService.guardarCancha(cancha);

        return "redirect:/gestion_reservas_admin";
    }

    @GetMapping("/seleccion_cancha")
    public ModelAndView mostrarFormularioSeleccionCancha() {
        ModelAndView modelAndView = new ModelAndView("seleccion_cancha");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName(); // Aquí asumo que el username del usuario autenticado es su dni
        modelAndView.addObject("dni", dni);
        List<Cancha> canchas = canchaService.obtenerTodasLasCanchas();
        modelAndView.addObject("canchas", canchas);
        return modelAndView;
    }
    @PostMapping("/seleccion_horario_visitante")
    public ModelAndView mostrarHorariosDisponibles(@RequestParam Long codigo) throws Exception {
        ModelAndView modelAndView = new ModelAndView("horarios_disponibles_visitante");
        
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName(); 
        
        Usuario usuario = usuarioService.buscarUsuario(Long.parseLong(dni));
        Cancha cancha = canchaService.obtenerCanchaPorCodigo(codigo);
        
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("cancha", cancha);
        
        return modelAndView;
    }

    @PostMapping("/guardar_reserva_visitante")
    public String guardarReservaVisitante(@RequestParam Long dni, @RequestParam Long codigo, @RequestParam String horario)
            throws Exception {
        // Buscar el usuario y la cancha por sus respectivos códigos
        Usuario usuario = usuarioService.buscarUsuario(dni);
        Cancha cancha = canchaService.obtenerCanchaPorCodigo(codigo);

        // Crear y guardar la reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setCancha(cancha);
        reservaService.guardarReserva(reserva);

        // Actualizar el estado de reserva de la cancha
        switch (horario) {
            case "1":
                reserva.setHorario(cancha.getHorario1());
                cancha.setReserva1(true);
                break;
            case "2":
                reserva.setHorario(cancha.getHorario2());
                cancha.setReserva2(true);
                break;
            case "3":
                reserva.setHorario(cancha.getHorario3());
                cancha.setReserva3(true);
                break;
        }
        canchaService.guardarCancha(cancha);

        return "redirect:/gestion_reservas_visitante";
    }
}
