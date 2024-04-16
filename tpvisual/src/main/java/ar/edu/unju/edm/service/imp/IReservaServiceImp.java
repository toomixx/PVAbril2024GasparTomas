package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Reserva;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.ReservaRepository;
import ar.edu.unju.edm.service.IReservaService;

@Service
public class IReservaServiceImp implements IReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    // Método para crear o actualizar una reserva
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Método para obtener todas las reservas
    public List<Reserva> obtenerTodasLasReservas() {
        return (List<Reserva>) reservaRepository.findAll();
    }

    // Método para obtener una reserva por su ID
    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    // Método para eliminar una reserva por su ID
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    // Método para eliminar una reserva de un usuario por su ID y DNI
    public void eliminarReservaDeUsuario(Long dniUsuario, Long idReserva) {
        reservaRepository.deleteByIdAndUsuarioDni(idReserva, dniUsuario);
    }

    public List<Reserva> obtenerReservasPorUsuario(Usuario usuario) {
        return reservaRepository.findByUsuario(usuario);
    }
}
