package ar.edu.unahur.obj2.energia;

import ar.edu.unahur.obj2.energia.excepciones.UsoDeLimiteDeReservaException;
import ar.edu.unahur.obj2.energia.interfaces_observadores.Observador;

public class AlarmaDeReservaCritica implements Observador {

    private Integer umbralDeTolerancia; // kWh
    private BateriaDeAlmacenamiento bateriaASupervisar;
    private String ultimaAdvertenciaRegistrada; 

    public AlarmaDeReservaCritica(BateriaDeAlmacenamiento bateriaASupervisar) {
        this.umbralDeTolerancia = 0;
        this.bateriaASupervisar = bateriaASupervisar;
    }

    public void agregarBateria(BateriaDeAlmacenamiento unaBateria) {
        this.bateriaASupervisar = unaBateria;
        bateriaASupervisar.añadirObservador(this);
    }

    public void comprobarDesbordamientoCritico(BateriaDeAlmacenamiento bateriaControlada) {
        if (bateriaControlada.getEnergiaActual() < this.umbralDeTolerancia) {
            throw new UsoDeLimiteDeReservaException("Cuidado! Alerta de uso del limite de reserva de enegia de la bateria modificada de ID: "  + bateriaControlada.getId());
        }
    }

    @Override
    public void actualizar() {
        this.comprobarDesbordamientoCritico(bateriaASupervisar);
    }

}
