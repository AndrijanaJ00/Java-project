package salon;

public class Menadzer extends Zaposleni {

    public Menadzer() {
    }

    //private Salon salon;

    @Override
    public double racunajZaradu(Salon salon) {
        double zarada = 0;
        for (Tretman t : salon.getListaUkupnihTretmana()) {
            zarada += t.getCena() * 0.2;
        }
        return zarada;
    }

}
