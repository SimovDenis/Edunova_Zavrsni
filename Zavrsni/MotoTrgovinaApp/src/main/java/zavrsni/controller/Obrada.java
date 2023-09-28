package zavrsni.controller;

import java.util.List;
import org.hibernate.Session;
import zavrsni.model.Entitet;
import zavrsni.util.HibernateUtil;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public abstract class Obrada<T extends Entitet> {

    protected T entitet;
    protected Session session;

    public abstract List<T> read();

    protected abstract void kontrolaUnos() throws MotoException;

    protected abstract void kontrolaPromjena() throws MotoException;

    protected abstract void kontrolaBrisanje() throws MotoException;

    public Obrada() {
        session = HibernateUtil.getSession();
    }
    
    public Obrada(T entitet){
        this();
        this.entitet = entitet;
    }

    public void create() throws MotoException {
        kontrolaNull();
        entitet.setSifra(null);
        kontrolaUnos();
        persist();
    }

    public void update() throws MotoException {
        kontrolaNull();
        kontrolaPromjena();
        persist();
    }

    public void delete() throws MotoException {
        kontrolaNull();
        kontrolaBrisanje();
        session.beginTransaction();
        session.remove(entitet);
        session.getTransaction().commit();
    }

    private void kontrolaNull() throws MotoException {
        if (entitet == null) {
            throw new MotoException("Entitet je null");
        }

    }

    private void persist() {
        session.beginTransaction();
        session.persist(entitet);
        session.getTransaction().commit();
    }

    public T getEntitet() {
        return entitet;
    }

    public void setEntitet(T entitet) {
        this.entitet = entitet;
    }
    
    public void refresh(){
        if(entitet!=null){
            session.refresh(entitet);
        }
    }

}
