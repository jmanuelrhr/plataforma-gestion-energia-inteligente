package ar.edu.unahur.obj2.energia.operaciones_de_tranferencia;

import ar.edu.unahur.obj2.energia.BateriaDeAlmacenamiento;

public class CargarEnergia implements IOperacionesDeTransferencia {

    private BateriaDeAlmacenamiento bateria;
    private Integer cantidad;
    private Boolean ejecutada;

    public CargarEnergia(BateriaDeAlmacenamiento bateria, Integer cantidad) {
        this.bateria = bateria;
        this.cantidad = cantidad;
        this.ejecutada = false;
    }

    @Override
    public void ejecutar() {
        bateria.cargarEnergia(cantidad);
    }

    @Override
    public void deshacer() {
        bateria.consumirEnergia(cantidad);
    }

    @Override
    public Boolean fueEjecutada() {
        return this.ejecutada;
    }

}
