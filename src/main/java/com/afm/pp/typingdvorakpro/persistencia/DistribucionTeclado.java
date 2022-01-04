package com.afm.pp.typingdvorakpro.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Martin
 */
public class DistribucionTeclado implements InterfaceTeclado{
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
    private boolean tieneArchivo;
    private final List<char []> teclasPrincipales;
    /**
     * 
     * @param idioma Idioma de la distribución de teclado
     * @param nombreDistro dvorak, dvorakProgrammer, qwerty
     */
    public DistribucionTeclado(String idioma, String nombreDistro) {
        this.idioma = idioma;
        this.nombreDistro = nombreDistro;
        this.rutaArchivo = this.idioma + "-" + this.nombreDistro + ".txt";
        this.tieneArchivo = false;
        this.teclasPrincipales = new ArrayList<>();
//        if(tieneArchivo()){
//            leerArchivo();
//        } else {
//            System.out.println("No existe un archivo de la distribución: " 
//                    + idioma + " " + nombreDistro);
//        }
    }
    
    public static void main(String[] args) {
        DistribucionTeclado dvorakPro = new DistribucionTeclado("EN", "dvorakProgrammer");
        dvorakPro.agregarFilaNormal("[{}(=*)+]!");
        //super agregarFilaNormal("%7531902468");
        dvorakPro.agregarFilaNormal(";,.pyfgcrl");
        dvorakPro.agregarFilaNormal("aoeuidhtns");
        dvorakPro.agregarFilaNormal("'qjkxbmwvz");
        
        dvorakPro.guardarArchivo();
    }
    @Override
    public boolean tieneArchivo() {
        if(tieneArchivo == false){
            File archivo = new File(rutaArchivo);
            tieneArchivo = archivo.exists();
        }
        return tieneArchivo;
    }

    @Override
    public void leerArchivo() {
        try {
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                
                agregarFilaNormal(linea);
                
                linea = br.readLine();
            }
            br.close();
        } catch (IOException n) {
            System.out.println(n.toString());
        }
    }

    @Override
    public void guardarArchivo() {
        String linea;
        FileWriter fw;
        try {
            fw = new FileWriter(rutaArchivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (char [] fila: teclasPrincipales) {
                linea = "";
                for(char caracter: fila){
                    linea += caracter;
                }
                System.out.println(linea);
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Se guardo el archivo.");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(DistribucionTeclado.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public void agregarFilaNormal(String fila) {
        teclasPrincipales.add(fila.toCharArray());
    }

    
    
}
