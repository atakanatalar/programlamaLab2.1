
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Font;

public class Labirent extends JFrame {

    Random random = new Random();
    ArrayList<Dusman> dusmanlar;
    ArrayList<Obje> objeler;
    List<Lokasyon> path;
    private JPanel contentPane;
    int[][] labirent;
    JLabel[][] jLabels;
    JLabel skorLbl;
    ImageIcon oyuncuIcon = null;
    Oyuncu oyuncu;
    Puan puan;

    public Labirent(int id) {

        arayuzOlustur();
        karakterSecim(id);
        labirentOku();
        labirentOlustur();
        karakterOku();
        karakterYerlestir();
        objeIslemleri();
        karakterKontrolleri(0);
    }

    public void arayuzOlustur() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(225, 20, 1000, 840);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        JLabel lblNewLabel = new JLabel("Skor = ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(780, 20, 107, 40);
        contentPane.add(lblNewLabel);

        skorLbl = new JLabel("");
        skorLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
        skorLbl.setBounds(880, 20, 160, 40);
        contentPane.add(skorLbl);

        JLabel sirineLbl = new JLabel("");
        sirineLbl.setBounds(905, 510, 80, 80);
        sirineLbl.setIcon(new ImageIcon("sirine.png"));
        contentPane.add(sirineLbl);
    }

    public void karakterSecim(int id) {
        puan = new Puan();
        switch (id) {
            case 1:
                oyuncu = new Oyuncu(0, "Gozluklu Sirin", "Oyuncu", new Lokasyon(6, 5), 20);
                oyuncu.setHareketHizi(2);
                puan.setGargamelTemas(15);
                puan.setAzmanTemas(5);
                oyuncuIcon = new ImageIcon("gozlukluSirin.png");
                break;
            case 2:
                oyuncu = new Oyuncu(1, "Tembel Sirin", "Oyuncu", new Lokasyon(6, 5), 20);
                oyuncu.setHareketHizi(1);
                puan.setGargamelTemas(15);
                puan.setAzmanTemas(5);
                oyuncuIcon = new ImageIcon("tembelSirin.png");
        }
        oyuncu.PuaniGoster(skorLbl, oyuncu.getSkor());
    }

    public void labirentOku() {
        int yatay = 0, dikey = 0;
        File file = new File("harita.txt");
        labirent = new int[11][13];
        int number;
        try {
            Scanner scanner = new java.util.Scanner(file);
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
            }

            while (scanner.hasNext()) {
                number = scanner.nextInt();
                labirent[dikey][yatay] = number;
                yatay++;
                if (yatay > 12) {
                    yatay = 0;
                    dikey++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void labirentOlustur() {
        int labelW = 65, labelL = 65, labelX = labelW, labelY = labelL;
        jLabels = new JLabel[13][11];
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                if (labirent[i][j] == 0) {
                    JLabel lbl = new JLabel();
                    lbl.setBounds(labelX, labelY, labelW, labelL);
                    lbl.setOpaque(true);
                    lbl.setBackground(Color.darkGray);
                    lbl.setBorder(BorderFactory.createLineBorder(Color.black));
                    contentPane.add(lbl);
                    jLabels[j][i] = lbl;
                } else {
                    JLabel lbl = new JLabel();
                    lbl.setBounds(labelX, labelY, labelW, labelL);
                    lbl.setOpaque(true);
                    lbl.setBackground(Color.white);
                    lbl.setBorder(BorderFactory.createLineBorder(Color.black));
                    contentPane.add(lbl);
                    jLabels[j][i] = lbl;
                }

                labelX += labelW;
            }
            labelX = labelW;
            labelY += labelL;
        }
    }

    public void karakterOku() {
        File file = new File("harita.txt");
        dusmanlar = new ArrayList<>();
        String satir, isim, kapi;
        try {
            Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                satir = scanner.nextLine();
                if (satir.contains("Karakter:")) {
                    int a = satir.indexOf(',');
                    isim = satir.substring(9, a);
                    kapi = satir.substring(satir.length() - 1);

                    switch (kapi) {
                        case "A":
                            dusmanlar.add(new Dusman(1, isim, "Dusman", new Lokasyon(3, 0), new ImageIcon(isim.toString().toLowerCase() + ".png"), kapi));
                            break;
                        case "B":
                            dusmanlar.add(new Dusman(1, isim, "Dusman", new Lokasyon(10, 0), new ImageIcon(isim.toString().toLowerCase() + ".png"), kapi));
                            break;
                        case "C":
                            dusmanlar.add(new Dusman(1, isim, "Dusman", new Lokasyon(0, 5), new ImageIcon(isim.toString().toLowerCase() + ".png"), kapi));
                            break;
                        case "D":
                            dusmanlar.add(new Dusman(1, isim, "Dusman", new Lokasyon(3, 10), new ImageIcon(isim.toString().toLowerCase() + ".png"), kapi));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void karakterYerlestir() {
        jLabels[oyuncu.getLokasyon().getX()][oyuncu.getLokasyon().getY()].setIcon(oyuncuIcon);

        for (int i = 0; i < dusmanlar.size(); i++) {
            jLabels[dusmanlar.get(i).getLokasyon().getX()][dusmanlar.get(i).getLokasyon().getY()].setIcon(dusmanlar.get(i).getImageIcon());
        }
    }

    public void objeIslemleri() {

        Lokasyon[] altinLokasyon = new Lokasyon[Obje.altinMiktari];
        Lokasyon[] mantarLokasyon = new Lokasyon[Obje.mantarMiktari];
        objeler = new ArrayList<>();

        Timer myTimer = new Timer();
        TimerTask altinOlustur = new TimerTask() {

            public void run() {
                for (int i = 0; i < Obje.altinMiktari; i++) {
                    do {
                        altinLokasyon[i] = new Lokasyon(random.nextInt(labirent[0].length), random.nextInt(labirent.length));
                    } while (labirent[altinLokasyon[i].getY()][altinLokasyon[i].getX()] == 0);

                    objeler.add(new Obje(5, new ImageIcon("altin.png"), altinLokasyon[i], "altin"));
                }

                for (int i = 0; i < objeler.size(); i++) {
                    if (objeler.get(i).getIsim() == "altin")
                        jLabels[objeler.get(i).getLokasyon().getX()][objeler.get(i).getLokasyon().getY()].setIcon(objeler.get(i).getImageIcon());
                }
            }
        };

        TimerTask altinSil = new TimerTask() {
            public void run() {
                for (int i = objeler.size() - 1; i >= 0; i--) {
                    if (objeler.get(i).getIsim() == "altin") {
                        jLabels[objeler.get(i).getLokasyon().getX()][objeler.get(i).getLokasyon().getY()].setIcon(null);
                        objeler.remove(i);
                    }
                }
            }
        };

        TimerTask mantarOlustur = new TimerTask() {
            public void run() {
                do {
                    mantarLokasyon[0] = new Lokasyon(random.nextInt(labirent[0].length), random.nextInt(labirent.length));
                } while (labirent[mantarLokasyon[0].getY()][mantarLokasyon[0].getX()] == 0);

                objeler.add(new Obje(50, new ImageIcon("mantar.png"), mantarLokasyon[0], "mantar"));

                for (int i = 0; i < objeler.size(); i++) {
                    if (objeler.get(i).getIsim() == "mantar")
                        jLabels[objeler.get(i).getLokasyon().getX()][objeler.get(i).getLokasyon().getY()].setIcon(objeler.get(i).getImageIcon());
                }

            }
        };

        TimerTask mantarSil = new TimerTask() {
            public void run() {
                for (int i = objeler.size() - 1; i >= 0; i--) {
                    if (objeler.get(i).getIsim() == "mantar") {
                        jLabels[objeler.get(i).getLokasyon().getX()][objeler.get(i).getLokasyon().getY()].setIcon(null);
                        objeler.remove(i);
                    }
                }
            }
        };

        myTimer.schedule(altinOlustur, 5000, 10000);
        myTimer.schedule(altinSil, 10000, 10000);
        myTimer.schedule(mantarOlustur, 10000, 17000);
        myTimer.schedule(mantarSil, 17000, 17000);
    }

    public void karakterKontrolleri(int hamle) {
        KeyListener keyListener = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {

                jLabels[oyuncu.getLokasyon().getX()][oyuncu.getLokasyon().getY()].setIcon(null);
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (oyuncu.getLokasyon().getY() - 1 >= 0 && labirent[oyuncu.getLokasyon().getY() - 1][oyuncu.getLokasyon().getX()] != 0) {
                        oyuncu.getLokasyon().setY(oyuncu.getLokasyon().getY() - 1);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (oyuncu.getLokasyon().getY() + 1 < labirent.length && labirent[oyuncu.getLokasyon().getY() + 1][oyuncu.getLokasyon().getX()] != 0) {
                        oyuncu.getLokasyon().setY(oyuncu.getLokasyon().getY() + 1);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (oyuncu.getLokasyon().getX() - 1 >= 0 && labirent[oyuncu.getLokasyon().getY()][oyuncu.getLokasyon().getX() - 1] != 0) {
                        oyuncu.getLokasyon().setX(oyuncu.getLokasyon().getX() - 1);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (oyuncu.getLokasyon().getX() + 1 < labirent[0].length && labirent[oyuncu.getLokasyon().getY()][oyuncu.getLokasyon().getX() + 1] != 0) {
                        oyuncu.getLokasyon().setX(oyuncu.getLokasyon().getX() + 1);
                    }
                }
                jLabels[oyuncu.getLokasyon().getX()][oyuncu.getLokasyon().getY()].setIcon(oyuncuIcon);

                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
                    for (int i = 0; i < objeler.size(); i++) {

                        if (oyuncu.getLokasyon().getX() == objeler.get(i).getLokasyon().getX() && oyuncu.getLokasyon().getY() == objeler.get(i).getLokasyon().getY()) {
                            oyuncu.setSkor(oyuncu.getSkor() + objeler.get(i).getPuan());
                            oyuncu.PuaniGoster(skorLbl, oyuncu.getSkor());
                            objeler.remove(i);
                        }
                    }
                    Oyuncu.setHamle(Oyuncu.getHamle() + 1);

                    if (Oyuncu.getHamle() == oyuncu.getHareketHizi()) {
                        labirentiTemizle();
                        for (int k = 0; k < dusmanlar.size(); k++) {
                            path = dusmanlar.get(k).DijkstraList(labirent, oyuncu.getLokasyon().getX(), oyuncu.getLokasyon().getY());
                            boya(path, k);
                            dusmanHareket(path, k);
                        }
                        Oyuncu.setHamle(0);
                    }

                    skorLbl.setText(oyuncu.getSkor() + "");
                    if ((oyuncu.getLokasyon().getX() == 12 && oyuncu.getLokasyon().getY() == 7) || oyuncu.getSkor() <= 0) {
                        setFocusable(false);
                        BitisPenceresi bitisPenceresi = new BitisPenceresi(oyuncu.getSkor());
                        bitisPenceresi.setVisible(true);
                    }
                }
            }
        };
        addKeyListener(keyListener);
        setFocusable(true);
    }

    public void boya(List<Lokasyon> path, int k) {
        for (int t = 0; t < path.size() - 2; t++) {
            switch (k) {
                case 0:
                    jLabels[path.get(t).getX()][path.get(t).getY()].setBackground(Color.green);
                    break;
                case 1:
                    jLabels[path.get(t).getX()][path.get(t).getY()].setBackground(Color.blue);
                    break;
                default:
                    jLabels[path.get(t).getX()][path.get(t).getY()].setBackground(Color.green);
                    break;
            }
        }
    }

    public void dusmanHareket(List<Lokasyon> path, int k) {
        boolean temas = false;

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                if (labirent[i][j] == 2)
                    labirent[i][j] = 1;
            }
        }
        if (dusmanlar.get(k).getLokasyon().getX() == oyuncu.getLokasyon().getX() && dusmanlar.get(k).getLokasyon().getY() == oyuncu.getLokasyon().getY()) {
            dusmanTemas(k);
            temas = true;
        }

        if (dusmanlar.size() > 1 && !(dusmanlar.get(0).getLokasyon().getX() == dusmanlar.get(1).getLokasyon().getX() && dusmanlar.get(0).getLokasyon().getY() == dusmanlar.get(1).getLokasyon().getY())) {
            jLabels[dusmanlar.get(k).getLokasyon().getX()][dusmanlar.get(k).getLokasyon().getY()].setIcon(null);
        } else if (true) {
            jLabels[dusmanlar.get(k).getLokasyon().getX()][dusmanlar.get(k).getLokasyon().getY()].setIcon(null);
        }

        for (int i = 0; i < objeler.size(); i++) {
            if (dusmanlar.get(k).getLokasyon().getX() == objeler.get(i).getLokasyon().getX() && dusmanlar.get(k).getLokasyon().getY() == objeler.get(i).getLokasyon().getY()) {
                jLabels[objeler.get(i).getLokasyon().getX()][objeler.get(i).getLokasyon().getY()].setIcon(objeler.get(i).getImageIcon());
            }
        }

        if (!temas) {
            if (dusmanlar.get(k).getAd().contains("Gargamel") && path.size() - 2 > 0) {
                dusmanlar.get(k).getLokasyon().setX(path.get(path.size() - 3).getX());
                dusmanlar.get(k).getLokasyon().setY(path.get(path.size() - 3).getY());
            } else if (dusmanlar.get(k).getAd().contains("Gargamel") && path.size() - 3 < 0) {
                dusmanTemas(k);
            } else {
                dusmanlar.get(k).getLokasyon().setX(path.get(path.size() - 1).getX());
                dusmanlar.get(k).getLokasyon().setY(path.get(path.size() - 1).getY());
            }
        }

        if (dusmanlar.get(k).getLokasyon().getX() == oyuncu.getLokasyon().getX() && dusmanlar.get(k).getLokasyon().getY() == oyuncu.getLokasyon().getY())
            dusmanTemas(k);

        jLabels[dusmanlar.get(k).getLokasyon().getX()][dusmanlar.get(k).getLokasyon().getY()].setIcon(dusmanlar.get(k).getImageIcon());
    }

    public void labirentiTemizle() {
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                if (labirent[i][j] != 0) {
                    jLabels[j][i].setBackground(Color.white);
                }
            }
        }
    }

    public void dusmanTemas(int k) {
        switch (dusmanlar.get(k).getKapi()) {
            case "A":
                dusmanlar.get(k).setLokasyon(new Lokasyon(3, 0));
                break;
            case "B":
                dusmanlar.get(k).setLokasyon(new Lokasyon(10, 0));
                break;
            case "C":
                dusmanlar.get(k).setLokasyon(new Lokasyon(0, 5));
                break;
            case "D":
                dusmanlar.get(k).setLokasyon(new Lokasyon(3, 10));
                break;
            default:
                dusmanlar.get(k).setLokasyon(new Lokasyon(10, 0));
                break;
        }
        puan.DusmanTemas(dusmanlar, oyuncu, k);
    }
}