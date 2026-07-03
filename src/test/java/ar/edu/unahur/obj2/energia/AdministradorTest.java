package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AdministradorTest {

    @Test
    void aUnNuevoAdministradorSeLoInformaCorrectamenteDelIncrementoDeEnergiaEnSuBateriaAsignada() {

        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("bateria_1", 10000);
        Administrador admin = new Administrador(bateria1);

        bateria1.cargarEnergia(3500);

        assertEquals("Se cargó 3500 kWh de energía en su batería", admin.getUltimoCambioRecibido());

    }

    @Test
    void aUnNuevoAdministradorSeLoInformaCorrectamenteDelConsumoDeEnergiaEnSuBateriaAsignada() {

        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("bateria_1", 10000);
        Administrador admin = new Administrador(bateria1);

        bateria1.consumirEnergia(3000);

        assertEquals("Se consumió 3000 kWh de energía en su batería", admin.getUltimoCambioRecibido());

    }

}
