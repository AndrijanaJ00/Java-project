package salon;

import java.util.ArrayList;
import java.util.List;

public class Radnik extends Zaposleni implements Comparable<Radnik> {

    private String id;
    private Specijalnost specijalnost;
    private List<Tretman> obavljeniTretmani = new ArrayList<>();

    public Radnik() {
    }

    public Radnik(String id, Specijalnost specijalnost) {
        this.id = id;
        this.specijalnost = specijalnost;
    }

    public Radnik(String id, Specijalnost specijalnost, String ime, String prezime){
        super(ime, prezime);
        setId(id);
        this.specijalnost = specijalnost;
    }

    public Radnik(String id, Specijalnost specijalnost, Zaposleni o){
        super(o);
        setId(id);
        this.specijalnost = specijalnost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public Specijalnost getSpecijalnost() {
        return specijalnost;
    }

    public void setSpecijalnost(Specijalnost specijalnost) {
        this.specijalnost = specijalnost;
    }

    public List<Tretman> getObavljeniTretmani() {
        return obavljeniTretmani;
    }

    public void setObavljeniTretmani(List<Tretman> obavljeniTretmani) {
        this.obavljeniTretmani = obavljeniTretmani;
    }

    @Override
    public String toString() {
        return "\n---------------------------\n"
                + "Ime radnika: " + this.getIme()
                + "\nPrezime radnika: " + this.getPrezime()
                + "\nID radnika: " + this.getId() + "\n"
                + "Specijalnost: " + this.getSpecijalnost().name();
    }

    @Override
    public double racunajZaradu(Salon salon) {
        double zarada = 0;
        for (Tretman t : obavljeniTretmani) {
            zarada += t.getCena() * 0.5;
        }

        return zarada;
    }

    @Override
    public int compareTo(Radnik t) {
        return this.getSpecijalnost().name().compareTo(t.getSpecijalnost().name());
    }
}
