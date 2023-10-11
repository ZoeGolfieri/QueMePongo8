package main;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import model.Color;
import model.Guardarropa;
import model.Prenda;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejecutar antes de levantar el servidor por primera vez
 *
 * @author flbulgarelli
 */
public class Bootstrap implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      Prenda prenda1= new Prenda("Pantalon", Color.AZUL);
      Prenda prenda2= new Prenda("Remera", Color.AZUL);
      List<Prenda> prendas= new ArrayList<>();
      List<Prenda> prendas2= new ArrayList<>();
      prendas.add(prenda1);
      prendas.add(prenda2);
      prendas2.add(prenda1);
      prendas2.add(prenda2);
      persist(prenda1);
      persist(prenda2);

      persist(new Guardarropa("Hola", prendas));
      persist(new Guardarropa("Chau", prendas2));


    });
  }

}
