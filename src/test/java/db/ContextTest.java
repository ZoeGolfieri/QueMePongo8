package db;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContextTest implements SimplePersistenceTest {

  @Test
  void contextUp() {
    var pantalon = new Prenda("Pantalón negro", Color.NEGRO);
    List<Prenda> prenda = new ArrayList<>();
    var guardarropa = new Guardarropa("primavera", prenda);
    guardarropa.agregarPrenda(pantalon);

    var usuario = new Usuario("dani", "dani@dani.com");
    usuario.agregarGuardarropa(guardarropa);

    persist(usuario);


    var guardarropas = RepositorioGuardarropas.instance().obtenerTodos();
    assertEquals(1, guardarropas.size());
  }


}
