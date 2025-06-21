/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import dao.AlunoDAO;
import dao.CursoDAO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        setTitle("Lista de Alunos");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        try {
      
            AlunoDAO dao = new AlunoDAO();
            List<Aluno> aluno = dao.listar();


            String[] colunas = {"ID","NOME", "CPF","TELEFONE", "EMAIL" ,"NASCIMENTO","STATUS","CURSO"};
            DefaultTableModel model = new DefaultTableModel(colunas, 0);
  
            for (Aluno c : aluno) {
                if(c.getAtivo() == 0){continue;}
                Object[] linha = {
                dao.pegarID(c.getNome()),
                c.getNome(),
                c.getCpf(),
                c.getTelefone(),
                c.getEmail(),
                c.getDatanasc(),
                "Ativo",
                dao.pegarNomecurso(dao.pegarIDcurso(c.getNome()))
                };
                model.addRow(linha);
            }

            JTable tabela = new JTable(model); 
            JScrollPane scrollPane = new JScrollPane(tabela);
            scrollPane.setPreferredSize(new Dimension(780, 250));


            JPanel painel = new JPanel(new BorderLayout());


            JPanel botoes = new JPanel();
            JButton listar = new JButton("Listar Todos");
            JButton deletar = new JButton("Deletar");
            JButton buscarAluno = new JButton("Buscar Aluno");
            JButton buscar = new JButton("Buscar por Curso");
            botoes.add(listar);
            botoes.add(buscarAluno);
            botoes.add(buscar);
            botoes.add(deletar);


            painel.add(scrollPane, BorderLayout.CENTER); 
            painel.add(botoes, BorderLayout.SOUTH);


            getContentPane().add(painel);
            
            listar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AlunoDAO dao = new AlunoDAO();
                    
                    model.setRowCount(0);
                        try {
                            for (Aluno c : aluno) {
                            if(c.getAtivo() == 0){continue;}
                            Object[] linha = {
                                dao.pegarID(c.getNome()),
                                c.getNome(),
                                c.getCpf(),
                                c.getTelefone(),
                                c.getEmail(),
                                c.getDatanasc(),
                                "Ativo",
                                dao.pegarNomecurso(dao.pegarIDcurso(c.getNome()))
                            };
                            model.addRow(linha);
                        } }catch (SQLException ex) {
                            Logger.getLogger(TabelaAlunos.class.getName()).log(Level.SEVERE, null, ex);
                        }
            
                }});

            deletar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String n = JOptionPane.showInputDialog("Digite o ID do Aluno:");
        
                    int id = Integer.parseInt(n);
        
                    AlunoDAO dao;
                    dao = new AlunoDAO();
                    try {
                        dao.DeleteAluno(id);
                        JOptionPane.showMessageDialog(rootPane, "Aluno deletado com sucesso");
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(rootPane, "Erro ao deletar. Tente Novamente");
                        System.out.println("Erro ao Deletar:"+ex);
                    }
                }
            });
            buscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CursoDAO cursoDAO = new CursoDAO();
                    AlunoDAO alunoDAO = new AlunoDAO();
                    String nomeCurso = JOptionPane.showInputDialog("Digite o nome do Curso:");
                    
                    if (nomeCurso == null || nomeCurso.trim().isEmpty()) {
                        return;
                    }
                    try {
                        model.setRowCount(0);
                        int idCurso = cursoDAO.pegarIDcurso(nomeCurso);

                        List<Aluno> alunos = alunoDAO.listarAlunosCurso(idCurso);

                        for (Aluno c : alunos) {
                            
                            if(c.getAtivo() == 0){continue;}
                            Object[] linha = {
                                alunoDAO.pegarID(c.getNome()),
                                c.getNome(),
                                c.getCpf(),
                                c.getTelefone(),
                                c.getEmail(),
                                c.getDatanasc(),
                                "Ativo",
                                dao.pegarNomecurso(dao.pegarIDcurso(c.getNome()))
                        };
                            model.addRow(linha);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TabelaAlunos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            buscarAluno.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AlunoDAO alunoDAO = new AlunoDAO();
                    String cpf = JOptionPane.showInputDialog("Digite o CPF do aluno:");
                    
                    if (cpf == null || cpf.trim().isEmpty()) {
                        return;
                    }
                    try {
                        model.setRowCount(0);

                        Aluno aluno =  alunoDAO.buscarPorCPF(cpf);

                            Object[] linha = {
                                alunoDAO.pegarID(aluno.getNome()),
                                aluno.getNome(),
                                aluno.getCpf(),
                                aluno.getTelefone(),
                                aluno.getEmail(),
                                aluno.getDatanasc(),
                                aluno.getAtivo(),
                                dao.pegarNomecurso(dao.pegarIDcurso(aluno.getNome()))
                            };
                            model.addRow(linha);
                    } catch (SQLException ex) {
                        Logger.getLogger(TabelaAlunos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }});

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

