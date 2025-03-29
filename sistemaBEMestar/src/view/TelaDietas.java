package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDietas {

    private JFrame frmDieta;
    private JTextArea dietaTextArea;

    public TelaDietas() {
        initialize();
    }

    private void initialize() {
        frmDieta = new JFrame();
        frmDieta.setBackground(new Color(255, 158, 177));
        frmDieta.setResizable(false);
        frmDieta.getContentPane().setBackground(new Color(255, 255, 255));
        frmDieta.setTitle("DIETAS - BEM ESTAR");
        frmDieta.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/images/bem-estar-fisico.png")));
        frmDieta.setBounds(100, 100, 489, 502);
        frmDieta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmDieta.getContentPane().setLayout(null);
        frmDieta.setLocationRelativeTo(null);

        JLabel lblNewLabel = new JLabel("Digite o tipo de dieta:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(32, 45, 152, 46);
        frmDieta.getContentPane().add(lblNewLabel);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dieta abaixo do peso", "Dieta peso normal", "Dieta sobrepeso", "Dieta obesidade"}));
        comboBox.setBounds(194, 45, 244, 46);
        frmDieta.getContentPane().add(comboBox);

        // JTextArea para exibir a dieta recomendada
        dietaTextArea = new JTextArea();
        dietaTextArea.setFont(new Font("Tahoma", Font.BOLD, 12));
        dietaTextArea.setBounds(32, 120, 406, 211);
        dietaTextArea.setLineWrap(true);
        dietaTextArea.setWrapStyleWord(true);
        dietaTextArea.setEditable(false); // Não permitir edição
        dietaTextArea.setBackground(new Color(240, 240, 240)); // Cor de fundo suave
        frmDieta.getContentPane().add(dietaTextArea);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 0, 128));
        panel.setBounds(0, 384, 473, 79);
        frmDieta.getContentPane().add(panel);

        JButton btnNewButton_3_1 = new JButton("VOLTAR");
        btnNewButton_3_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal pricipal = new TelaPrincipal();
                frmDieta.dispose();
            }
        });
        btnNewButton_3_1.setForeground(Color.WHITE);
        btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton_3_1.setBackground(new Color(128, 182, 255));
        btnNewButton_3_1.setBounds(165, 21, 140, 36);
        panel.add(btnNewButton_3_1);

        // ActionListener para atualizar o JTextArea de acordo com a seleção no JComboBox
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDiet = (String) comboBox.getSelectedItem();
                switch (selectedDiet) {
                    case "Dieta abaixo do peso":
                        dietaTextArea.setText("A dieta para pessoas abaixo do peso inclui alimentos ricos em calorias, proteínas e carboidratos. Exemplos: arroz, feijão, carnes magras, batatas, pães integrais.");
                        break;
                    case "Dieta peso normal":
                        dietaTextArea.setText("A dieta para pessoas com peso normal deve ser balanceada, com uma boa ingestão de frutas, vegetais, proteínas e carboidratos integrais. Exemplos: legumes, grãos, peixes.");
                        break;
                    case "Dieta sobrepeso":
                        dietaTextArea.setText("Para pessoas com sobrepeso, recomenda-se reduzir calorias e evitar alimentos ricos em açúcares e gorduras saturadas. Exemplos: saladas, vegetais, proteínas magras, grãos integrais.");
                        break;
                    case "Dieta obesidade":
                        dietaTextArea.setText("A dieta para pessoas com obesidade envolve a redução de calorias, ingestão controlada de carboidratos e aumento do consumo de fibras. Exemplos: vegetais, carnes magras, alimentos com baixo índice glicêmico.");
                        break;
                    default:
                        dietaTextArea.setText("Selecione uma dieta para visualizar as recomendações.");
                        break;
                }
            }
        });

        frmDieta.setLocationRelativeTo(null);
        frmDieta.setVisible(true);
    }
}
