/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import zavrsni.model.Djelatnik;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public class ObradaDjelatnik extends Obrada<Djelatnik> {

    @Override
    public List<Djelatnik> read() {
        return session.createQuery("from Djelatnik k order by k.sifra desc", Djelatnik.class)
                .setMaxResults(20)
                .list();
    }

    public List<Djelatnik> read(String uvjet) {
        uvjet = uvjet == null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%" + uvjet + "%";

        List<Djelatnik> lista = session.createQuery("from Djelatnik k "
                + " where concat(k.ime,' ', k.prezime,' ',k.ime,' ',coalesce(k.brojUgovora,'')) like :uvjet"
                + " order by k.prezime, k.ime", Djelatnik.class)
                .setParameter("uvjet", uvjet)
                .setMaxResults(20)
                .list();

        Collator spCollator = Collator.getInstance(Locale.of("hr", "HR"));

        lista.sort((e1, e2) -> spCollator.compare(e1.getPrezime(), e2.getPrezime()));

        return lista;
    }

    public Djelatnik readBySifra(int sifra) {
        return session.get(Djelatnik.class, sifra);
    }

    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaIme();
        kontrolaPrezime();
        kontrolaBrojUgovora();
        kontrolaIBAN();
        kontrolaKontakt();
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
        if (!entitet.getRacuni().isEmpty()) {
            throw new MotoException("Ne možete obrisati djelatnika koji je već na nekom računu");
        }
    }

    private void kontrolaIme() throws MotoException {

        String i = entitet.getIme();

        if (i == null) {
            throw new MotoException("Ime djelatnika mora biti definirano");
        }

        if (i.isEmpty()) {
            throw new MotoException("Ime djelatnika ne smije biti prazno");
        }

        char[] iNiz = i.toCharArray();

        for (char c : iNiz) {
            if (Character.isDigit(c) || !Character.isLetter(iNiz[0])) {
                throw new MotoException("Ime djelatnika ne smije sadržavati brojku i prvi znak mora biti slovo");
            }
        }

    }

    private void kontrolaPrezime() throws MotoException {

        String p = entitet.getPrezime();

        if (p == null) {
            throw new MotoException("Prezime djelatnika mora biti definirano");
        }

        if (p.isEmpty()) {
            throw new MotoException("Prezime djelatnika ne smije biti prazno");
        }

        char[] pNiz = p.toCharArray();

        for (char c : pNiz) {
            if (Character.isDigit(c) || !Character.isLetter(pNiz[0])) {
                throw new MotoException("Prezime djelatnika ne smije sadržavati brojku i prvi znak mora biti slovo");
            }
        }

    }

    private void kontrolaBrojUgovora() throws MotoException {

        String br = entitet.getBrojUgovora();

        if (br == null) {
            throw new MotoException("Broj ugovora djelatnika mora biti definiran");
        }

        if (br.isEmpty()) {
            throw new MotoException("Broj ugovora djelatnika ne smije biti prazan");
        }

        if (!br.contains("-") && !br.contains("/")) {
            throw new MotoException("Broj ugovora djelatnika mora sadržavati minus(-) ili kosu crtu(/)");
        }

        char[] ch = br.toCharArray();

        if (!Character.isDigit(ch[0]) && !Character.isLetter(ch[0])) {
            throw new MotoException("Broj ugovora djelatnika mora počinjati slovom ili brojkom");
        }

        List<Djelatnik> lista = session.createQuery("from Djelatnik d where d.brojUgovora =:uvjet and "
                + "d.sifra !=:sifra", Djelatnik.class)
                .setParameter("uvjet", entitet.getBrojUgovora())
                .setParameter("sifra", entitet.getSifra() == null ? 0 : entitet.getSifra())
                .list();

        if (lista != null && !lista.isEmpty()) {
            throw new MotoException("Broj ugovora je zauzet!");
        }

    }

    private void kontrolaKontakt() throws MotoException {
        String s = entitet.getKontakt();

        if (s == null) {
            throw new MotoException("Kontakt djelatnika mora biti definiran");
        }

        if (s.isEmpty()) {
            throw new MotoException("Kontakt djelatnika ne smije biti prazan");
        }

        char[] ch = s.toCharArray();

        if (!Character.isDigit(ch[0]) && !Character.isLetter(ch[0])) {
            throw new MotoException("Kontakt djelatnika mora počinjati slovom ili brojkom");
        }

    }

    private void kontrolaIBAN() throws MotoException {
        String s = entitet.getIban().trim();

        if (s == null) {
            throw new MotoException("IBAN djelatnika mora biti definiran");
        }

        if (s.isEmpty()) {
            throw new MotoException("IBAN djelatnika ne smije biti prazan");
        }

        char[] ch = s.toCharArray();

        if (!Character.isDigit(ch[0]) && !Character.isLetter(ch[0])) {
            throw new MotoException("IBAN djelatnika mora počinjati slovom ili brojkom");
        }
    }

}
