/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package zavrsni;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import zavrsni.controller.ObradaOperater;
import zavrsni.model.Operater;
import zavrsni.util.HibernateUtil;
import zavrsni.util.MotoException;
import zavrsni.util.PocetniInsert;
import zavrsni.view.Autorizacija;
import zavrsni.view.SplashScreen;

/**
 *
 * @author Denis
 */
public class Start {

    public static void main(String[] args) {
        //HibernateUtil.getSession();
        new PocetniInsert();

        /**
         * ObradaDjelatnik od = new ObradaDjelatnik();
         *
         * od.setEntitet(od.read().get(0));
         *
         *
         * try { od.delete(); } catch (Exception e) { e.printStackTrace(); }
         *
         */
//        ObradaOperater oo = new ObradaOperater();
//
//        Operater o = oo.autoriziraj("oper@mototrgovina.hr", "oper");
//
//        System.out.println(o == null ? "Neispravno" : o.getIme());
        //new Autorizacija().setVisible(true);
        
        //new SplashScreen().setVisible(true);
        
        

    }

    private static void lozinka() {
        Argon2 argon2 = Argon2Factory.create();

        String hash = argon2.hash(10, 65536, 1, "oper".toCharArray());

        ObradaOperater oo = new ObradaOperater();
        Operater o = new Operater();
        o.setIme("Denis");
        o.setPrezime("Simov");
        o.setEmail("dsimov@mototrgovina.hr");
        o.setUloga("admin");
        o.setOib("64622497133");
        o.setLozinka(hash);

        oo.setEntitet(o);

        try {
            oo.create();
        } catch (MotoException ex) {
            System.out.println(ex.getPoruka());
        }
    }
}
