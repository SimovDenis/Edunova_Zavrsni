package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Racun extends Entitet {

    private Date vrijemeKupovine;
    private String brojRacuna;
    private String nacinPlacanja;
    @ManyToOne
    private Kupac kupac;
    @ManyToOne
    private Djelatnik djelatnik;

    @OneToMany(mappedBy = "racun")
    private List<Stavka> stavka;

    public List<Stavka> getStavka() {
        return stavka;
    }

    public void setStavka(List<Stavka> stavka) {
        this.stavka = stavka;
    }

    public Date getVrijemeKupovine() {
        return vrijemeKupovine;
    }

    public void setVrijemeKupovine(Date vrijemeKupovine) {
        this.vrijemeKupovine = vrijemeKupovine;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Djelatnik getDjelatnik() {
        return djelatnik;
    }

    public void setDjelatnik(Djelatnik djelatnik) {
        this.djelatnik = djelatnik;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (getBrojRacuna() == null) {
            sb.append("Racun bez broja racuna, ");
            sb.append(String.valueOf(getSifra()));
        } else if (getBrojRacuna().length() > 10) {
            sb.append(getBrojRacuna().substring(0, 10));
            sb.append("...");
        } else {
            sb.append(getBrojRacuna());
        }

        if (getKupac() != null && getKupac().getIme() != null && !getKupac().getIme().isEmpty()) {
            sb.append(" [");
            sb.append(getKupac().getIme());
            sb.append(" ");
            sb.append(getKupac().getPrezime());
            sb.append("]");
        }

        return sb.toString();
    }

}
