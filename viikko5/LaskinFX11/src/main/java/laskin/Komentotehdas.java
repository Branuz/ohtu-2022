package laskin;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Komentotehdas {
    private HashMap<Button, Komento> komennot;
    private Sovelluslogiikka sovellus;

    public Komentotehdas(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, sovellus, undo));
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, sovellus, undo));
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, sovellus, undo));

    }

    public Komento hae(Button button) {
        return komennot.get(button);
    }
}
