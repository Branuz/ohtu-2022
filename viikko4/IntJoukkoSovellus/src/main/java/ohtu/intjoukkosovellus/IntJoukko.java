
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int size = 5, // aloitustalukon koko
                            basicGrowth = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0 ;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[size];
        this.kasvatuskoko = basicGrowth;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            ljono = new int[size];
        } else {
            ljono = new int[kapasiteetti];
        }
        this.kasvatuskoko = basicGrowth;

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            ljono = new int[size];
            this.kasvatuskoko = basicGrowth;
        } else {
            ljono = new int[kapasiteetti];
            this.kasvatuskoko = kasvatuskoko;
        }
    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            ljono[0] = luku;
            alkioidenLkm++;
            return true;
        } 

        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;

            if (alkioidenLkm == ljono.length) {
                ljono = Arrays.copyOf(ljono, alkioidenLkm + kasvatuskoko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int indexLocation = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                indexLocation = i;
                break;
            }
        }

        
        if (indexLocation != -1) {
            for (int j = indexLocation; j < alkioidenLkm - 1; j++) {
                ljono[j] = ljono[j + 1];
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    public int getLukumaara() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }else {

            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }

            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = Arrays.copyOf(ljono, alkioidenLkm);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko combinedArray = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            combinedArray.lisaa(aTaulu[i]);
        }

        for (int i = 0; i < bTaulu.length; i++) {
            combinedArray.lisaa(bTaulu[i]);
        }

        return combinedArray;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko cut = new IntJoukko();
        int[] aTaulu = a.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            if(b.kuuluu(aTaulu[i])) {
                cut.lisaa(aTaulu[i]);
            }
        }

        return cut;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko difference = new IntJoukko();
        int[] aTaulu = a.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            if(!b.kuuluu(aTaulu[i])) {
                difference.lisaa(aTaulu[i]);
            }
        }

        return difference;
    }
        
}
