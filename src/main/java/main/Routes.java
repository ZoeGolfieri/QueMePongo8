package main;

import controller.DemoController;
import controller.GuardarropasController;
import controller.PrendasController;
import controller.SesionController;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.persistence.PersistenceException;

public class Routes implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    new Bootstrap().run();
    new Routes().start();
  }

  public void start() {
    System.out.println("Iniciando servidor");

    Spark.port(9000);
    Spark.staticFileLocation("/public");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    DemoController demoController = new DemoController();
    GuardarropasController guardarropasController = new GuardarropasController();
    PrendasController prendasController = new PrendasController();
    SesionController sesionController = new SesionController();

    Spark.get("/", demoController::listar, engine);
    Spark.get("/guardarropas", guardarropasController::listar, engine);
    Spark.get("/guardarropas/:id", guardarropasController::buscar, engine);
    Spark.post("/guardarropas/:id/prendas", prendasController::crear);
    Spark.get("/guardarropas/:id/prendas/nueva", prendasController::nueva, engine);
    Spark.get("/guardarropas/:id/prendas/:idPrenda", prendasController::buscar, engine);
    Spark.get("/guardarropas/:id/prendas/:idPrenda/borrar", prendasController::borrar, engine);
    Spark.get("/login", sesionController::mostrarLogin, engine);
    Spark.post("/login", sesionController::iniciarSesion);

    Spark.exception(PersistenceException.class, (e, request, response) -> {
      response.redirect("/500"); //TODO
    });

    Spark.before((request, response) -> {
      entityManager().clear();
    });
  }


}
