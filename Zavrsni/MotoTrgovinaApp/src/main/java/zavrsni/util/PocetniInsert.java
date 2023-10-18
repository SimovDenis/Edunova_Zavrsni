package zavrsni.util;

import com.github.javafaker.Faker;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import zavrsni.controller.ObradaOperater;
import zavrsni.model.Djelatnik;
import zavrsni.model.Kupac;
import zavrsni.model.Operater;
import zavrsni.model.Proizvod;
import zavrsni.model.Racun;
import zavrsni.model.Stavka;

/**
 *
 * @author Denis
 */
public class PocetniInsert {

    private static final int BROJ_DJELATNIKA = 8;
    private static final int BROJ_KUPACA = 80;
    private static final int BROJ_PROIZVODA = 600;
    private static final int BROJ_RACUNA = 100;
    private static final int BROJ_STAVKI = 2000;

    private Faker faker;
    private Session session;
    private List<Djelatnik> djelatnici;
    private List<Kupac> kupci;
    private List<Stavka> stavke;
    private List<Proizvod> proizvodi;
    private List<Racun> racuni;    

    public PocetniInsert() {
        faker = new Faker();
        session = HibernateUtil.getSession();
        djelatnici = new ArrayList<>();
        kupci = new ArrayList<>();
        proizvodi = new ArrayList<>();
        racuni = new ArrayList<>();
        stavke = new ArrayList<>();        
        session.getTransaction().begin();
        kreirajDjelatnike();
        kreirajKupce();
        kreirajProizvode();
        kreirajRacune();
        kreirajStavke();                           
        session.getTransaction().commit();
        lozinka();
    }

    private void kreirajDjelatnike() {
        Djelatnik d;
        for (int i = 0; i < BROJ_DJELATNIKA; i++) {
            d = new Djelatnik();
            d.setIme(faker.name().firstName());
            d.setPrezime(faker.name().lastName());
            d.setKontakt(faker.internet().emailAddress());
            d.setBrojUgovora(faker.finance().creditCard());
            d.setIban(faker.finance().iban());
            session.persist(d);
            djelatnici.add(d);
        }
    }

    private void kreirajKupce() {
        Kupac k;
        for (int i = 0; i < BROJ_KUPACA; i++) {
            k = new Kupac();
            k.setIme(faker.name().firstName());
            k.setPrezime(faker.name().lastName());
            k.setKontakt(faker.internet().emailAddress());
            session.persist(k);
            kupci.add(k);
        }

    }

    private void kreirajRacune() {
        Racun r;
        for (int i = 0; i < BROJ_RACUNA; i++) {
            r = new Racun();
            r.setBrojRacuna(faker.number().numberBetween(100, 900) + "-" + faker.number().numberBetween(2019, 2024));
            r.setNacinPlacanja(faker.currency().name());
            r.setVrijemeKupovine(faker.date().birthday(0, 3));
            r.setDjelatnik(djelatnici.get(faker.number().numberBetween(0, BROJ_DJELATNIKA - 1)));
            r.setKupac(kupci.get(faker.number().numberBetween(0, BROJ_KUPACA - 1)));
            session.persist(r);
            racuni.add(r);
        }
    }

    private void kreirajStavke() {
        Stavka s;
        for (int i = 0; i < BROJ_STAVKI; i++) {
            s = new Stavka();
            s.setProizvod(proizvodi.get(faker.number().numberBetween(0, BROJ_PROIZVODA - 1)));
            s.setRacun(racuni.get(faker.number().numberBetween(0, BROJ_RACUNA - 1)));
            s.setKolicina(faker.number().numberBetween(1, 10));
            session.persist(s);
            stavke.add(s);
        }
    }

    private void kreirajProizvode() {
        Proizvod p;        
        for (int i = 0; i < BROJ_PROIZVODA; i++) {
            p = new Proizvod();
            p.setNaziv(faker.pokemon().name());
            p.setCijena(new BigDecimal(faker.number().numberBetween(10, 5000)));
            p.setGarancija(faker.number().numberBetween(0, 20));
            session.persist(p);
            proizvodi.add(p);
        }
    }
    
    private void lozinka() {
        Argon2 argon2 = Argon2Factory.create();

        String hash = argon2.hash(10, 65536, 1, "oper".toCharArray());

        ObradaOperater oo = new ObradaOperater();
        Operater o = new Operater();
        o.setIme("Denis");
        o.setPrezime("Simov");
        o.setEmail("dsimov@mototrgovina.hr");
        o.setUloga("admin");
        o.setOib("14503725279");
        o.setLozinka(hash);

        oo.setEntitet(o);

        try {
            oo.create();
        } catch (MotoException ex) {
            System.out.println(ex.getPoruka());
        }
    }

}
