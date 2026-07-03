package ar.edu.unahur.obj2.energia.interfaces_observadores;

public interface Observable {

    void añadirObservador(Observador observador);

    void eliminarObservador(Observador observador);

    void notificarObservadores();

}
