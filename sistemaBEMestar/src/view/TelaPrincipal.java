package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

    private JFrame frmPrincipal;

    public TelaPrincipal() {
        initialize();
    }

    private void initialize() {
        frmPrincipal = new JFrame();
        frmPrincipal.setBackground(new Color(255, 158, 177));
        frmPrincipal.setResizable(false);
        frmPrincipal.setTitle("TELA PRINCIPAL - BEM ESTAR");
        frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/images/bem-estar-fisico.png")));
        frmPrincipal.setBounds(100, 100, 489, 502);
        frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPrincipal.getContentPane().setLayout(null);
        frmPrincipal.setLocationRelativeTo(null);

        // Carregar e redimensionar a imagem da logo
        ImageIcon logoIcon = new ImageIcon(TelaPrincipal.class.getResource("/images/bem-estar-fisico.png"));
        Image img = logoIcon.getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        // Criar o JLabel com a logo
        JLabel lblLogo = new JLabel(resizedIcon);
        lblLogo.setBounds(176, 46, 106, 93); // Ajuste conforme necessário
        frmPrincipal.getContentPane().add(lblLogo);

        JButton btnNewButton = new JButton("IMC");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaImc imc = new TelaImc();
        		frmPrincipal.dispose();
        	}
        });
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
        btnNewButton.setBackground(new Color(128, 182, 255));
        btnNewButton.setBounds(156, 166, 148, 54);
        frmPrincipal.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("DIETAS");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaDietas dietas = new TelaDietas();
        		frmPrincipal.dispose();
        	}
        });
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 15));
        btnNewButton_1.setBackground(new Color(128, 182, 255));
        btnNewButton_1.setBounds(156, 231, 148, 54);
        frmPrincipal.getContentPane().add(btnNewButton_1);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 0, 128));
        panel.setBounds(0, 382, 473, 81);
        frmPrincipal.getContentPane().add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton_3 = new JButton("SAIR");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaLogin login = new TelaLogin();
        		frmPrincipal.dispose();
        	}
        });
        btnNewButton_3.setForeground(new Color(255, 255, 255));
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton_3.setBackground(new Color(128, 182, 255));
        btnNewButton_3.setBounds(189, 23, 89, 36);
        panel.add(btnNewButton_3);

        frmPrincipal.setVisible(true);
    }
}
