package zavrsni.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Proizvod extends Entitet {

    private String naziv;
    private BigDecimal cijena;
    private Integer garancija;

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
