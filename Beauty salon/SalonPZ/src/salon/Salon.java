package salon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Salon {

    private String imeSalona;
    private ArrayList<Radnik> listaRadnika;
    private ArrayList<Klijent> listaKlijenata;
    private ArrayList<Tretman> listaAktivnihTretmana;
    private ArrayList<Tretman> listaUkupnihTretmana;

    public Salon() {
    }

    public Salon(String imeSalona) {
        this.imeSalona = imeSalona;
        listaRadnika = new ArrayList<>();
        listaKlijenata = new ArrayList<>();
        listaAktivnihTretmana = new ArrayList<>();
        listaUkupnihTretmana = new ArrayList<>();
    }

    public String getImeSalona() {
        return imeSalona;
    }

    public void setImeSalona(String imeSalona) {
        this.imeSalona = imeSalona;
    }

    public ArrayList<Radnik> getListaRadnika() {
        return listaRadnika;
    }

    public void setListaRadnika(ArrayList<Radnik> listaRadnika) {
        this.listaRadnika = listaRadnika;
    }

    public ArrayList<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }

    public void setListaKlijenata(ArrayList<Klijent> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }

    public ArrayList<Tretman> getListaTretmana() {
        return listaAktivnihTretmana;
    }

    public void setListaTretmana(ArrayList<Tretman> listaTretmana) {
        this.listaAktivnihTretmana = listaTretmana;
    }

    public ArrayList<Tretman> getListaUkupnihTretmana() {
        return listaUkupnihTretmana;
    }

    public void setListaUkupnihTretmana(ArrayList<Tretman> listaUkupnihTretmana) {
        this.listaUkupnihTretmana = listaUkupnihTretmana;
    }
    
    public void dodajRadnika(Radnik radnik) {
        this.listaRadnika.add(radnik);
    }

    public void dodajKlijenta(Klijent klijent) {
        listaKlijenata.add(klijent);
       
    }
    
    public void zavrsiTretman(String id) {
        for (int i = 0; i < listaAktivnihTretmana.size(); i++) {
            Tretman tre = listaAktivnihTretmana.get(i);
            Radnik radnik = tre.getRadnik();
            if (id.equals(tre.getRadnik().getId())) {
                listaKlijenata.remove(tre.getKlijent());
                listaRadnika.add(radnik);
                System.out.println("\nRadnik " + id + " je slobodan.");
                listaAktivnihTretmana.remove(tre);
                
                listaUkupnihTretmana.add(tre);
                
                List<Tretman> listaTretmanaRadnika = radnik.getObavljeniTretmani();
                listaTretmanaRadnika.add(tre);
                
                racunajZaraduZaTretman(tre);
                racunajZaraduZaRadnika(tre.getRadnik());
                
                //proveriCekaonicu(tre.getCena());
                return;
            }
        }
        System.out.print("\nGreška: radnik ");
        System.out.println(id + " nije nadjen u listi tretmana.");
    }

   public void racunajZaraduZaTretman(Tretman tre) {
        double zarada = tre.getCena();
        
        if (zarada > 0) {
            System.out.println();
            System.out.println("\nZarada za tretman je: ");
            System.out.println( zarada);
        } else {
            System.out.println("\nBesplatan tretman");
        }
    }
   
   public void racunajZaraduZaRadnika(Zaposleni radnik) {
        double zarada = radnik.racunajZaradu(this);
        
        if (zarada > 0) {
            System.out.println();
            System.out.println("\nUkupna zarada za radnika " + radnik.getIme() + " je: ");
            System.out.println( zarada);
        } else {
            System.out.println("\nRadnik:" + radnik.getIme() + " Nema zarade");
        }
    }
   
    public void prikaziKlijente() {
        int n = listaKlijenata.size();
        if (n == 0) {
            System.out.println();
            System.out.println("\nNema klijenata koji čekaju...");
        } else {
            System.out.println("\nKlijenti koji čekaju:");
            for (int i = 0; i < n; i++) {
                System.out.println( listaKlijenata.get(i).toString());
            }
        }

    }

    public void prikaziRadnike() {
        Collections.sort(listaRadnika);
        int n = listaRadnika.size();
        if (n == 0) {
            System.out.println();
            System.out.println("\nNema slobodnih radnika ...");
        } else {
            System.out.println("\nSlobodni radnici:");
            for (int i = 0; i < n; i++) {
                System.out.println( listaRadnika.get(i).toString());
            }
        }

    }

   
    public void prikaziTretman() {
       
        int n = listaAktivnihTretmana.size();
        if (n == 0) {
            System.out.println();
            System.out.println("\nNema tretmana koji su u toku ...");
        } else {
            System.out.println("\nTretmani koji su u toku:");
            for (int i = 0; i < n; i++) {
                System.out.println(listaAktivnihTretmana.get(i).toString() );
            }
        }

    }

  
    public void proveriCekaonicu(double cena) {

        for (int i = 0; i < listaKlijenata.size(); i++) {
            Klijent klijent = listaKlijenata.get(i);
            for (int j = 0; j < listaRadnika.size(); j++) {
                Radnik radnik = listaRadnika.get(j);
                //double cena = 0;
                if (klijent.getVrstaTretmana().equals(radnik.getSpecijalnost().name())) {
                    Tretman tretman = new Tretman(radnik, klijent, cena, LocalDate.MAX);
                    
//radnik.getObavljeniTretmani().add(tretman);

                    listaAktivnihTretmana.add(tretman);
                    listaKlijenata.remove(klijent);
                    i--;
                    listaRadnika.remove(radnik);
                    System.out.println("\nNovi tretman: ");
                    System.out.println(tretman.toString());
                    break;

                }

            }

        }

    }
    
     public void zaradaSvihRadnika() {     
        for (int j = 0; j < listaRadnika.size(); j++) {
            Radnik radnik = listaRadnika.get(j);
            double zarada = radnik.racunajZaradu(this);
        
            if (zarada > 0) {
                System.out.println();
                System.out.println("\nUkupna zarada za radnika "+ radnik.getIme() + " je: " + zarada);
            } else {
                System.out.println("\nRadnik: " + radnik.getIme() + " Nema zarade");
            }
            System.out.println("\n-----------------------------");
        }
     }

     public void zaradaManagera() { 
        Menadzer manager = new Menadzer();
        manager.setIme("Dejan");
      
        double zarada = manager.racunajZaradu(this);

        if (zarada > 0) {
            System.out.println();
            System.out.println("\nUkupna zarada za mendzera "+ manager.getIme() + " je: " + zarada);
        } else {
            System.out.println("\nManager : " + manager.getIme() + " Nema zarade");
        }
        System.out.println("\n-----------------------------");
        
     }

}
