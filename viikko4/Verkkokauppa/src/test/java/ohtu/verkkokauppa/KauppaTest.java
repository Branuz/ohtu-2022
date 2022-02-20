package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.common.base.Verify;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setup() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
        

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaaParameterilla() {

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));   
    }

    @Test
    public void kaksiEriTuotetta() {
        when(viite.uusi()).thenReturn(100);

        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "peruna", 2));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("mikko", "111222");

        verify(pankki).tilisiirto(eq("mikko"), eq(100), eq("111222"), anyString(),eq(7));

    }

    @Test
    public void kaksiSamaaTuotetta() {
        when(viite.uusi()).thenReturn(150);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("mikko", "111222");
        verify(pankki).tilisiirto(eq("mikko"), eq(150), eq("111222"), anyString(),eq(10));
    }

    @Test
    public void lisataanTuoteSekaLoppunutTuote() {
        when(viite.uusi()).thenReturn(150);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "peruna", 2));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("mikko", "111222");
        verify(pankki).tilisiirto(eq("mikko"), eq(150), eq("111222"), anyString(),eq(5));

    }

    @Test
    public void asiointiNollaaOstokset() {
        when(viite.uusi()).thenReturn(150);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("mikko", "111222");
        verify(pankki).tilisiirto(eq("mikko"), eq(150), eq("111222"), anyString(),eq(5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("mikko", "222111");
        verify(pankki).tilisiirto(eq("mikko"), eq(150), eq("222111"), anyString(),eq(5));
    }

    @Test
    public void uusiViiteTapahtumissa() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("mikko", "1234");
        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("jarkko", "4321");
        verify(viite, times(2)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("jorma", "2233");
        verify(viite, times(3)).uusi();

    }

    @Test
    public void koristaPoistaminenToimii() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        verify(varasto).palautaVarastoon(new Tuote(1, "maito", 5));
    }
}