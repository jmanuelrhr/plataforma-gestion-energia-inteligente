package ar.edu.unahur.obj2.energia;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.energia.interfaces_observadores.Observador;

public class RegistroCentralDeAuditoria implements Observador {

    private List<String> listaAlteracionesNuevasEnBaterias;
    private BateriaDeAlmacenamiento bateriaAObservar;

    public RegistroCentralDeAuditoria() {
        this.listaAlteracionesNuevasEnBaterias = new ArrayList<>();
        this.bateriaAObservar = null;
    }

    public void agregarObservable(BateriaDeAlmacenamiento unaBateria) {
        if (this.listaAlteracionesNuevasEnBaterias.size() != 0) {
            bateriaAObservar = unaBateria;
            unaBateria.añadirObservador(this);
            this.listaAlteracionesNuevasEnBaterias.clear();

        } else {
            bateriaAObservar = unaBateria;
            unaBateria.añadirObservador(this);
        }
    }

    public List<String> getListaAlteracionesEnBaterias() {
        return this.listaAlteracionesNuevasEnBaterias;
    }

    @Override
    public void actualizar() {
        String alteracionNuevaEnBateria = "Nueva alteración en una Batería / ID: " + bateriaAObservar.getId()
                + " - Nueva energía actual: " + bateriaAObservar.getEnergiaActual() + "kWh";

        this.listaAlteracionesNuevasEnBaterias.add(alteracionNuevaEnBateria);
    }

}
