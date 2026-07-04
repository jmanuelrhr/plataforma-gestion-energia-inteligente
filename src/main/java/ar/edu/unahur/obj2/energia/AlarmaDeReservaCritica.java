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

    public String getUltimaAdvertenciaRegistrada() {
        return this.ultimaAdvertenciaRegistrada;
    }

    public void agregarBateria(BateriaDeAlmacenamiento unaBateria) {
        this.bateriaASupervisar = unaBateria;
        bateriaASupervisar.añadirObservador(this);
    }

    public void comprobarDesbordamientoCritico(BateriaDeAlmacenamiento bateriaControlada) {
        if (bateriaControlada.getEnergiaActual() < this.umbralDeTolerancia) {
            String advertenciaARegistrar = "Cuidado! Alerta de uso del limite de reserva de enegia de la bateria modificada de ID: "  + bateriaControlada.getId();
            this.ultimaAdvertenciaRegistrada = advertenciaARegistrar;
            throw new UsoDeLimiteDeReservaException(advertenciaARegistrar);
        }
    }

    @Override
    public void actualizar() {
        this.comprobarDesbordamientoCritico(bateriaASupervisar);
    }

}
