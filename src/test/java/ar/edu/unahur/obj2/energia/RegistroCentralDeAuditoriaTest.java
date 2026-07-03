package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RegistroCentralDeAuditoriaTest {

    @Test
    void unNuevoRegistroCentralDeAuditoriaSiAgregaComoObservableAUnaBateriaRegistraSuCargaDeEnergia() {

        RegistroCentralDeAuditoria nuevoRegistro = new RegistroCentralDeAuditoria();
        
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);

        nuevoRegistro.agregarObservable(bateria1);
        
        bateria1.cargarEnergia(5000);

        assertEquals("Nueva alteración en una Batería / ID: BATERIA_1 - Nueva energía actual: 15000kWh", nuevoRegistro.getListaAlteracionesEnBaterias().getFirst());
    }

    @Test
    void unNuevoRegistroCentralDeAuditoriaSiAgregaComoObservableAUnaBateriaRegistraSuConsumoDeEnergia() {

        RegistroCentralDeAuditoria nuevoRegistro = new RegistroCentralDeAuditoria();
        
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);

        nuevoRegistro.agregarObservable(bateria1);
        
        bateria1.consumirEnergia(5000);

        assertEquals("Nueva alteración en una Batería / ID: BATERIA_1 - Nueva energía actual: 5000kWh", nuevoRegistro.getListaAlteracionesEnBaterias().getFirst());
    }


}
