/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package zavrsni;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import zavrsni.controller.ObradaProizvod;
import zavrsni.model.Proizvod;
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

        ObradaProizvod op = new ObradaProizvod();
        Proizvod p = new Proizvod();
        p.setNaziv("Test");
        p.setCijena(new BigDecimal(22));
        p.setGarancija(2);
        op.setEntitet(p);

        try {
            op.create();
        } catch (MotoException ex) {
            System.out.println(ex.getPoruka());
        }

    }
}
