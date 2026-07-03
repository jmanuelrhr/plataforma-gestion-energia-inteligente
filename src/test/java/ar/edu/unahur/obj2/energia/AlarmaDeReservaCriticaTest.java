package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.energia.excepciones.UsoDeLimiteDeReservaException;

public class AlarmaDeReservaCriticaTest {

    @Test
    void alCrearUnSistemaDeAlarmaDeReservaCriticaEsteLanzaUnaAdvertenciaCuandoSeExcedeElUmbralEstandarDeTolerancia(){
      
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("bateria_1", 10000);
        AlarmaDeReservaCritica alarma = new AlarmaDeReservaCritica(bateria1);
        alarma.agregarBateria(bateria1);

        try {
            bateria1.consumirEnergia(12000);
        } catch (UsoDeLimiteDeReservaException e) {
            assertTrue(e.getMessage().equals("Cuidado! Alerta de uso del limite de reserva de enegia de la bateria modificada de ID: bateria_1")
                      && bateria1.getEnergiaActual() == -2000);
                      
            // se modifica la energia actual de la bateria a -2000, 
            // pero se lanza la excepcion de uso de limite de reserva
            // a modo de advertencia
        }
    }

}
