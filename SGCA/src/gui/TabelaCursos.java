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
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Aluno;
import model.Curso;

/**
 *
 * @author faelb
 */
public class TabelaCursos extends JFrame{
    
     public TabelaCursos(){
        setTitle("Lista de Cursos");
        setSize(1000,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        try {
      
            CursoDAO dao = new CursoDAO();
            List<Curso> curso = dao.listar();


           String[] colunas = {"ID", "CURSO", "CARGA HORARIA", "LIMITE DE ALUNOS","STATUS"};
           DefaultTableModel model = new DefaultTableModel(colunas, 0);
  
            for (Curso c : curso) {
                if(c.getAtivo() == 0){continue;}
                Object[] linha = {
                c.getIdCurso(),
                c.getNome(),
                c.getCargaHoraria(),
                c.getLimiteAlunos(),
                c.getAtivo()
                };
                if(c.getAtivo() == 0){
                 linha[4] = "Desabilitado";
                }else{linha[4]= "Ativo";}
                model.addRow(linha);
            }
            JTable tabela = new JTable(model); 
            JScrollPane scrollPane = new JScrollPane(tabela);
            scrollPane.setPreferredSize(new Dimension(780, 250));


            JPanel painel = new JPanel(new BorderLayout());


            JPanel botoes = new JPanel();
            JButton relatorio = new JButton("Relatório");
            JButton deletar = new JButton("Deletar");
            JButton buscar = new JButton("Buscar");
            JButton listar = new JButton("Listar Todos");
            botoes.add(listar);
            botoes.add(buscar);
            botoes.add(relatorio);
            botoes.add(deletar);


            painel.add(scrollPane, BorderLayout.CENTER); 
            painel.add(botoes, BorderLayout.SOUTH);


            getContentPane().add(painel);
            
            listar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.setRowCount(0);
                    for (Curso c : curso) {
                        if(c.getAtivo() == 0){continue;}
                        Object[] linha = {
                        c.getIdCurso(),
                        c.getNome(),
                        c.getCargaHoraria(),
                        c.getLimiteAlunos(),
                        c.getAtivo()
                    };
                    if(c.getAtivo() == 0){linha[4] = "Desabilitado";}else{linha[4]= "Ativo";}
                model.addRow(linha);
            }
                }});

            deletar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String n = JOptionPane.showInputDialog("Digite o ID do Curso:");
        
                    int id = Integer.parseInt(n);
        
                    CursoDAO dao;
                    dao = new CursoDAO();
                    try {
                        dao.DeletarCurso(id);
                        JOptionPane.showMessageDialog(rootPane, "Curso deletado com sucesso");
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(rootPane, "Erro ao deletar. Tente Novamente");
                        System.out.println("Erro ao Deletar:"+ex);
                    }
                }
            });
            
            relatorio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] opcoes = {"Ativos", "Desabilitados", "Cancelar"};
                    int n = JOptionPane.showOptionDialog(null, "Deseja o relatório dos alunos ativos ou desabilitados?", "Confirme", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, opcoes, opcoes[0]);
                    if (n == 2) {
                        return;
                    }
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Salvar relatório");

                    int userSelection = fileChooser.showSaveDialog(null);
                    if(n == 0){
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File arquivo = fileChooser.getSelectedFile();
                    dao.gerarRelatorioAlunosAtivos(arquivo.getAbsolutePath());
                }}if(n == 1){
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File arquivo = fileChooser.getSelectedFile();
                    dao.gerarRelatorioAlunosDesabilitados(arquivo.getAbsolutePath());
                }else{return;}
                }
                
                }});
            
            buscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CursoDAO cursoDAO = new CursoDAO();
                    AlunoDAO alunoDAO = new AlunoDAO();
                    String nomeCurso = JOptionPane.showInputDialog("Digite o nome do Curso:");
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
                                idCurso
                        };
                            model.addRow(linha);
                        }
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
    
    
    public void tabela() throws SQLException {
        new TabelaCursos();
    }
    
    
}

    

