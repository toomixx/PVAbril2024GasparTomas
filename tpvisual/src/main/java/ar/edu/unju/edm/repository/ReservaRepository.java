package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Reserva;
import ar.edu.unju.edm.model.Usuario;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva,Long>{

  void deleteByIdAndUsuarioDni(Long idReserva, Long dniUsuario);
  List<Reserva> findByUsuario(Usuario usuario);
  Reserva findByIdAndUsuario(Long id, Usuario usuario);
}
