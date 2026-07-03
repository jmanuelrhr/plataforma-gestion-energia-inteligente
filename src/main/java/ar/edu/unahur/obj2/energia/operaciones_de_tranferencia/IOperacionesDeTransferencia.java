package ar.edu.unahur.obj2.energia.operaciones_de_tranferencia;

import ar.edu.unahur.obj2.energia.excepciones.valorDeOperacionInvalidoException;

public interface IOperacionesDeTransferencia {

    void ejecutar();

    void deshacer() throws valorDeOperacionInvalidoException;
 
    Boolean fueEjecutada();

}
