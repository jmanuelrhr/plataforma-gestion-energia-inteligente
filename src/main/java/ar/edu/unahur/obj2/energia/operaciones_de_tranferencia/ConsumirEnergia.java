package ar.edu.unahur.obj2.energia.operaciones_de_tranferencia;

import ar.edu.unahur.obj2.energia.BateriaDeAlmacenamiento;

public class ConsumirEnergia implements IOperacionesDeTransferencia {

    private BateriaDeAlmacenamiento bateria;
    private Integer cantidad;
    private Boolean ejecutada;

    public ConsumirEnergia(BateriaDeAlmacenamiento bateria, Integer cantidad) {
        this.bateria = bateria;
        this.cantidad = cantidad;
        this.ejecutada = false;
    }

    @Override
    public void ejecutar() {
        bateria.consumirEnergia(cantidad);
        this.ejecutada = true;
    }

    @Override
    public void deshacer() {
        bateria.cargarEnergia(cantidad);
    }

    @Override
    public Boolean fueEjecutada() {
        return this.ejecutada;
    }

}
