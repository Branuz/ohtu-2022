package ohtu;

import java.util.List;

public class Ostoskori {
 
    public int tavaroitaKorissa() {
        // kertoo korissa olevien tavaroiden lukumäärän
        // eli jos koriin lisätty 2 kpl tuotetta "maito", 
        //   tulee metodin palauttaa 2 
        // jos korissa on 1 kpl tuotetta "maito" ja 1 kpl tuotetta "juusto", 
        //   tulee metodin palauttaa 2 
        int itemAmount = 0;  

        return itemAmount;
    }
 
    public int hinta() {
        // kertoo korissa olevien tuotteiden yhteenlasketun hinnan
        int combinedPrice = 0;
 
        return combinedPrice;
    }
 
    public void lisaaTuote(Tuote lisattava) {
        // lisää tuotteen
    }
 
    public void poista(Tuote poistettava) {
        // poistaa tuotteen
    }
 
    public List<Ostos> ostokset() {
        // palauttaa listan jossa on korissa olevat ostokset
 
        return null;
    }
 
    public void tyhjenna() {
        // tyhjentää korin
    }
}
