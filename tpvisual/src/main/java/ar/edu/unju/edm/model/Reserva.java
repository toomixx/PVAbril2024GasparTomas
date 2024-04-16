package ar.edu.unju.edm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Reserva {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "usuario_dni", referencedColumnName = "dni")
  private Usuario usuario;
  
  @ManyToOne
  @JoinColumn(name = "cancha_codigo", referencedColumnName = "codigo")
  private Cancha cancha;
  
  private String horario;

  public Reserva() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Cancha getCancha() {
    return cancha;
  }

  public void setCancha(Cancha cancha) {
    this.cancha = cancha;
  }

  public String getHorario() {
    return horario;
  }

  public void setHorario(String horario) {
    this.horario = horario;
  }
}
