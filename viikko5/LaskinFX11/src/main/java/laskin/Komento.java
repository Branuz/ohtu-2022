package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {

    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Sovelluslogiikka sovellus;
    protected Button undo;

    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Sovelluslogiikka sovellus, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.sovellus = sovellus;
        this.undo = undo;
    }

    public int fetchInputValue() {
        int value = 0;
        
        try {
            value = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        return value;
    }

    public void setValue(int value) {
        syotekentta.setText("");
        tuloskentta.setText("" + value);

        if (value == 0) {
            nollaa.disableProperty().set(true);

        } else {
            nollaa.disableProperty().set(false);
        }
        
        undo.disableProperty().set(false);
    }
    

    public abstract void peru();

    public abstract void suorita();
}
