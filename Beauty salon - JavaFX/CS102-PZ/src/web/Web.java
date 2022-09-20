package web;

import entities.Usluga;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Andrijana Jovanovic
 */
public class Web {

    public static void getUsluge() {

        try {
            Document doc = Jsoup.connect("https://www.sredime.rs/beograd/salon-grey2").get();
            Elements elements = doc.select("#vendor-list-container > #sc1616 > .service-list > .service-row.service > .row");

            for (Element el : elements) {
                Usluga usluga = new Usluga();
                String naziv = el.select(".col-xs-8 > .service-name").text();
                String cena = el.select(".col-xs-3.text-right > span > .service-name").text();
                cena = cena.replace(".", "");
                usluga.setNaziv(naziv);
                usluga.setCena(Integer.parseInt(cena));
                controller.CrudUsluga.createFromWeb(usluga);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
