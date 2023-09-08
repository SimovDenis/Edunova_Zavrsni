package zavrsni.model;

import jakarta.persistence.Entity;

@Entity
public class Djelatnik extends Entitet {

    private String ime;
    private String prezime;
    private String brojUgovora;
    private String iban;
    private String kontakt;

    public Djelatnik(int sifra, String ime, String prezime, String brojUgovora, String iban, String kontakt) {
        super(sifra);
        this.ime = ime;
        this.prezime = prezime;
        this.brojUgovora = brojUgovora;
        this.iban = iban;
        this.kontakt = kontakt;
    }

    public Djelatnik() {

    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

}
