package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cancha;
import ar.edu.unju.edm.repository.CanchaRepository;
import ar.edu.unju.edm.service.ICanchaService;

@Service
public class ICanchaServiceImp implements ICanchaService {
  @Autowired
  private CanchaRepository canchaRepository;

  // Método para crear o actualizar una cancha
  public Cancha guardarCancha(Cancha cancha) {
    return canchaRepository.save(cancha);
  }

  // Método para obtener todas las canchas
  public List<Cancha> obtenerTodasLasCanchas() {
    return (List<Cancha>) canchaRepository.findAll();
  }

  // Método para obtener una cancha por su código
  public Cancha obtenerCanchaPorCodigo(Long codigo) {
    Cancha canchaEncontrada = new Cancha();
    canchaEncontrada=canchaRepository.findById(codigo).orElse(null);
    return canchaEncontrada;
  }

  // Método para eliminar una cancha por su código
  public void eliminarCancha(Long codigo) {
    canchaRepository.deleteById(codigo);
  }
}
