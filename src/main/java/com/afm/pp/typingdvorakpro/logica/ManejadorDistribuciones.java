package com.afm.pp.typingdvorakpro.logica;

import com.afm.pp.typingdvorakpro.persistencia.DistribucionTeclado;
import com.afm.pp.typingdvorakpro.presentacion.VistaInicial;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Martin
 */
public class ManejadorDistribuciones {
    
    /**
     * Distribucion actual sobre la cual se va a trabajar.
     */
    private static DistribucionTeclado distribucionActual;

    /**
     * Instancia unica de esta clase.
     */
    private static ManejadorDistribuciones instance;

    /**
     * Único constructor
     */
    private ManejadorDistribuciones(){
        
    }
    
    /**
     * Método que retorna la única instancia de la clase.
     * @return 
     */
    public static ManejadorDistribuciones getInstance(){
        if(ManejadorDistribuciones.instance == null)
            ManejadorDistribuciones.instance = new ManejadorDistribuciones();
        
        return ManejadorDistribuciones.instance;
    }
    
    public boolean existeDistribucion(String nombre, String idioma){
        File archivo = new File(
                DistribucionTeclado.CARPETA + idioma + 
                DistribucionTeclado.SEPARADOR + nombre + 
                        DistribucionTeclado.EXTENSION
        );
        return archivo.exists();
    }
    
    public void agregarDistribucion(String nombre, String idioma, String [] filasNormal, String [] filasSuper){
        if(!existeDistribucion(nombre, idioma)){
            DistribucionTeclado nuevaDistro = new DistribucionTeclado(idioma, nombre);
            for(String fila: filasNormal)
                nuevaDistro.agregarFila(fila);
            for(String fila: filasSuper)
                nuevaDistro.agregarFilaSuper(fila);
            nuevaDistro.guardarArchivo();
        } else {
            System.out.println("Ya existe la distro " + idioma + " " + nombre);
        }
    }

    public DistribucionTeclado getDistribucionActual() {
        if (ManejadorDistribuciones.distribucionActual != null) {
            return ManejadorDistribuciones.distribucionActual;
        } else {
            System.out.println("No se tiene una distribución actualmente.");
            return null;
        }
    }
    
    public DistribucionTeclado getDistribucion(String nombre, String idioma){
        if(existeDistribucion(nombre, idioma)){
            ManejadorDistribuciones.distribucionActual = new DistribucionTeclado(idioma, nombre);
            return ManejadorDistribuciones.distribucionActual;
        } else {
            System.out.println("No existe una distribucion con: " + nombre + idioma);
            return null;
        }
    }
    
    public static final String [] getNombresDistribuciones(){
        File carpeta = new File("distros");
        List<String> nombresDistros = new ArrayList<>();
        
        for (File archivo : carpeta.listFiles()) 
            if(archivo.isFile())
                nombresDistros.add(archivo.getName());//.split("-")[1].split(".dst")[0]);
        
        String [] nombres = new String[nombresDistros.size()];
        for (int i = 0; i < nombresDistros.size(); i++) 
            nombres[i] = nombresDistros.get(i);
        
        return nombres;
    }
}
