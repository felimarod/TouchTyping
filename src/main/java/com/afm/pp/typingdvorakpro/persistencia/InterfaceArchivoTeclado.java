package com.afm.pp.typingdvorakpro.persistencia;

/**
 *
 * @author Felipe Martin
 */
public interface InterfaceArchivoTeclado {
    /**
     * Verifica si existe el archivo
     * @return Valor de verdad sobre la existencia del archivo
     */
    boolean tieneArchivo();
    
    /**
     * Lee el archivo de la distibucion, la ruta del mismo esta dada por el idioma
     * y el nombre de la distibucion:
     * EJ: ES-dvorak.txt
     * Se debe verificar que existe el archivo antes de leerlo
     */
    void leerArchivo();
    
    void guardarArchivo();
    
    void agregarFila(String fila);
    
    void agregarFilaSuper(String fila);
}
