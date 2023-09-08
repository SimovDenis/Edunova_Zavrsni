package zavrsni.model;

import jakarta.persistence.Entity;

@Entity
public class Kupac extends Entitet {

    private String ime;
    private String prezime;
    private String kontakt;

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

}
