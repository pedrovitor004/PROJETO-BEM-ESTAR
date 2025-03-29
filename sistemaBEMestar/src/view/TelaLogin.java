package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.ConexaoDbc;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaLogin {
    
    private JFrame frmLogin;
    private JTextField usuarioField;
    private JPasswordField passwordField;
    
    public TelaLogin() {
        initialize();
    }

    private void initialize() {
        frmLogin = new JFrame();
        frmLogin.setBackground(new Color(255, 158, 177));
        frmLogin.setResizable(false);
        frmLogin.getContentPane().setBackground(new Color(255, 0, 128));
        frmLogin.setTitle("BEM ESTAR - LOGIN");
        frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/images/bem-estar-fisico.png")));
        frmLogin.setBounds(100, 100, 489, 502);
        frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLogin.getContentPane().setLayout(null);
        frmLogin.setLocationRelativeTo(null);
        
        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(194, 60, 93, 58);
        frmLogin.getContentPane().add(lblNewLabel);
        
        JLabel lblUsurio = new JLabel("USUÁRIO:");
        lblUsurio.setForeground(new Color(255, 255, 255));
        lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUsurio.setBounds(82, 155, 93, 58);
        frmLogin.getContentPane().add(lblUsurio);
        
        JLabel lblSenha = new JLabel("SENHA:");
        lblSenha.setForeground(new Color(255, 255, 255));
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSenha.setBounds(82, 232, 93, 58);
        frmLogin.getContentPane().add(lblSenha);
        
        usuarioField = new JTextField();
        usuarioField.setBounds(222, 172, 163, 28);
        frmLogin.getContentPane().add(usuarioField);
        usuarioField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(222, 245, 163, 28);
        frmLogin.getContentPane().add(passwordField);
        
        JButton btnNewButton = new JButton("ENTRAR");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                // Verificação se os campos estão vazios
                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos de usuário e senha antes de entrar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Connection con = ConexaoDbc.conexao();
                    String sql = "SELECT * FROM dados_senhas WHERE usuario=? AND senha=?";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    
                    stmt.setString(1, usuario);
                    stmt.setString(2, senha);
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                        TelaPrincipal principal = new TelaPrincipal();
                        frmLogin.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário/senha incorretos, digite novamente!");
                        usuarioField.setText("");
                        passwordField.setText("");
                    }
                    
                    stmt.close();
                    con.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(255, 158, 177));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton.setBounds(176, 338, 132, 41);
        frmLogin.getContentPane().add(btnNewButton);  
        
        JButton btnNewButton_2 = new JButton("CADASTRAR");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCadastro cadastro = new TelaCadastro();
                frmLogin.dispose();
            }
        });
        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 15));
        btnNewButton_2.setBackground(new Color(128, 182, 255));
        btnNewButton_2.setBounds(176, 390, 132, 44);
        frmLogin.getContentPane().add(btnNewButton_2);
        frmLogin.setVisible(true);
    }

    public JTextField getTextField() {
        return usuarioField;
    }

    public void setTextField(JTextField textField) {
        this.usuarioField = textField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
}