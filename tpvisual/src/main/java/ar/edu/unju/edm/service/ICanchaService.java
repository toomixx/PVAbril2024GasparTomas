package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Cancha;

public interface ICanchaService {
  public Cancha guardarCancha(Cancha cancha);

  // Método para obtener todas las canchas
  public List<Cancha> obtenerTodasLasCanchas();

  // Método para obtener una cancha por su código
  public Cancha obtenerCanchaPorCodigo(Long codigo);

  // Método para eliminar una cancha por su código
  public void eliminarCancha(Long codigo);
}
