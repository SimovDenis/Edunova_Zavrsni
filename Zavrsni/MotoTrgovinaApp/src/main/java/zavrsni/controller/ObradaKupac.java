/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import zavrsni.model.Kupac;
import zavrsni.util.MotoException;

/**
 *
 * @author Polaznik
 */
public class ObradaKupac extends Obrada<Kupac> {

    @Override
    public List<Kupac> read() {
        return session.createQuery("from Kupac k order by k.sifra desc", Kupac.class).list();
    }

    public List<Kupac> read(String uvjet) {
        uvjet = uvjet == null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%" + uvjet + "%";

        List<Kupac> lista = session.createQuery("from Kupac k "
                + " where concat(k.ime,' ', k.prezime,' ',k.ime,' ',coalesce(k.kontakt,'')) like :uvjet"
                + " order by k.prezime, k.ime", Kupac.class)
                .setParameter("uvjet", uvjet)
                .setMaxResults(20)
                .list();

        Collator spCollator = Collator.getInstance(Locale.of("hr", "HR"));

        lista.sort((e1, e2) -> spCollator.compare(e1.getPrezime(), e2.getPrezime()));

        return lista;
    }
    
    public Kupac readBySifra(int sifra) {
        return session.get(Kupac.class, sifra);
    }

    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaIme();
        kontrolaPrezime();
        kontrolaKontakt();
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
        if (!entitet.getRacuni().isEmpty()) {
            throw new MotoException("Ne možete brisati kupce koji su već na nekom računu");
        }
    }

    private void kontrolaIme() throws MotoException {
        String i = entitet.getIme();

        if (i == null) {
            throw new MotoException("Ime kupca mora biti definirano");
        }

        if (i.isEmpty()) {
            throw new MotoException("Ime kupca ne smije biti prazno");
        }

        char[] iNiz = i.toCharArray();

        for (char c : iNiz) {
            if (Character.isDigit(c)) {
                throw new MotoException("Ime kupca ne smije sadržavati brojku");
            }
        }

    }

    private void kontrolaPrezime() throws MotoException {
        String p = entitet.getPrezime();

        if (p == null) {
            throw new MotoException("Prezime kupca mora biti definirano");
        }

        if (p.isEmpty()) {
            throw new MotoException("Prezime kupca ne smije biti prazno");
        }

        char[] pNiz = p.toCharArray();

        for (char c : pNiz) {
            if (Character.isDigit(c)) {
                throw new MotoException("Prezime kupca ne smije sadržavati brojku");
            }
        }

    }

    private void kontrolaKontakt() throws MotoException {
        String s = entitet.getKontakt();

        if (s == null) {
            throw new MotoException("Kontakt kupca mora biti definirano");
        }

        if (s.isEmpty()) {
            throw new MotoException("Kontakt kupca ne smije biti prazno");
        }

        char[] ch = s.toCharArray();

        if (!Character.isDigit(ch[0]) && !Character.isLetter(ch[0])) {
            throw new MotoException("Kontakt kupca mora počinjati slovom ili brojkom");
        }

    }

}
