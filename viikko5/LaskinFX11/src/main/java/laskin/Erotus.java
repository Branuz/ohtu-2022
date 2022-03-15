package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    private int previous;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Sovelluslogiikka sovellus, Button undo) {
        super(tuloskentta, syotekentta, nollaa, sovellus, undo);
    }

    @Override
    public void suorita() {
        this.previous = Integer.parseInt(tuloskentta.getText());
        sovellus.miinus(super.fetchInputValue());
        super.setValue(sovellus.tulos());
    }

    @Override
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(previous);
        super.setValue(previous);
    }
    
}
