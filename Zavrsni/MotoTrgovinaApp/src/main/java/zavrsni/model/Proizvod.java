package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proizvod extends Entitet {

    private String naziv;
    private BigDecimal cijena;
    private Integer garancija;  
    
    @ManyToMany(mappedBy = "proizvodi")
    private List<Racun> racuni;

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Proizvod(String naziv, BigDecimal cijena, Integer garancija, Integer sifra) {
        super(sifra);
        this.naziv = naziv;
        this.cijena = cijena;
        this.garancija = garancija;
    }

    public Proizvod() {
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public Integer getGarancija() {
        return garancija;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public void setGarancija(Integer garancija) {
        this.garancija = garancija;
    }

}
