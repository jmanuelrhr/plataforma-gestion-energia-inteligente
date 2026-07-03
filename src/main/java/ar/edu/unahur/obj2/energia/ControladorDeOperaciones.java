package ar.edu.unahur.obj2.energia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unahur.obj2.energia.operaciones_de_tranferencia.IOperacionesDeTransferencia;

public class ControladorDeOperaciones {

    private List<IOperacionesDeTransferencia> listaDeOperaciones;

    public ControladorDeOperaciones() {
        this.listaDeOperaciones = new ArrayList<>();
    }

    public void registrarOperaciones(IOperacionesDeTransferencia... operaciones) {
        List<IOperacionesDeTransferencia> operacionesEncontradas = Arrays.asList(operaciones);

        operacionesEncontradas.forEach(operacion -> {
            if (!listaDeOperaciones.contains(operacion)) {
                listaDeOperaciones.add(operacion);
            }
        });
    }

    public void ejecutarAccion(IOperacionesDeTransferencia operacion) {
        if (listaDeOperaciones.contains(operacion)) {
            operacion.ejecutar();
        }
    }

    public void deshacerAccion(IOperacionesDeTransferencia operacion) {
        if (listaDeOperaciones.contains(operacion)) {
            operacion.deshacer();
        }
    }

    public void ejecutarTodasLasAccionesRegistradas() {
        listaDeOperaciones.forEach(operacion -> {
            try {
                this.ejecutarAccion(operacion);
            } catch (RuntimeException e) {
                this.deshacerTodasLasAcciones();
                return;
            }
        });
        this.vaciarAccionesRegistradas();
    }

    public void deshacerTodasLasAcciones() {
        listaDeOperaciones.forEach(operacion -> {
            if (operacion.fueEjecutada()) {
                this.deshacerAccion(operacion);
            }
        });
    }

    public void vaciarAccionesRegistradas() {
        listaDeOperaciones.clear();
    }

}
