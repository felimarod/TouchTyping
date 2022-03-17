/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afm.pp.typingdvorakpro;

import com.afm.pp.typingdvorakpro.logica.ManejadorDistribuciones;
import com.afm.pp.typingdvorakpro.persistencia.DistribucionTeclado;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class AppConsola {
    Scanner sc;
    ManejadorDistribuciones manDistros;
    char [] caracteresActuales;

    public AppConsola() {
        this.sc = new Scanner(System.in);
        this.manDistros = ManejadorDistribuciones.getInstance();
    }
    
    public void start(){
        inicio();
        elegirDistribucion();
        elegirFila();
    }
    
    public void inicio(){
        System.out.println("------------------------------------------------------------------------"
                + "\nBienvenido a tu app de Touch Typing por consola."
                + "\n------------------------------------------------------------------------");
    }
    
    public void elegirDistribucion(){
        clearScreen();
        int index = 0;
        int opcion = 0;
        boolean ingresoNumero;
        boolean numeroValido = false;
       
        String [] distros = ManejadorDistribuciones.getNombresDistribuciones();
        System.out.println("------------------------------------------------------------------------"
                + "\nA continuación vera las distribuciones disponibles:");
        for (;index < distros.length;index++)
            System.out.println((index + 1) + ". " + distros[index].replaceAll(".dkb", ".") );
        
        System.out.println((index + 1) + ". Agregar nueva distribución."
                +"\n------------------------------------------------------------------------");

        do {
            try{
                opcion = Integer.parseInt(pedirLinea("Ingresa el número de la distribución de teclado en la cual desea trabajar: "));
                ingresoNumero = true;
                if(opcion < 1 || opcion > index + 1){
                    numeroValido = false;
                    System.out.println("Ingrese un número valido(Entre 1 y "+ (index + 1) + ")");
                } else {
                    numeroValido = true;
                }
            }catch(NumberFormatException nfe){
                ingresoNumero = false;
            }
        } while (!(ingresoNumero && numeroValido));
        
        if(opcion <= index){
            String [] distroSeleccionada = distros[opcion-1].split("-");
            manDistros.getDistribucion(distroSeleccionada[1].replaceAll(DistribucionTeclado.EXTENSION, ""), distroSeleccionada[0]);
        } else if (opcion == index +1){
            String nombre, idioma;
            
            nombre = pedirLinea("Ingrese el nombre de la nueva distribución de teclado:");
            idioma = pedirLinea("Ingrese el idioma de la nueva distribución de teclado(EN, ES):");
            
            if(manDistros.existeDistribucion(nombre, idioma)){
                System.out.println("Ya existe la distribución de teclado "
                        + "en idioma " + idioma + " y con nombre " + nombre);
            } else {
                String[] filasNormal = new String [4];
                String[] filasSuper = new String [4];
                for(int i = 0; i<4; i++)
                    filasNormal[i] = pedirLinea("Ingrese los caracteres correspondientes a la fila " + i);

                for(int i = 0; i<4; i++)
                    filasSuper[i] = pedirLinea("Ingrese los caracteres super correspondientes a la fila " + i);

                manDistros.agregarDistribucion(nombre, idioma, filasNormal, filasSuper);
            }
        }
        //System.out.println(manDistros.getDistribucionActual().getNombreDistro());
        //System.out.println("------------------------------------------------------------------------");
    }
    
    private String pedirLinea(String linea){
        System.out.println(linea);
        return sc.nextLine();
    }

    public void clearScreen() {  
//        System.out.print("\033[H\033[2J");
//        System.out.flush();  
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            /*No hacer nada*/
        }
    }
    
    private void elegirFila() {
        int seleccion = 0;
        System.out.println("------------------------------------------------------------------------"
                + "\nFilas que puede elegir: ");
        for (int i = 0; i < 4; i++)
            System.out.println(i+1 + ".\tFila " + i);
        do {
            try{
                seleccion = Integer.parseInt(pedirLinea("Ingrese su seleccíon:"));
                break;
            }catch(NumberFormatException nfe){
                System.out.println("Ingrese un número.");
            }
        } while (true);
        
        this.caracteresActuales = manDistros.getDistribucionActual()
                .getTeclasPrincipales()
                .get(seleccion -1);  
    }
    private void elegirEjercicio(){
        System.out.println("------------------------------------------------------------------------"
                + "\nEjercicios: ");
        for (int i = 0; i < caracteresActuales.length/2; i++) 
            System.out.println((i+1) + ".\t " + caracteresActuales[i] + ", " + caracteresActuales[caracteresActuales.length-1-i]);
        
    }
}
