package com.afm.pp.typingdvorakpro;

import com.afm.pp.typingdvorakpro.logica.ManejadorDistribuciones;
import com.afm.pp.typingdvorakpro.presentacion.VistaInicial;

/**
 *
 * @author Felipe Martin
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
//        for (String distro: ManejadorDistribuciones.getNombresDistribuciones())
//            System.out.println(distro);
        //ManejadorDistribuciones.getInstance().prueba();
        AppConsola miApp = new AppConsola();
        miApp.start();
        
    }
    
    
    public void prueba(){
        VistaInicial view = new VistaInicial();
        view.setVisible(true);
        
//        JComboBox<String> jcb = new JComboBox<>(getNombresDistribuciones());
//        view.add(jcb);
//        jcb.setBounds(0, 0, 50, 50);
    }
}
