package model;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.Collection;
import java.util.List;

public class RepositorioGuardarropas implements WithSimplePersistenceUnit {

	private static final RepositorioGuardarropas INSTANCE = new RepositorioGuardarropas();

	public static RepositorioGuardarropas instance() {
		return INSTANCE;
	}

	public List<Guardarropa> obtenerTodos() {
		return entityManager().createQuery("from Guardarropa", Guardarropa.class).getResultList();
	}
	public Guardarropa buscar(long id) {
		return entityManager().find(Guardarropa.class, id);
	}

}



