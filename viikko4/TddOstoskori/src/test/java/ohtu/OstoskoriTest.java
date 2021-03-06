package ohtu;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    Ostoskori kori;

    @Before
    public void setUp() {
        kori = new Ostoskori();
    }

    // step 1
    @Test
    public void ostoskorinHintaJaTavaroidenMaaraAlussa() { 
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }

    // step 2
    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiTuote() {
        Tuote maito = new Tuote("maito", 3);
 
        kori.lisaaTuote(maito);
        assertEquals(1, kori.tavaroitaKorissa());
    }

    // step 3
    @Test
    public void yhdenTuotteenLisäämisenJälkeenOstoskorinHintaOnSamaKuinTuotteenHinta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        assertEquals(3, kori.hinta());
    }

    // step 4    
    @Test
    public void kahdenTuotteenJälkeenKorissaKaksiTuotetta() {
        Tuote maito = new Tuote("maito", 3);
        Tuote peruna = new Tuote("peruna", 2);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(peruna);
        assertEquals(2, kori.tavaroitaKorissa());
    }
    // step 5
    @Test
    public void kahdenEriTuotteenHintaOikea() {
        Tuote maito = new Tuote("maito", 3);
        Tuote peruna = new Tuote("peruna", 2);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(peruna);
        assertEquals(5, kori.hinta());
    }

    // step 6
    @Test
    public void kahdenSamanTuotteenJälkeenKorissaKaksiTavaraa() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        assertEquals(2, kori.tavaroitaKorissa());
    }

    // step 7
    @Test
    public void kahdenSamanTuotteenJälkeenKahdenHinta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        assertEquals(6, kori.hinta());
    }

    // step 8
    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlio() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        assertEquals(1, kori.ostokset().size());
    }

    // step 9
    @Test
    public void yhdenTuotteenLisaamisenKorissaYksiOstosOlioJollaOikeaTuotteenNimiJaMaara() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
 
        Ostos ostos = kori.ostokset().get(0);
 
        assertEquals("maito", ostos.tuotteenNimi());
        assertEquals(1, ostos.lukumaara());
    }
    
    // step 10
    @Test
    public void kahdenTuotteenJälkeenKorissaKaksiTuotettaMäärältä() {
        Tuote maito = new Tuote("maito", 3);
        Tuote peruna = new Tuote("peruna", 2);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(peruna);
        assertEquals(2, kori.ostokset().size());
    }

    // step 11
    @Test
    public void kahdenSamanTuotteenJalkeenOstoskorissaYksiOstos() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        assertEquals(1, kori.ostokset().size());
        assertEquals(2, kori.tavaroitaKorissa());
    }

    //step 12
    @Test
    public void kahdenSamanLisaamisenJalkeenNimiJaMaaraOikein() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        assertEquals(1, kori.ostokset().size());
        assertEquals(2, kori.tavaroitaKorissa());
        assertEquals("maito", kori.ostokset().get(0).tuotteenNimi());
    }

    //step 13
    @Test
    public void korissaSamanTuotteenPoistoOnnistuu() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        kori.poista(maito);

        assertEquals(1, kori.tavaroitaKorissa());
    }

    //step 14
    @Test
    public void koriOnTyhjaViimeisenTuotteenPoistonJalkeen() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.poista(maito);

        assertEquals(0, kori.tavaroitaKorissa());
        assertEquals(0, kori.ostokset().size());
    }

    //step 15
    @Test
    public void korinTyhjennysOnnistuu() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.tyhjenna();

        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
        assertEquals(0, kori.ostokset().size());

    }
}
