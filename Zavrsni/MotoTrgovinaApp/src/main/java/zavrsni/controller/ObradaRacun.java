/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.util.List;
import zavrsni.model.Racun;
import zavrsni.util.MotoException;

/**
 *
 * @author Polaznik
 */
public class ObradaRacun extends Obrada<Racun>{

    @Override
    public List<Racun> read() {
        return session.createQuery("from Racuni", Racun.class).list();
    }

    @Override
    protected void kontrolaUnos() throws MotoException {        
        kontrolaBrojRacuna();
        kontrolaKupac();
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
        
    }

    private void kontrolaBrojRacuna() throws MotoException {
        String s = entitet.getBrojRacuna();
        char[] ch = s.toCharArray();
        
        for(char c : ch){
            if(!Character.isLetter(ch[0]) || !Character.isDigit(ch[0])){
                throw new MotoException("Broj računa mora početi sa brojem ili slovom");
            }
        }
        
        if (s == null) {
            throw new MotoException("Broj računa mora biti definiran");
        }
        
        if (s.isEmpty()) {
            throw new MotoException("Broj računa ne smije biti prazno");
        }
        
    }

    private void kontrolaKupac() throws MotoException {
        if(entitet.getKupac() == null){
            throw new MotoException("Račun mora imati kupca");
        }
    }
    
    
    
}
