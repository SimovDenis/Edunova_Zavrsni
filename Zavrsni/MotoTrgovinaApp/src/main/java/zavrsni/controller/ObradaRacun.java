/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.util.Comparator;
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
        return session.createQuery("from Racun r order by r.sifra desc", Racun.class).list();
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
        kontrolaNacinPlacanja();
    }

    private void kontrolaKupac() throws MotoException {
        if (getEntitet().getKupac() == null || getEntitet().getKupac().getSifra().equals(0)) {
            throw new MotoException("Obavezno odabrati kupca");
        }
    }

    private void kontrolaVrijemeKupovineUnos() throws MotoException {
        if (getEntitet().getVrijemeKupovine() == null) {
            throw new MotoException("Datum i vrijeme moraju biti postavljeni");
        }

        if (entitet.getVrijemeKupovine().compareTo(new Date()) < 0) {
            throw new MotoException("Datum i vrijeme moraju biti nakon trenutnog datuma i vremena");
        }
    }

    private void kontrolaDjelatnik() throws MotoException {
        if (getEntitet().getDjelatnik() == null || getEntitet().getDjelatnik().getSifra().equals(0)) {
            throw new MotoException("Obavezno odabrati djelatnika");
        }
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaBrojRacuna();
        kontrolaKupac();
        kontrolaDjelatnik();
        kontrolaVrijemeKupovinePromjena();
        kontrolaNacinPlacanja();
    }

    private void kontrolaVrijemeKupovinePromjena() throws MotoException {
        if (getEntitet().getVrijemeKupovine() == null) {
            throw new MotoException("Datum i vrijeme moraju biti postavljeni");
        }
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
        if (!entitet.getStavka().isEmpty()) {
            throw new MotoException("Ne možete obrisati račun koji ima stavke");
        }
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

        List<Racun> lista = session.createQuery("from Racun d where d.brojRacuna =:uvjet and "
                + "d.sifra !=:sifra", Racun.class)
                .setParameter("uvjet", entitet.getBrojRacuna())
                .setParameter("sifra", entitet.getSifra() == null ? 0 : entitet.getSifra())
                .list();

        if (lista != null && !lista.isEmpty()) {
            throw new MotoException("Broj računa je zauzet!");
        }

    }

    private void kontrolaNacinPlacanja() throws MotoException {
        String s = entitet.getNacinPlacanja().trim();

        if (s == null) {
            throw new MotoException("Način plaćanja mora biti definiran");
        }

        if (s.isEmpty()) {
            throw new MotoException("Polje način plaćanja ne smije biti prazno");
        }

    }

}
