package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaImc {

    private JFrame frmImc;
    private JTextField nomeField, pesoField, alturaField;
    private JLabel resultadoLabel, imagemLabel;
    private JTextArea dietaTextArea;

    public TelaImc() {
        initialize();
    }

    private void initialize() {
        frmImc = new JFrame();
        frmImc.setResizable(false);
        frmImc.setTitle("IMC - BEM ESTAR");
        frmImc.setBounds(100, 100, 500, 684);
        frmImc.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaImc.class.getResource("/images/bem-estar-fisico.png")));
        frmImc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmImc.getContentPane().setLayout(null);
        frmImc.setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNome.setBounds(25, 26, 149, 32);
        frmImc.getContentPane().add(lblNome);

        nomeField = new JTextField();
        nomeField.setBounds(199, 26, 167, 32);
        frmImc.getContentPane().add(nomeField);

        JLabel lblPeso = new JLabel("Peso (kg):");
        lblPeso.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPeso.setBounds(25, 69, 149, 32);
        frmImc.getContentPane().add(lblPeso);

        pesoField = new JTextField();
        pesoField.setBounds(199, 69, 167, 32);
        frmImc.getContentPane().add(pesoField);

        JLabel lblAltura = new JLabel("Altura (m):");
        lblAltura.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblAltura.setBounds(25, 120, 149, 32);
        frmImc.getContentPane().add(lblAltura);

        alturaField = new JTextField();
        alturaField.setBounds(199, 120, 167, 32);
        frmImc.getContentPane().add(alturaField);

        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setBackground(new Color(255, 158, 177));
        btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCalcular.setBounds(25, 170, 136, 39);
        frmImc.getContentPane().add(btnCalcular);

        JButton btnLimpar = new JButton("LIMPAR CAMPOS");
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setBackground(new Color(255, 158, 177));
        btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnLimpar.setBounds(171, 170, 167, 39);
        frmImc.getContentPane().add(btnLimpar);

        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(255, 255, 255));
        panel_1.setBackground(new Color(255, 0, 128));
        panel_1.setBounds(25, 230, 232, 193);
        frmImc.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        resultadoLabel = new JLabel("Resultado aparecerá aqui");
        resultadoLabel.setForeground(new Color(255, 255, 255));
        resultadoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        resultadoLabel.setBounds(10, 11, 212, 14);
        panel_1.add(resultadoLabel);

        dietaTextArea = new JTextArea("Dieta recomendada aparecerá aqui");
        dietaTextArea.setForeground(new Color(255, 255, 255));
        dietaTextArea.setFont(new Font("Tahoma", Font.BOLD, 11));
        dietaTextArea.setBounds(10, 36, 212, 150);
        dietaTextArea.setLineWrap(true);
        dietaTextArea.setWrapStyleWord(true);
        dietaTextArea.setEditable(false);
        dietaTextArea.setBackground(panel_1.getBackground());
        panel_1.add(dietaTextArea);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(267, 230, 207, 378);
        frmImc.getContentPane().add(panel_2);
        panel_2.setLayout(null); // Usando layout nulo para controlar livremente a posição da imagem

        imagemLabel = new JLabel();
        imagemLabel.setBounds(0, 0, 207, 378); // Ajuste para ocupar o espaço do painel
        panel_2.add(imagemLabel);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal principal = new TelaPrincipal();
                frmImc.dispose();
            }
        });
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnVoltar.setBackground(new Color(255, 158, 177));
        btnVoltar.setBounds(25, 568, 136, 39);
        frmImc.getContentPane().add(btnVoltar);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        frmImc.setVisible(true);
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(pesoField.getText().replace(",", "."));
            double altura = Double.parseDouble(alturaField.getText().replace(",", "."));
            double imc = peso / (altura * altura);
            String resultado;
            String dieta;
            String imagemPath = "";

            if (imc < 18.5) {
                resultado = "Abaixo do peso";
                dieta = "Consuma mais calorias,\ncom proteínas e carboidratos.";
                imagemPath = "/images/magro.png";
            } else if (imc < 24.9) {
                resultado = "Peso normal";
                dieta = "Mantenha uma alimentação\n equilibrada com frutas, vegetais e proteínas.";
                imagemPath = "/images/peso.png";
            } else if (imc < 29.9) {
                resultado = "Sobrepeso";
                dieta = "Reduza açúcares e gorduras,\naumente consumo de fibras e proteínas.";
                imagemPath = "/images/obesidade.png";
            } else {
                resultado = "Obesidade";
                dieta = "Adote uma dieta balanceada,\nreduza gorduras e pratique atividades físicas.";
                imagemPath = "/images/obesidade (1).png";
            }

            resultadoLabel.setText("IMC: " + String.format("%.2f", imc) + " - " + resultado);
            dietaTextArea.setText("Recomendação: " + dieta);

            // Ajustar a imagem
            ImageIcon icon = new ImageIcon(getClass().getResource(imagemPath));
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(imagemLabel.getWidth(), imagemLabel.getHeight(), Image.SCALE_SMOOTH);
            imagemLabel.setIcon(new ImageIcon(resizedImg));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para peso e altura!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        pesoField.setText("");
        alturaField.setText("");
        resultadoLabel.setText("Resultado aparecerá aqui");
        dietaTextArea.setText("Dieta recomendada aparecerá aqui");
        imagemLabel.setIcon(null);
    }
}
