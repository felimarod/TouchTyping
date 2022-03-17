package com.afm.pp.typingdvorakpro.persistencia;

import com.afm.pp.typingdvorakpro.logica.ManejadorDistribuciones;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Martin
 */
public class DistribucionTeclado implements InterfaceArchivoTeclado {
    public static final String CARPETA = "distros/";
    public static final String EXTENSION = ".dkb";
    public static final String SEPARADOR = "-";
    /**
     * Idioma de la distribución de teclado
     * EN - ingles, ES - español
     */
    private final String idioma;
    /**
     * Nombre de la distribución de teclado
     * dvorak, dvorakProgrammer, qwerty
     */
    private final String nombreDistro;
    private final String rutaArchivo;
    private final List<char []> teclasPrincipales;
    private final List<char []> teclasSuper;
    /**
     * 
     * @param idioma Idioma de la distribución de teclado
     * @param nombreDistro dvorak, dvorakProgrammer, qwerty
     */
    public DistribucionTeclado(String idioma, String nombreDistro) {
        this.idioma = idioma;
        this.nombreDistro = nombreDistro;
        this.rutaArchivo = DistribucionTeclado.CARPETA + this.idioma + 
                DistribucionTeclado.SEPARADOR + this.nombreDistro + 
                DistribucionTeclado.EXTENSION;
        this.teclasPrincipales = new ArrayList<>();
        this.teclasSuper = new ArrayList<>();
        if(tieneArchivo()){
            leerArchivo();
        } else {
            System.out.println("No existe un archivo de la distribución: " 
                    + idioma + " " + nombreDistro);
        }
    }

    public List<char[]> getTeclasPrincipales() {
        return teclasPrincipales;
    }

    public List<char[]> getTeclasSuper() {
        return teclasSuper;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getNombreDistro() {
        return nombreDistro;
    }
    
    
    
    @Override
    public boolean tieneArchivo() {
        File archivo = new File(rutaArchivo);    
        return archivo.exists();
    }

    @Override
    public void leerArchivo() {
        int numLinea = 0;
        try {
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                
                if(numLinea < 4){
                    agregarFila(linea);
                } else {
                    agregarFilaSuper(linea);
                }
                
                linea = br.readLine();
                numLinea++;
            }
            br.close();
        } catch (IOException n) {
            System.out.println(n.toString());
        }
    }

    @Override
    public void guardarArchivo() {
        if(tieneArchivo()){
            System.out.println("Ya existe archivo de la distribución.");
            return;
        }
        FileWriter fw;
        try {
            fw = new FileWriter(rutaArchivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (char [] fila: teclasPrincipales) {
                String linea = "";
                for(char caracter: fila){
                    linea += caracter;
                }
                bw.write(linea);
                bw.newLine();
            }
            for (char [] fila: teclasSuper) {
                String linea = "";
                for(char caracter: fila){
                    linea += caracter;
                }
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Se guardo el archivo.");
            bw.close();
        } catch (IOException ex) {
            System.out.println("No se pudo guardar el archivo.\nError:\n" + ex.toString());
        }
    }

    @Override
    public void agregarFila(String fila) {
        teclasPrincipales.add(fila.toCharArray());
    }

    @Override
    public void agregarFilaSuper(String fila) {
        teclasSuper.add(fila.toCharArray());
    }
}
