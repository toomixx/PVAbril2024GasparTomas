package ar.edu.unju.edm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Cancha {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo; // Clave primaria
  
  private String capacidad; // Enum: FUTBOL_7, FUTBOL_5

  private Double precio;
    
  private String horario1;
  private Boolean reserva1=false;
    
  private String horario2;
  private Boolean reserva2=false;
  private String horario3;
  private Boolean reserva3=false;
  
  @OneToMany(mappedBy = "cancha", cascade = CascadeType.ALL)
  private List<Reserva> reservas = new ArrayList<>();

  public Cancha() {
  }

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public String getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(String capacidad) {
    this.capacidad = capacidad;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public String getHorario1() {
    return horario1;
  }

  public void setHorario1(String horario1) {
    this.horario1 = horario1;
  }

  public String getHorario2() {
    return horario2;
  }

  public void setHorario2(String horario2) {
    this.horario2 = horario2;
  }

  public String getHorario3() {
    return horario3;
  }

  public void setHorario3(String horario3) {
    this.horario3 = horario3;
  }

  public List<Reserva> getReservas() {
    return reservas;
  }

  public void setReservas(List<Reserva> reservas) {
    this.reservas = reservas;
  }

  public Boolean getReserva1() {
    return reserva1;
  }

  public void setReserva1(Boolean reserva1) {
    this.reserva1 = reserva1;
  }

  public Boolean getReserva2() {
    return reserva2;
  }

  public void setReserva2(Boolean reserva2) {
    this.reserva2 = reserva2;
  }

  public Boolean getReserva3() {
    return reserva3;
  }

  public void setReserva3(Boolean reserva3) {
    this.reserva3 = reserva3;
  }
}
