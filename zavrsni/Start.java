/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package zavrsni;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import zavrsni.controller.ObradaDjelatnik;
import zavrsni.controller.ObradaProizvod;
import zavrsni.controller.ObradaRacun;
import zavrsni.model.Djelatnik;
import zavrsni.model.Proizvod;
import zavrsni.model.Racun;
import zavrsni.util.HibernateUtil;
import zavrsni.util.MotoException;
import zavrsni.util.PocetniInsert;

/**
 *
 * @author Denis
 */
public class Start {

    public static void main(String[] args) {
        //HibernateUtil.getSession();
        //new PocetniInsert();
        
        
        /**
        ObradaDjelatnik od = new ObradaDjelatnik();
        
        od.setEntitet(od.read().get(0));
        
        
        try {
            od.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        **/
        
        ObradaRacun or = new ObradaRacun();
        Racun r = new Racun();
        r.setBrojRacuna("");
        or.setEntitet(r);
        
        try {
            or.create();
        } catch (MotoException ex) {
            System.out.println(ex.getPoruka());
        }
         
        
        
        

       

    }
}
