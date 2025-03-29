package view;

import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;

import data.ConexaoDbc;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaCadastro {

    private JFrame frmCadastro;
    private JTextField textField;
    private JPasswordField passwordField;

    public TelaCadastro() {
        initialize();
    }

    private void initialize() {
        frmCadastro = new JFrame();
        frmCadastro.setResizable(false);
        frmCadastro.setBackground(new Color(128, 182, 255));
        frmCadastro.setTitle("CADASTRO - BEM ESTAR");
        frmCadastro.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastro.class.getResource("/images/bem-estar-fisico.png")));
        frmCadastro.setBounds(100, 100, 489, 612);
        frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCadastro.getContentPane().setLayout(null);
        frmCadastro.setLocationRelativeTo(null);

        ImageIcon logoIcon = new ImageIcon(TelaCadastro.class.getResource("/images/bem-estar-fisico.png"));
        Image img = logoIcon.getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        JLabel lblLogo = new JLabel(resizedIcon);
        lblLogo.setBounds(176, 27, 106, 93);
        frmCadastro.getContentPane().add(lblLogo);

        JLabel lblNewLabel = new JLabel("Usuário:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(71, 147, 149, 32);
        frmCadastro.getContentPane().add(lblNewLabel);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSenha.setBounds(71, 198, 149, 32);
        frmCadastro.getContentPane().add(lblSenha);

        textField = new JTextField();
        textField.setBounds(245, 147, 167, 32);
        frmCadastro.getContentPane().add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(245, 198, 167, 32);
        frmCadastro.getContentPane().add(passwordField);

        // Botão CADASTRAR
        JButton btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCadastrar.setForeground(new Color(255, 255, 255));
        btnCadastrar.setBackground(new Color(255, 0, 128));
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos antes de cadastrar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Connection con = ConexaoDbc.conexao();
                    String sql = "INSERT INTO dados_senhas(usuario, senha) VALUES (?, ?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, usuario);
                    stmt.setString(2, senha);
                    stmt.execute();
                    stmt.close();
                    con.close();

                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    textField.setText("");
                    passwordField.setText("");

                    // Atualizar a listagem
                    ListagemCadastrados listagem = new ListagemCadastrados();
                    listagem.atualizarTabela();
                    frmCadastro.dispose(); // Fecha a tela de cadastro
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCadastrar.setBounds(161, 299, 139, 37);
        frmCadastro.getContentPane().add(btnCadastrar);

        // Botão REMOVER
        JButton btnRemover = new JButton("REMOVER");
        btnRemover.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnRemover.setBackground(new Color(255, 0, 128));
        btnRemover.setForeground(new Color(255, 255, 255));
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textField.getText().trim();

                if (usuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite um usuário para remover!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário: " + usuario + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Connection con = ConexaoDbc.conexao();
                        String sql = "DELETE FROM dados_senhas WHERE usuario = ?";

                        PreparedStatement stmt = con.prepareStatement(sql);
                        stmt.setString(1, usuario);

                        int rowsAffected = stmt.executeUpdate();
                        stmt.close();
                        con.close();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                            textField.setText("");
                            passwordField.setText("");

                            // Atualiza a listagem
                            ListagemCadastrados listagem = new ListagemCadastrados();
                            listagem.atualizarTabela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao remover o usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnRemover.setBounds(161, 357, 139, 37);
        frmCadastro.getContentPane().add(btnRemover);

        // Botão VOLTAR
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnVoltar.setBackground(new Color(255, 0, 128));
        btnVoltar.setForeground(new Color(255, 255, 255));
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaLogin login = new TelaLogin();
                frmCadastro.dispose();
            }
        });

        btnVoltar.setBounds(161, 477, 139, 37);
        frmCadastro.getContentPane().add(btnVoltar);
        
        JButton btnCadastrados = new JButton("CADASTRADOS");
        btnCadastrados.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCadastrados.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListagemCadastrados cadastrados = new ListagemCadastrados();
        		frmCadastro.dispose();
        	}
        });
        btnCadastrados.setForeground(Color.WHITE);
        btnCadastrados.setBackground(new Color(255, 0, 128));
        btnCadastrados.setBounds(161, 415, 139, 37);
        frmCadastro.getContentPane().add(btnCadastrados);

        frmCadastro.setVisible(true);
    }
}
