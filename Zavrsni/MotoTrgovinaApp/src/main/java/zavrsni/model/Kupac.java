package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Kupac extends Entitet {

    private String ime;
    private String prezime;
    private String kontakt;

    @OneToMany(mappedBy = "kupac")
    private List<Racun> racuni;

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Kupac(int sifra, String ime, String prezime, String kontakt) {
        super(sifra);
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
    }

    public Kupac() {
        super();
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
