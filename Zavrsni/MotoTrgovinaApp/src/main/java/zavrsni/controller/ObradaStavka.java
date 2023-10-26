/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.util.List;
import zavrsni.model.Stavka;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public class ObradaStavka extends Obrada<Stavka> {

    @Override
    public List<Stavka> read() {
        return session.createQuery("from Stavka s", Stavka.class).list();
    }

    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaKolicina();
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
    }

    private void kontrolaKolicina() throws MotoException {
        var g = entitet.getKolicina();

        if (g == null) {
            throw new MotoException("Količina mora biti definirana");
        }

        if (g < 1 || g > 10) {
            throw new MotoException("Ako je količina postavljena, mora biti 0 ili veća. Također mora biti manja ili jednaka 10");
        }
    }

}
