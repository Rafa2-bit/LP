/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import dao.AlunoDAO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Aluno;

/**
 *
 * @author faelb
 */

public class TabelaAlunos extends JFrame {


    public TabelaAlunos(){
        setTitle("Lista de Clientes");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        try {
      
            AlunoDAO dao = new AlunoDAO();
            List<Aluno> aluno = dao.listar();


            String[] colunas = {"NOME", "CPF", "EMAIL", "TELEFONE","NASCIMENTO"};
            DefaultTableModel model = new DefaultTableModel(colunas, 0);
  
            for (Aluno c : aluno) {
    
                Object[] linha = {
                c.getNome(),
                c.getCpf(),
                c.getTelefone(),
                c.getEmail(),
                c.getDatanasc()
                };
                model.addRow(linha);
            }

            JTable tabela = new JTable(model); 
            JScrollPane scrollPane = new JScrollPane(tabela);
            scrollPane.setPreferredSize(new Dimension(780, 250));


            JPanel painel = new JPanel(new BorderLayout());


            JPanel botoes = new JPanel();
            JButton editar = new JButton("Editar");
            JButton deletar = new JButton("Deletar");
            JButton buscar = new JButton("Buscar");
            botoes.add(buscar);
            botoes.add(editar);
            botoes.add(deletar);


            painel.add(scrollPane, BorderLayout.CENTER); 
            painel.add(botoes, BorderLayout.SOUTH);


            getContentPane().add(painel);

            deletar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String n = JOptionPane.showInputDialog("Digite o ID do Aluno");
        
                    int id = Integer.parseInt(n);
        
                    AlunoDAO dao;
                    dao = new AlunoDAO();
                    try {
                        dao.DeleteAluno(id);
                        JOptionPane.showMessageDialog(rootPane, "Aluno deletado com sucesso");
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(rootPane, "Erro ao deletar");
                        System.out.println("Erro ao Deletar:"+ex);
                    }
                }
            });
            
            setVisible(true);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void listar() throws SQLException {
        new TabelaAlunos();
    }
    
    
}

