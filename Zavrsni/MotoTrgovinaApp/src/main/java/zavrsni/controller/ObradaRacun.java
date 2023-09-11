/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import zavrsni.model.Racun;
import zavrsni.util.MotoException;

/**
 *
 * @author Polaznik
 */
public class ObradaRacun extends Obrada<Racun> {

    @Override
    public List<Racun> read() {
        return session.createQuery("from Racuni", Racun.class).list();
    }

    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaVrijemeKupovine();
        kontrolaBrojRacuna();
        kontrolaNacinPlacanja();
        kontrolaKupac();
        kontrolaDjelatnik();
        kontrolaProizvod();
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
        if (entitet.getKupac() != null || entitet.getDjelatnik() != null || entitet.getProizvodi() != null) {
            throw new MotoException("Ne možete obrisati račun koji ima kupca, djelatnika ili proizvod na sebi");
        }

    }

    private void kontrolaBrojRacuna() throws MotoException {
        String s = entitet.getBrojRacuna();
        
         if (s == null) {
            throw new MotoException("Broj računa mora biti definiran");
        }

        if (s.isEmpty()) {
            throw new MotoException("Broj računa ne smije biti prazno");
        }
        
        char[] ch = s.toCharArray();

        if (!Character.isLetter(ch[0]) && !Character.isDigit(ch[0])) {
            throw new MotoException("Broj računa mora početi sa brojem ili slovom");
        }

       

    }

    private void kontrolaKupac() throws MotoException {
        if (entitet.getKupac() == null) {
            throw new MotoException("Račun mora imati kupca");
        }
    }

    private void kontrolaDjelatnik() throws MotoException {
        if (entitet.getDjelatnik() == null) {
            throw new MotoException("Račun mora imati djelatnika");
        }
    }

    private void kontrolaProizvod() throws MotoException {
        if (entitet.getProizvodi() == null) {
            throw new MotoException("Račun mora imati minimalno jedan proizvod");
        }
    }

    private void kontrolaNacinPlacanja() throws MotoException {
        String s = entitet.getNacinPlacanja().toLowerCase();
        if (!s.equals("gotovina") && !s.equals("kartica")) {
            throw new MotoException("Način plaćanja mora biti \"gotovina\" ili \"kartica\"");
        }

    }

    private void kontrolaVrijemeKupovine() throws MotoException {
        if (entitet.getVrijemeKupovine() == null) {
            throw new MotoException("Vrijeme kupovine mora biti definirano");
        }

        if (entitet.getVrijemeKupovine().before(Date.from(Instant.now()))) {
            throw new MotoException("Vrijeme kupovine ne može biti prije trenutnog vremena");
        }
    }

}
