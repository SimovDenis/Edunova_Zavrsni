package zavrsni.util;

import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import zavrsni.model.Djelatnik;
import zavrsni.model.Kupac;
import zavrsni.model.Proizvod;
import zavrsni.model.Racun;

/**
 *
 * @author Denis
 */
public class PocetniInsert {
    
    private static final int BROJ_DJELATNIKA = 8;
    private static final int BROJ_KUPACA = 200;
    private static final int BROJ_PROIZVODA = 600;
    private static final int BROJ_RACUNA = 100;
    
    private Faker faker;
    private Session session;
    private List<Djelatnik> djelatnici;
    private List<Kupac> kupci;
    private List<Proizvod> proizvodi;
    
    public PocetniInsert() {
        faker = new Faker();
        session = HibernateUtil.getSession();
        djelatnici = new ArrayList<>();
        kupci = new ArrayList<>();
        proizvodi = new ArrayList<>();
        session.getTransaction().begin();
        kreirajDjelatnike();
        kreirajKupce();
        kreirajProizvode();
        kreirajRacune();
        session.getTransaction().commit();
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
    
    private void kreirajProizvode() {
        Proizvod p;
        for (int i = 0; i < BROJ_PROIZVODA; i++) {
            p = new Proizvod();
            p.setNaziv(faker.pokemon().name());
            p.setCijena(new BigDecimal(faker.number().numberBetween(10, 12000)));
            p.setGarancija(faker.number().numberBetween(0, 20));
            session.persist(p);
            proizvodi.add(p);
        }
    }
    
    private void kreirajRacune() {
        Racun r;
        List<Proizvod> p;
        for (int i = 0; i < BROJ_RACUNA; i++) {
            r = new Racun();
            r.setBrojRacuna(faker.business().creditCardNumber());
            r.setNacinPlacanja(faker.currency().name());
            r.setVrijemeKupovine(faker.date().birthday(0, 3));
            r.setDjelatnik(djelatnici.get(faker.number().numberBetween(0, BROJ_DJELATNIKA - 1)));
            r.setKupac(kupci.get(faker.number().numberBetween(0, BROJ_KUPACA - 1)));
            p = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(1, 100); j++) {
                proizvodi.get(faker.number().numberBetween(0, BROJ_PROIZVODA - 1));
            }
            r.setProizvodi(p);
            session.persist(r);            
        }
    }
    
}
