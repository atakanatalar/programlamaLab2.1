import java.util.ArrayList;

import javax.swing.JLabel;

public class Puan extends Oyuncu {

    @Override
    public void PuaniGoster(JLabel skorlbl, int skor) {
        super.PuaniGoster(skorlbl, skor);
        skorlbl.setText(skor + "");
    }

    @Override
    public void DusmanTemas(ArrayList<Dusman> dusmanlar, Oyuncu oyuncu, int indis) {
        super.DusmanTemas(dusmanlar, oyuncu, indis);
        switch (dusmanlar.get(indis).getAd()) {
            case "Gargamel":
                oyuncu.setSkor(oyuncu.getSkor() - getGargamelTemas());
                break;
            case "Azman":
                oyuncu.setSkor(oyuncu.getSkor() - getAzmanTemas());
                break;
            default:
                oyuncu.setSkor(oyuncu.getSkor() - 5);
                break;
        }
    }
}