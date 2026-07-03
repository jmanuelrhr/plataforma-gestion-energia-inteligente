package ar.edu.unahur.obj2.energia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.energia.excepciones.IntentoDeConsumoExcesivoDeBateriaException;
import ar.edu.unahur.obj2.energia.excepciones.valorDeOperacionInvalidoException;
import ar.edu.unahur.obj2.energia.operaciones_de_tranferencia.*;

public class OperacionesTest {

    @Test
    void dadaUnaAmpliacionEnUnaBateriaCuandoSeEjecutaAumentaCorrectamenteSuEnergia() {
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);
        CargarEnergia ampliacion = new CargarEnergia(bateria1, 3000);

        ampliacion.ejecutar();

        assertEquals(13000, bateria1.getEnergiaActual());
    }

    @Test
    void dadaUnaAmpliacionYeEjecutadaEnUnaBateriaCuandoSeDeshaceEstaVuelveAlValorOriginalCorrectamente() {
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);
        CargarEnergia ampliacion = new CargarEnergia(bateria1, 3000);

        ampliacion.ejecutar(); // Pasa a 13000

        ampliacion.deshacer(); // Vuelve a 10000

        assertEquals(10000, bateria1.getEnergiaActual());
    }

    @Test
    void dadaUnConsumoEnUnaBateriaCuandoSeEjecutaDisminuyeCorrectamenteSuEnergia() {
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);
        ConsumirEnergia consumo = new ConsumirEnergia(bateria1, 3000);

        consumo.ejecutar();

        assertEquals(7000, bateria1.getEnergiaActual());
    }

    @Test
    void dadaUnConsumoYaEjecutadoEnUnaBateriaCuandoSeDeshaceEstaVuelveAlValorOriginalCorrectamente() {
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);
        ConsumirEnergia consumo = new ConsumirEnergia(bateria1, 3000);

        consumo.ejecutar(); // Pasa a 7000

        consumo.deshacer(); // Vuelve a 10000 

        assertEquals(10000, bateria1.getEnergiaActual());
    }

    @Test
    void cuandoSeIntentaConsumirMasEnergiaDeLaQueTieneLaBateriaSeLanzaUnaExcepcion() {
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);
        ConsumirEnergia consumo = new ConsumirEnergia(bateria1, 15001);
        // excede el limite de energia negativa de 5000 kWh

        assertThrows(IntentoDeConsumoExcesivoDeBateriaException.class, () -> {
            consumo.ejecutar();;
        });

    }

    @Test
    void cuandoSeIntentaHacerUnaAmpliacionEnUnaBateriaConUnValorInvalidoSeLanzaUnaExcepcion() {
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);
        CargarEnergia ampliacion = new CargarEnergia(bateria1, -1000);
        // Valor de aumento de energia invalido

        assertThrows(valorDeOperacionInvalidoException.class, () -> {
            ampliacion.ejecutar();;
        });
    }

    @Test
    void cuandoSeIntentaHacerUnConsumoEnUnaBateriaConUnValorInvalidoSeLanzaUnaExcepcion() {
        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 10000);
        ConsumirEnergia consumo = new ConsumirEnergia(bateria1, -1000);
        // Valor de consumo de energia invalido

        assertThrows(valorDeOperacionInvalidoException.class, () -> {
            consumo.ejecutar();;
        });
    }

    // Transaccionalidad

    @Test
    void dadaUnLoteDeAccionesEjecutadasCorrectamenteSiUnaFallaSeDeshacenTodasYLaBateriaVuelveAlValorOriginal() {

        BateriaDeAlmacenamiento bateria1 = new BateriaDeAlmacenamiento("BATERIA_1", 100000);
        ConsumirEnergia consumo = new ConsumirEnergia(bateria1, 25000);
        ConsumirEnergia consumo2 = new ConsumirEnergia(bateria1, 25000);
        ConsumirEnergia consumo3 = new ConsumirEnergia(bateria1, 80000);
        // el consumo3 excede el limite de energia negativa de 5000 kWh, por lo que al ejecutarse se lanza una excepcion y se deshacen todas las acciones

        ControladorDeOperaciones controladorDeOps = new ControladorDeOperaciones();

        controladorDeOps.registrarOperaciones(consumo, consumo2, consumo3);
        controladorDeOps.ejecutarTodasLasAccionesRegistradas();

        assertEquals(100000, bateria1.getEnergiaActual());

    }

}
