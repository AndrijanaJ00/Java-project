package salon;

public class Klijent {

    public static int brojKarticeTretmana = 1;
    private String vrstaTretmana;

    public Klijent() {
    }

    public Klijent(String vrstaTretmana) {
        this.brojKarticeTretmana++;
        this.vrstaTretmana = vrstaTretmana;
    }

    public int getBrojKarticeTretmana() {
        return brojKarticeTretmana;
    }

    public String getVrstaTretmana() {
        return vrstaTretmana;
    }

    public void setVrstaTretmana(String vrstaTretmana) {
        this.vrstaTretmana = vrstaTretmana;
    }

    @Override
    public String toString() {
        return "\n---------------------------\n"
              
                + "Broj kartice klijenta: " + this.brojKarticeTretmana 
                + "\nVrsta tretmana: " + this.vrstaTretmana;
    }

}
