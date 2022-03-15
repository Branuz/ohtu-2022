package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo; 
    private Komentotehdas komennot;
    private Komento edellinen = null;
 

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.komennot = new Komentotehdas(tuloskentta, syotekentta, plus, miinus, nollaa, undo);
        this.undo = undo;
    }
    
    @Override
    public void handle(Event event) {
        if ( event.getTarget() != undo ) {
            Komento komento = this.komennot.hae((Button)event.getTarget());
            komento.suorita();  
            this.edellinen = komento;
            
        } else {
            this.edellinen.peru();
            this.edellinen = null;
        }                  
    }

}