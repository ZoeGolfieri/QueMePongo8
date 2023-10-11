package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Prendas")
public class Prenda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPrenda;
  @Column
  private String nombre;
  @Enumerated
  private Color color;

  public Prenda() {
  }

  public Prenda(String nombre, Color color) {
    super();
    this.nombre = nombre;
    this.color = color;
  }
  public Long getId() {
    return idPrenda;
  }
  public Color getColor() {
    return color;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setColor(Color color) {
    this.color = color;
  }

}
