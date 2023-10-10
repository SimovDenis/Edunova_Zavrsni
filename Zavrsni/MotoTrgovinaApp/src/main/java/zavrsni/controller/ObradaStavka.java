/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import zavrsni.model.Proizvod;
import zavrsni.model.Stavka;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public class ObradaStavka extends Obrada<Stavka>{

    @Override
    public List<Stavka> read() {
        return session.createQuery("from Stavka s", Stavka.class).list();
    }        

    @Override
    protected void kontrolaUnos() throws MotoException {
        
    }

    @Override
    protected void kontrolaPromjena() throws MotoException {
        
    }

    @Override
    protected void kontrolaBrisanje() throws MotoException {
        
    }
    
}
