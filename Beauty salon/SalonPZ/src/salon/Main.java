package salon;

import java.io.*;
import java.util.*;
import salonException.PogresnaSpecijalnostException;

public class Main {

    private static Scanner input = new Scanner(System.in);

    private static Salon salon = new Salon("Ann");
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        try {
            reader = new BufferedReader(new FileReader("izvestaj.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                Radnik r = new Radnik();
                r.setIme(line);
                r.setPrezime(reader.readLine());
                r.setId(reader.readLine());
                if (reader.readLine().equals("frizer")) {
                    r.setSpecijalnost(Specijalnost.frizer);
                } else {
                    r.setSpecijalnost(Specijalnost.sminker);
                }
                reader.readLine();
                salon.dodajRadnika(r);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        dodajZaposleneGUI();
        otvoriCekaonicuGUI();
    }

    public static void dodajZaposleneGUI() throws IOException {
        String ime, prezime, id;
        String specijalnost = "";
        String dodajJos = "";

        System.out.println("Salon lepote \"" + salon.getImeSalona() + "\"");
        System.out.println("\nUnesite listu radnika u smeni:");
        System.out.println();

        BufferedWriter writter = null;
        try {
            writter = new BufferedWriter(new FileWriter("izvestaj.txt", true));
        } catch (IOException ex) {
            System.out.println("Ne postojeci fajl");
        }

        do {

            System.out.print("Ime radnika: ");
            ime = input.next();
            System.out.print("Prezime radnika: ");
            prezime = input.next();
            System.out.print("ID radnika: ");
            id = input.next();

            while (true) {
                System.out.print("Specijalnost: ");
                try {
                    String proba = input.next();
                    if (proba.equalsIgnoreCase("frizer") || proba.equalsIgnoreCase("sminker")) {
                        specijalnost = proba;
                        break;
                    } else {
                        throw new PogresnaSpecijalnostException("Morate uneti frizer ili sminker!");
                    }
                } catch (PogresnaSpecijalnostException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            Radnik radnik = new Radnik(id, Specijalnost.valueOf(specijalnost), ime, prezime);
            salon.dodajRadnika(radnik);

            writter.write(ime);
            writter.newLine();
            writter.write(prezime);
            writter.newLine();
            writter.write(id);
            writter.newLine();
            writter.write(specijalnost);
            writter.newLine();
            writter.write("***************************");
            writter.newLine();
            writter.flush();
            System.out.println("\nDa li zelite da unesete jos radnika (da/ne)?");
            input.nextLine();
            dodajJos = input.nextLine();
            System.out.println();
            if (dodajJos.equalsIgnoreCase("ne")) {
                try {
                    writter.close();
                } catch (IOException ex) {
                    System.out.println("Ne postojeci fajl");
                }
            }
        } while (dodajJos.equalsIgnoreCase("DA"));
    }

    public static void otvoriCekaonicuGUI() {
        int opcija;

        do {
            prikaziOpcije();
            opcija = input.nextInt();
            input.nextLine();
            switch (opcija) {
                case 1: // novi klijennt
                    dodajKlijentaGUI();
                    break;
                case 2: //zavrsi tretman
                    zavrsiTretmanGUI();
                    break;
                case 3: //lista klijenata u cekaonici
                    salon.prikaziKlijente();
                    break;
                case 4: //lista slobodnih radnika
                    salon.prikaziRadnike();
                    break;
                case 5: //lista tretmana
                    salon.prikaziTretman();
                    break;
                case 6:
                    salon.zaradaSvihRadnika();
                    break;
                case 7:
                    salon.zaradaManagera();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("\nIzabrali ste pogrešnu opciju!");
            }
        } while (opcija != 8);
    }

    public static void prikaziOpcije() {
        System.out.println();
        System.out.println("-----------Opcije menija-----------");
        System.out.println(" 1. Novi klijent u cekaonici");
        System.out.println(" 2. Završen tretman");
        System.out.println(" 3. Lista klijenata u čekaonici");
        System.out.println(" 4. Lista slobodnih radnika");
        System.out.println(" 5. Lista trenutnih tretmana");
        System.out.println(" 6. Zarada svih radnika");
        System.out.println(" 7. Zarada menadzera");
        System.out.println(" 8. Kraj rada");
        System.out.print("Unesite broj opcije: ");

    }

    public static void dodajKlijentaGUI() {

        String vrstaTretmana;
        double cena;
        System.out.println();
        System.out.println(" Broj kartice tretmana: " + Klijent.brojKarticeTretmana);
     
        while (true) {
            System.out.print(" Vrsta tretmana: ");
            try {
                String probaVrstaTretmana = input.nextLine();
                if (probaVrstaTretmana.equalsIgnoreCase("frizer") || probaVrstaTretmana.equalsIgnoreCase("sminker")) {
                    vrstaTretmana = probaVrstaTretmana;
                    break;
                } else {
                    throw new PogresnaSpecijalnostException("Morate uneti frizer ili sminker!");
                }
            } catch (PogresnaSpecijalnostException ex) {
                System.err.println(ex.getMessage());
            }
        }
        System.out.println(" Unesite cenu tretmana: ");
        cena = input.nextDouble();
        System.out.println();
        Klijent klijent = new Klijent(vrstaTretmana);
        salon.dodajKlijenta(klijent);
        salon.proveriCekaonicu(cena);

    }

    public static void zavrsiTretmanGUI() {
        String id;
        System.out.println();
         if (salon.getListaTretmana().size() > 0) {
            System.out.print("\nID radnika kod koga je završen tretman: ");
            id = input.next();
            salon.zavrsiTretman(id);
        } else {
            System.out.println("Trenutno nema tretmana koji su u toku!");
        }
    }
}
