/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package zavrsni;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zavrsni.controller.ObradaDjelatnik;
import zavrsni.controller.ObradaKupac;
import zavrsni.controller.ObradaProizvod;
import zavrsni.controller.ObradaRacun;
import zavrsni.model.Djelatnik;
import zavrsni.model.Kupac;
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
        
        ObradaDjelatnik ok = new ObradaDjelatnik();
        Djelatnik k = new Djelatnik();
        k.setIme("Denis");
        k.setPrezime("Denić");
        k.setKontakt("dsimov19@gmail.com");
        k.setBrojUgovora("444.1020");
        k.setIban("HR3242536");
        ok.setEntitet(k);
        
        try {
            ok.create();
        } catch (MotoException ex) {
            System.out.println(ex.getPoruka());
        }
         
        
        
        

       

    }
}
