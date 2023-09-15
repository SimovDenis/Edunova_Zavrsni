/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.List;
import zavrsni.model.Operater;
import zavrsni.util.Alati;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public class ObradaOperater extends Obrada<Operater> {

    @Override
    public List<Operater> read() {
        return session.createQuery("from Operater", Operater.class).list();
    }

    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaIme();
        kontrolaPrezime();
        kontrolaOIB();
        kontrolaEmail();
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaUnos();
    }

    public Operater autoriziraj(String email, String lozinka) {
        Operater o;

        try {
            o = session.createQuery("from Operater o where o.email=:email", Operater.class)
                    .setParameter("email", email).getSingleResult();

            Argon2 argon2 = Argon2Factory.create();

            return argon2.verify(o.getLozinka(), lozinka.toCharArray()) ? o : null;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {

    }

    private void kontrolaIme() throws MotoException {
        String i = entitet.getIme();

        if (i == null) {
            throw new MotoException("Ime operatera mora biti definirano");
        }

        if (i.isEmpty()) {
            throw new MotoException("Ime operatera ne smije biti prazno");
        }

        char[] iNiz = i.toCharArray();

        for (char c : iNiz) {
            if (Character.isDigit(c) || !Character.isLetter(iNiz[0])) {
                throw new MotoException("Ime operatera ne smije sadržavati brojku i prvi znak mora biti slovo");
            }
        }
    }

    private void kontrolaPrezime() throws MotoException {
        String i = entitet.getIme();

        if (i == null) {
            throw new MotoException("Prezime operatera mora biti definirano");
        }

        if (i.isEmpty()) {
            throw new MotoException("Prezime operatera ne smije biti prazno");
        }

        char[] iNiz = i.toCharArray();

        for (char c : iNiz) {
            if (Character.isDigit(c) || !Character.isLetter(iNiz[0])) {
                throw new MotoException("Prezime operatera ne smije sadržavati brojku i prvi znak mora biti slovo");
            }
        }

    }

    private void kontrolaOIB() throws MotoException {
        if (!Alati.isValjanOIB(entitet.getOib())) {
            throw new MotoException("OIB nije valjan");
        }

    }

    private void kontrolaEmail() throws MotoException {
        if (!entitet.getEmail().contains("@")) {
            throw new MotoException("Email operatera mora sadržavati @");
        }

    }

}
