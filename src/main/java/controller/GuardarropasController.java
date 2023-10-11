package controller;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import model.Guardarropa;
import model.RepositorioGuardarropas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GuardarropasController implements WithSimplePersistenceUnit {
  public ModelAndView listar(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("anio", LocalDate.now().getYear());
    modelo.put("guardarropas", RepositorioGuardarropas.instance().obtenerTodos());
    return new ModelAndView(modelo, "guardarropas.html.hbs");
  }
  public ModelAndView buscar(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    Guardarropa guardarropa = RepositorioGuardarropas.instance().buscar(Long.parseLong(request.params("id")));
    modelo.put("prendas", guardarropa.getPrendas());
    modelo.put("id", guardarropa.getId());
    return new ModelAndView(modelo, "modeloGuardarropas.html.hbs");
  }
}
