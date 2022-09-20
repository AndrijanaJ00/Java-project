package salon;

import java.time.LocalDate;

public class Tretman implements Comparable<Tretman> {

    private Radnik radnik;
    private Klijent klijent;
    private double cena;
    private LocalDate date;

    public Tretman() {
    }

    public Tretman(Radnik radnik, Klijent klijent, double cena, LocalDate date) {
        this.radnik = radnik;
        this.klijent = klijent;
        this.cena = cena;
        this.date = date;
    }

    public Tretman(Tretman t) {
        this.radnik = t.radnik;
        this.klijent = t.klijent;
        this.cena = t.cena;
        this.date = t.date;
    }

    public Tretman(Radnik radnik, Klijent klijent) {
        this.radnik = radnik;
        this.klijent = klijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena){
        this.cena = cena;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return radnik.toString() + "\n"
                + klijent.toString();
    }

    @Override
    public int compareTo(Tretman t) {
        return this.getDate().compareTo(t.getDate());
    }

}
