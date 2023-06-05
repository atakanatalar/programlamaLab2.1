
import java.util.ArrayList;

import javax.swing.JLabel;

public class Oyuncu extends Karakter {

    private int skor;
    private static int hamle = 0;
    private int hareketHizi;
    private int gargamelTemas;
    private int azmanTemas;

    public Oyuncu() {
    }

    public Oyuncu(int id, String ad, String tur, Lokasyon lokasyon, int skor) {
        super(id, ad, tur, lokasyon);
        this.skor = skor;
    }

    public void PuaniGoster(JLabel skorlbl, int skor) {
        skorlbl.setText(skor + "");
    }

    public void DusmanTemas(ArrayList<Dusman> dusmanlar, Oyuncu oyuncu, int indis) {
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public static int getHamle() {
        return hamle;
    }

    public static void setHamle(int hamle) {
        Oyuncu.hamle = hamle;
    }

    public int getGargamelTemas() {
        return gargamelTemas;
    }

    public void setGargamelTemas(int gargamelTemas) {
        this.gargamelTemas = gargamelTemas;
    }

    public int getAzmanTemas() {
        return azmanTemas;
    }

    public void setAzmanTemas(int azmanTemas) {
        this.azmanTemas = azmanTemas;
    }

    public int getHareketHizi() {
        return hareketHizi;
    }

    public void setHareketHizi(int hareketHizi) {
        this.hareketHizi = hareketHizi;
    }
}
