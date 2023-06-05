import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BaslangicPenceresi extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BaslangicPenceresi frame = new BaslangicPenceresi();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BaslangicPenceresi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 200, 550, 430);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBaslik = new JLabel("Şirinler");
        lblBaslik.setFont(new Font("Tahoma", Font.PLAIN, 70));
        lblBaslik.setBounds(170, 0, 250, 70);
        contentPane.add(lblBaslik);

        JButton btnNewButton = new JButton("Gozluklu Sirin");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Labirent frame = new Labirent(1);
                frame.setVisible(true);
                setVisible(false);
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 27));
        btnNewButton.setBounds(40, 310, 220, 70);
        contentPane.add(btnNewButton);

        JButton btnTembelirin = new JButton("Tembel Sirin");
        btnTembelirin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Labirent frame = new Labirent(2);
                frame.setVisible(true);
                setVisible(false);
            }
        });
        btnTembelirin.setFont(new Font("Tahoma", Font.PLAIN, 27));
        btnTembelirin.setBounds(290, 310, 220, 70);
        contentPane.add(btnTembelirin);

        JLabel lblTembelSirin = new JLabel();
        lblTembelSirin.setBounds(50, 50, 250, 250);
        lblTembelSirin.setIcon(new ImageIcon("gozlukluSirinSecim.png"));
        contentPane.add(lblTembelSirin);

        JLabel lblGozlukluSirin = new JLabel();
        lblGozlukluSirin.setBounds(340, 50, 170, 250);
        lblGozlukluSirin.setIcon(new ImageIcon("tembelSirinSecim.png"));
        contentPane.add(lblGozlukluSirin);

    }
}