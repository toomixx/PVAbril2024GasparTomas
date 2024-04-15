package ar.edu.unju.edm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cancha {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo; // Clave primaria
  
  private Integer capacidad; // Enum: FUTBOL_7, FUTBOL_5

  private Double precio;
    
  private String horario1;
    
  private String horario2;
    
  private String horario3;
    
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

  public Integer getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(Integer capacidad) {
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
}
