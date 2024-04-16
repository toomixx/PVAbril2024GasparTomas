package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Reserva;
import ar.edu.unju.edm.model.Usuario;

public interface IReservaService {
  public Reserva guardarReserva(Reserva reserva);

  // Método para obtener todas las reservas
  public List<Reserva> obtenerTodasLasReservas();

  // Método para obtener una reserva por su ID
  public Reserva obtenerReservaPorId(Long id);

  // Método para eliminar una reserva por su ID
  public void eliminarReserva(Long id);

  // Método para eliminar una reserva de un usuario por su ID y DNI
  public void eliminarReservaDeUsuario(Long dniUsuario, Long idReserva);
  public List<Reserva> obtenerReservasPorUsuario(Usuario usuario);

}
