package ar.edu.unahur.obj2.energia;

import java.util.*;

import ar.edu.unahur.obj2.energia.excepciones.IntentoDeConsumoExcesivoDeBateriaException;
import ar.edu.unahur.obj2.energia.excepciones.valorDeOperacionInvalidoException;
import ar.edu.unahur.obj2.energia.interfaces_observadores.Observable;
import ar.edu.unahur.obj2.energia.interfaces_observadores.Observador;

public class BateriaDeAlmacenamiento implements Observable {

    private final String id;
    private Integer energiaActual; // kWh
    private final List<Observador> listaObservadores;

    public BateriaDeAlmacenamiento(String id, Integer energiaActual) {
        this.id = id;
        this.energiaActual = energiaActual;
        this.listaObservadores = new ArrayList<>();
    }

    public Integer getEnergiaActual() {
        return energiaActual;
    }

    public String getId() {
        return this.id;
    }

    public void cargarEnergia(Integer cantidad) {
        if (cantidad <= 0) {
            throw new valorDeOperacionInvalidoException("Valor de aumento de energia invalido.");
        } else {
            this.energiaActual += cantidad;
            this.notificarObservadores();
        }
    }

    public void consumirEnergia(Integer cantidad) {
        if (cantidad <= 0) {
            throw new valorDeOperacionInvalidoException("Valor de consumo de energia invalido.");
        } if ((this.energiaActual - cantidad) < -5000) {
            throw new IntentoDeConsumoExcesivoDeBateriaException("Intento de consumo de energia excesivo en la bateria.");
        } else {
            this.energiaActual -= cantidad;
            this.notificarObservadores();
        }
    }

    @Override
    public void añadirObservador(Observador observador) {
        listaObservadores.add(observador);
    }

    @Override
    public void eliminarObservador(Observador observador) {
        listaObservadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
       for (Observador observador : listaObservadores) {
            observador.actualizar();
        }
    }

}
