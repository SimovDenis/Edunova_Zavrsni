/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.util.List;
import zavrsni.model.Djelatnik;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public class ObradaDjelatnik extends Obrada<Djelatnik> {
    
    @Override
    public List<Djelatnik> read() {
        return session.createQuery("from Djelatnik", Djelatnik.class).list();
    }
    
    @Override
    protected void kontrolaUnos() throws MotoException {
        kontrolaIme();
        kontrolaPrezime();
        kontrolaBrojUgovora();
    }
    
    @Override
    protected void kontrolaPromjena() throws MotoException {
        kontrolaUnos();        
    }
    
    @Override
    protected void kontrolaBrisanje() throws MotoException {
        if(!entitet.getRacuni().isEmpty()){
            throw new MotoException("Ne možete obrisati djelatnika koji je već na nekom računu");
        }
    }
    
    private void kontrolaIme() throws MotoException {
        
        String i = entitet.getIme();
        char[] iNiz = i.toCharArray();
        
        for (char c : iNiz) {
            if (Character.isDigit(c)) {
                throw new MotoException("Ime djelatnika ne smije sadržavati brojku");
            }
        }
        
        if (i == null) {
            throw new MotoException("Ime djelatnika mora biti definirano");
        }
        
        if (i.isEmpty()) {
            throw new MotoException("Ime djelatnika ne smije biti prazno");
        }
    }
    
    private void kontrolaPrezime() throws MotoException {
        
        String p = entitet.getPrezime();
        char[] pNiz = p.toCharArray();
        
        for (char c : pNiz) {
            if (Character.isDigit(c)) {
                throw new MotoException("Prezime djelatnika ne smije sadržavati brojku");                
            }
        }
        
        if (p == null) {
            throw new MotoException("Prezime djelatnika mora biti definirano");
        }
        
        if (p.isEmpty()) {
            throw new MotoException("Prezime djelatnika ne smije biti prazno");
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
        
    }
    
}
