/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.math.BigDecimal;
import java.util.List;
import zavrsni.model.Proizvod;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public class ObradaProizvod extends Obrada<Proizvod> {

    @Override
    public List<Proizvod> read() {
        return session.createQuery("from Proizvod", Proizvod.class).list();
    }

    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaNaziv();
        kontrolaCijena();
        kontrolaGarancija();
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
        if (!entitet.getRacuni().isEmpty()) {
            throw new MotoException("Ne možete obrisati proizvode koji postoje već na nekom računu");
        }
    }

    private void kontrolaNaziv() throws MotoException {
        String s = entitet.getNaziv();

        if (s == null) {
            throw new MotoException("Naziv mora biti definiran");
        }

        if (s.isEmpty()) {
            throw new MotoException("Naziv ne smije biti prazan");
        }

        char[] ch = s.toCharArray();

        if (!Character.isLetter(ch[0])) {
            throw new MotoException("Naziv proizvoda mora počinjati slovom");
        }

    }

    private void kontrolaCijena() throws MotoException {
        var c = entitet.getCijena();

        if (c == null) {
            return;
        }

        if (c.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MotoException("Ako je cijena postavljena, mora biti veća od 0");
        }

    }

    private void kontrolaGarancija() throws MotoException {
        var g = entitet.getGarancija();

        if (g == null) {
            return;
        }

        if (g < 0 || g > 20) {
            throw new MotoException("Ako je garancija postavljena, mora biti 0 ili veća. Također mora biti manja ili jednaka 20");
        }
    }

}
