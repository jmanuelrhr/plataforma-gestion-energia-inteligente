package ar.edu.unahur.obj2.energia;

import ar.edu.unahur.obj2.energia.interfaces_observadores.Observador;

public class Administrador implements Observador {

    private final BateriaDeAlmacenamiento bateriaAsignada;
    private Integer energiaInicialBateriaAsignada;
    private String ultimaVariacionRecibida;

    public Administrador(BateriaDeAlmacenamiento bateriaAsignada) {
        this.bateriaAsignada = bateriaAsignada;
        this.energiaInicialBateriaAsignada = bateriaAsignada.getEnergiaActual();
        this.ultimaVariacionRecibida = "";
        bateriaAsignada.añadirObservador(this);
    }

    public String getUltimoCambioRecibido(){
        return this.ultimaVariacionRecibida;
    }

    @Override
    public void actualizar() {
        if(bateriaAsignada.getEnergiaActual() > energiaInicialBateriaAsignada){
           this.ultimaVariacionRecibida = "Se cargó "+ (bateriaAsignada.getEnergiaActual() - energiaInicialBateriaAsignada) + " kWh de energía en su batería";
        } else {
           this.ultimaVariacionRecibida = "Se consumió "+ (energiaInicialBateriaAsignada - bateriaAsignada.getEnergiaActual()) + " kWh de energía en su batería";
        }

        this.energiaInicialBateriaAsignada = bateriaAsignada.getEnergiaActual();
    }

}
