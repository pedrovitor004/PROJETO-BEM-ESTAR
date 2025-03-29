package view;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConexaoDbc;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListagemCadastrados {

    private JFrame frmListagem;
    private JTable table;

    public ListagemCadastrados() {
        initialize();
        atualizarTabela();
    }

    private void initialize() {
        frmListagem = new JFrame();
        frmListagem.setTitle("LISTAGEM DE CADASTRADOS");
        frmListagem.setIconImage(Toolkit.getDefaultToolkit().getImage(ListagemCadastrados.class.getResource("/images/bem-estar-fisico.png")));
        frmListagem.setBounds(100, 100, 535, 682);
        frmListagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmListagem.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 63, 499, 465);
        frmListagem.getContentPane().add(scrollPane);

        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.BOLD, 13));
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Nome", "Senha"
        	}
        ));

        JLabel lblNewLabel = new JLabel("USUÁRIOS CADASTRADOS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(10, 11, 220, 41);
        frmListagem.getContentPane().add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 0, 128));
        panel.setBounds(0, 569, 519, 74);
        frmListagem.getContentPane().add(panel);
        panel.setLayout(null);
        
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaCadastro cadastro = new TelaCadastro();
        		frmListagem.dispose();
        	}
        });
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnVoltar.setForeground(new Color(255, 0, 128));
        btnVoltar.setBackground(new Color(255, 255, 255));
        btnVoltar.setBounds(192, 26, 139, 37);
        panel.add(btnVoltar);
    }

    public void atualizarTabela() {
        try {
            Connection con = ConexaoDbc.conexao();
            String sql = "SELECT * FROM dados_senhas";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Limpa a tabela antes de atualizar

            while (rs.next()) {
                int id = rs.getInt("id");
                String usuario = rs.getString("usuario");
                String senha = rs.getString("senha");
                model.addRow(new Object[]{id, usuario, senha});
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        frmListagem.setVisible(true);
        frmListagem.setLocationRelativeTo(null);
    }
}
