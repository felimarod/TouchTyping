/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afm.pp.typingdvorakpro.persistencia;

/**
 *
 * @author Usuario
 */
public interface InterfaceTeclado {
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
    
    void agregarFilaNormal(String fila);
}
