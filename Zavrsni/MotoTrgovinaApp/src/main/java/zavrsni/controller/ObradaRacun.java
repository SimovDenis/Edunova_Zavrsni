/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.text.Collator;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import zavrsni.model.Proizvod;
import zavrsni.model.Racun;
import zavrsni.util.MotoException;

/**
 *
 * @author Polaznik
 */
public class ObradaRacun extends Obrada<Racun> {

    @Override
    public List<Racun> read() {
        return session.createQuery("from Racun", Racun.class).list();
    }
    
    public List<Racun> read(String uvjet) {
        uvjet = uvjet == null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%" + uvjet + "%";

        List<Racun> lista = session.createQuery("from Racun k "
                + " where k.brojRacuna like :uvjet"
                + " order by k.brojRacuna", Racun.class)
                .setParameter("uvjet", uvjet)                
                .list();        

        lista.sort(Comparator.comparing(Racun::getBrojRacuna).reversed());

        return lista;
    }

    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaBrojRacuna();
        kontrolaKupac();
        kontrolaDjelatnik();
        kontrolaVrijemeKupovineUnos();
    }
    
    private void kontrolaKupac(){
        if (getEntitet().getKupac()== null || getEntitet().getKupac().getSifra().equals(0)) {
            getEntitet().setKupac(null);
        }
    }

    private void kontrolaVrijemeKupovineUnos() throws MotoException {
        if (getEntitet().getVrijemeKupovine() == null) {
            return;
        }

        if (getEntitet().getVrijemeKupovine().compareTo(new Date()) < 0) {
            throw new MotoException("Datum i vrijeme moraju biti nakon trenutnog datuma i vremena");
        }
    }

    private void kontrolaDjelatnik() throws MotoException {
        if (getEntitet().getDjelatnik() == null || getEntitet().getDjelatnik().getSifra().equals(0)) {
            getEntitet().setDjelatnik(null);
        }
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaBrojRacuna();
        kontrolaKupac();
        kontrolaDjelatnik();        
        kontrolaVrijemeKupovinePromjena();
    }

    private void kontrolaVrijemeKupovinePromjena() throws MotoException {
        if (getEntitet().getVrijemeKupovine() == null) {
            throw new MotoException("Datum i vrijeme moraju biti postavljeni");
        }
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {

    }

    private void kontrolaBrojRacuna() throws MotoException {
        String s = entitet.getBrojRacuna();

        if (s == null) {
            throw new MotoException("Broj računa mora biti definiran");
        }

        if (s.isEmpty()) {
            throw new MotoException("Polje broj računa ne smije biti prazno");
        }

        char[] ch = s.toCharArray();

        if (!Character.isLetter(ch[0]) && !Character.isDigit(ch[0])) {
            throw new MotoException("Broj računa mora početi sa brojem ili slovom");
        }
    }

}
