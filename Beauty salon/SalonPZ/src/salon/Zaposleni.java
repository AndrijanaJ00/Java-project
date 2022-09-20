package salon;

public abstract class Zaposleni {
    private String ime;
    private String prezime;

    public Zaposleni() {
    }

    public Zaposleni(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public Zaposleni(Zaposleni o) {
        this.ime = o.ime;
        this.prezime = o.prezime;
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

    @Override
    public String toString() {
        return "Osoba: " + ime + " " + prezime;
    }

    public abstract double racunajZaradu(Salon salon);

}
