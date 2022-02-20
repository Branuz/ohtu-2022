package ohtu;

import java.util.ArrayList;
import java.util.List;

public class Ostoskori {

    private List<Ostos> shoppingList;

    public Ostoskori() {
        this.shoppingList = new ArrayList<>();
    }
    public int tavaroitaKorissa() {
        // kertoo korissa olevien tavaroiden lukumäärän
        // eli jos koriin lisätty 2 kpl tuotetta "maito", 
        //   tulee metodin palauttaa 2 
        // jos korissa on 1 kpl tuotetta "maito" ja 1 kpl tuotetta "juusto", 
        //   tulee metodin palauttaa 2 
        int itemAmount = 0;
        
        for(Ostos o: shoppingList) {
            itemAmount+= o.lukumaara();
        }

        return itemAmount;
    }
 
    public int hinta() {
        // kertoo korissa olevien tuotteiden yhteenlasketun hinnan
        int combinedPrice = 0;
        for(Ostos o: shoppingList) {
            combinedPrice+= o.hinta();
        }
 
        return combinedPrice;
    }
 
    public void lisaaTuote(Tuote lisattava) {
        boolean found = false;
        for(Ostos o: shoppingList) {
            if(o.tuotteenNimi().equals(lisattava.getNimi())) {
                o.muutaLukumaaraa(1);
                found = true;
                break;
            }
        }

        if(!found) {
            shoppingList.add(new Ostos(lisattava));
        }
    }
 
    public void poista(Tuote poistettava) {
        // poistaa tuotteen
        for(Ostos o: shoppingList) {
            if(o.tuotteenNimi().equals(poistettava.getNimi())) {
                o.muutaLukumaaraa(-1);
                if(o.lukumaara() == 0) {
                    shoppingList.remove(o);
                }
                break;
            }
        }
    }
 
    public List<Ostos> ostokset() {
        // palauttaa listan jossa on korissa olevat ostokset
 
        return shoppingList;
    }
 
    public void tyhjenna() {
        // tyhjentää korin
    }
}
