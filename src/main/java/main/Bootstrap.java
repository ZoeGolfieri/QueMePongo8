package main;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import model.Color;
import model.Guardarropa;
import model.Prenda;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
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
      var usuario = new Usuario("franco", "flbulgarelli@gmail.com", "123456");
      persist(usuario);
      Prenda prenda1= new Prenda("Pantalon", Color.AZUL);
      persist(prenda1);
      Prenda prenda2= new Prenda("Remera", Color.AZUL);
      persist(prenda2);
      Prenda prenda3= new Prenda("Remera", Color.AZUL);
      persist(prenda3);
      List<Prenda> prendas= new ArrayList<>();
      Guardarropa guardarropa1 = new Guardarropa("Chau", prendas);
      Guardarropa guardarropa2 = new Guardarropa("Hola", prendas);

      prendas.add(prenda1);
      prendas.add(prenda2);
      prendas.add(prenda3);

      persist(guardarropa1);
      persist(guardarropa2);



    });
  }

}
