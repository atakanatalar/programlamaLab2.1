import javax.swing.ImageIcon;

public class Obje {

    static int altinMiktari = 5;
    static int mantarMiktari = 1;
    private int puan;
    private String isim;
    private ImageIcon imageIcon;
    private Lokasyon lokasyon;

    public Obje() {
    }

    public Obje(int puan, ImageIcon imageIcon, Lokasyon lokasyon, String isim) {
        this.puan = puan;
        this.imageIcon = imageIcon;
        this.lokasyon = lokasyon;
        this.isim = isim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Lokasyon getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(Lokasyon lokasyon) {
        this.lokasyon = lokasyon;
    }
}


