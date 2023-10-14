package controller;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import model.Color;
import model.Guardarropa;
import model.Prenda;
import model.RepositorioGuardarropas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class PrendasController implements WithSimplePersistenceUnit {
  public ModelAndView buscar(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    Guardarropa guardarropa = RepositorioGuardarropas.instance().buscar(Long.parseLong(request.params("id")));
    modelo.put("idGuardarropa", guardarropa.getId());
    Prenda prenda = guardarropa.buscarPrenda(Long.parseLong(request.params("idPrenda")));
    modelo.put("id", prenda.getId());
    modelo.put("nombre", prenda.getNombre());
    modelo.put("color", prenda.getColor());
    return new ModelAndView(modelo, "modeloPrendas.html.hbs");
  }
  public ModelAndView nueva(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    Guardarropa guardarropa = RepositorioGuardarropas.instance().buscar(Long.parseLong(request.params("id")));
    modelo.put("id", guardarropa.getId());
    return new ModelAndView(modelo, "nuevaPrenda.html.hbs");
  }

  public Void crear(Request request, Response response) {
    withTransaction(() -> {
      Guardarropa guardarropa = RepositorioGuardarropas.instance().buscar(Long.parseLong(request.params("id")));
      Prenda prenda = new Prenda(request.queryParams("nombre"), Color.valueOf(request.queryParams("color")));
      guardarropa.agregarPrenda(prenda);
      entityManager().persist(prenda);
    });
    long idGuardarropa = Long.parseLong(request.params("id"));
    response.redirect("/guardarropas/"+ idGuardarropa);
    return null;
  }

  public ModelAndView borrar(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    withTransaction(() -> {
      Guardarropa guardarropa = RepositorioGuardarropas.instance().buscar(Long.parseLong(request.params("id")));
      System.out.printf("IMPRIMIMOS ID/////////////// %d %n", guardarropa.getId());
      modelo.put("prendas", guardarropa.getPrendas());
      modelo.put("id", guardarropa.getId());
      Prenda prenda = guardarropa.buscarPrenda(Long.parseLong(request.params("idPrenda")));
      System.out.printf(" IMPRIMIMOS ID PRENDA///////////////%d %n", prenda.getId());
      guardarropa.borrarPrenda(prenda);
      entityManager().remove(prenda);
    });
    long idGuardarropa = Long.parseLong(request.params("id"));
    response.redirect("/guardarropas/" + idGuardarropa);
    return new ModelAndView(modelo, "modeloGuardarropas.html.hbs");
  }
}
