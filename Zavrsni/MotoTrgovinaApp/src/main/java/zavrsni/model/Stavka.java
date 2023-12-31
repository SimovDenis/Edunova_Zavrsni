/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Denis
 */
@Entity
public class Stavka extends Entitet {

    @ManyToOne
    private Racun racun;
    @ManyToOne
    private Proizvod proizvod;
    private Integer kolicina;

    public Stavka() {
    }

    public Stavka(Racun racun, Proizvod proizvod, Integer kolicina, Integer sifra) {
        super(sifra);
        this.racun = racun;
        this.proizvod = proizvod;
        this.kolicina = kolicina;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return proizvod + "(" + kolicina + ")";
    }

}
