
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class BitisPenceresi extends JFrame {

    private JPanel contentPane;

    public BitisPenceresi(int skor) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 200, 550, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblOyunDurum = new JLabel("");
        lblOyunDurum.setFont(new Font("Tahoma", Font.PLAIN, 50));
        lblOyunDurum.setBounds(40, 10, 250, 100);
        contentPane.add(lblOyunDurum);

        if (skor > 0) {
            lblOyunDurum.setText("Kazandınız");
            JLabel lblBitisSirine = new JLabel();
            lblBitisSirine.setBounds(300, 10, 300, 300);
            lblBitisSirine.setIcon(new ImageIcon("sirineBitis.png"));
            contentPane.add(lblBitisSirine);

        } else {
            lblOyunDurum.setText("Kaybettiniz");
            JLabel lblBitisSirin = new JLabel();
            lblBitisSirin.setBounds(350, 10, 160, 300);
            lblBitisSirin.setIcon(new ImageIcon("gargamelBitis.png"));
            contentPane.add(lblBitisSirin);
        }

        JLabel lblSkor = new JLabel("Skorunuz = " + skor);
        lblSkor.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblSkor.setBounds(70, 100, 200, 50);
        contentPane.add(lblSkor);

        JLabel lblYasin = new JLabel("Yasin Çalışkan 180201060");
        lblYasin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblYasin.setBounds(10, 250, 500, 30);
        contentPane.add(lblYasin);

        JLabel lblAtakan = new JLabel("Atakan Atalar  190201038");
        lblAtakan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAtakan.setBounds(10, 280, 500, 30);
        contentPane.add(lblAtakan);

    }
}
