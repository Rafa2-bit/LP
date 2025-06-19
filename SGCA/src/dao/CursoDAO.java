
package dao;

import factory.ConnectionDB;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Curso;

/**
 *
 * @author faelb
 */
public class CursoDAO {
    private Connection conn;

    public CursoDAO() {
        this.conn = new ConnectionDB().getConnection();
    }
    
    
        public void insertDB(int idCurso,String nome , int cargaHoraria, int limiteAlunos, int ativos)throws SQLException{
        String sql = "INSERT INTO curso(idCurso,nome, cargaHoraria, limiteAlunos, ativo) VALUES (?,?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, idCurso);
        stm.setString(2, nome);
        stm.setInt(3, cargaHoraria);
        stm.setInt(4, limiteAlunos);
        stm.setInt(5, ativos);
        stm.executeUpdate();
        
        stm.close();
        }
        
        
        public void DeletarCurso(int idCurso)throws SQLException{
        String del = "DELETE FROM curso WHERE idCurso = ?";   
        PreparedStatement stm = conn.prepareStatement(del);
       
        try{
            stm.setInt(1, idCurso);
            int linha = stm.executeUpdate();
            if(linha > 0){
            System.out.println("Curso deletado com sucesso");
            }
            else{
                System.out.println("Curso não encontrado");
            }  
        }
        catch(Exception e){
            System.out.println("Erro ao deletar curso:"+e);
        }
       
        stm.close();
        conn.close(); 
        }
        
        
        public void editarCurso(int idCurso,String nome , int cargaHoraria, int limiteAlunos) throws SQLException {
        String sql = "UPDATE curso SET nome=?, cargaHoraria=?, limiteAlunos=? WHERE idCurso=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        
        
        try {
            stm.setString(1, nome);
            stm.setInt(2, cargaHoraria);
            stm.setInt(3, limiteAlunos);
            stm.setInt(4, idCurso);
        
            stm.executeUpdate();
        
            stm.close();
            conn.close();
        }
        catch(Exception e){
        System.out.println("Erro ao editar curso"+e);
        }   
        }
        
        
        public void desativarCurso(int idCurso)throws SQLException{
            String sql = "UPDATE curso SET ativo = FALSE WHERE idCurso = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            try{
                stm.setInt(1, idCurso);
                
                stm.executeUpdate();
            }catch(Exception e){
                System.out.println("Erro ao desativar curso:"+e);
            }
        }
        
        
        public void ativarCurso(int idCurso)throws SQLException{
            String sql = "UPDATE curso SET ativo = TRUE WHERE idCurso = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            try{
                stm.setInt(1, idCurso);
                
                stm.executeUpdate();
            }catch(Exception e){
                System.out.println("Erro ao ativar curso:"+e);
            }
        }
         
        public List<Curso> Editar(int idCurso) throws SQLException {  
            List<Curso> cursos = new ArrayList<>();
            String sql = "SELECT * FROM curso WHERE idCurso=?";
    
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setInt(1, idCurso);
                ResultSet rs = stm.executeQuery();
        
            while (rs.next()) {
            Curso curso = new Curso(
                rs.getInt("idCurso"),
                rs.getString("nome"),
                rs.getInt("cargaHoraria"),
                rs.getInt("limiteAlunos"),1
            );
            cursos.add(curso);
            }
        
            } catch (Exception e) {
                System.out.println("Erro ao buscar dados do aluno: " + e.getMessage());
            }

            return cursos;
        }

        
        public List<Curso> listar() throws SQLException{ 
        List<Curso> cursos = new ArrayList<>();
        String sql = "select * from curso";
        try(Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(sql)){
               while(rs.next()){
                   
                   Curso curso = new Curso(
                           rs.getInt("idCurso"),
                           rs.getString("nome"),
                           rs.getInt("cargaHoraria"),
                           rs.getInt("limiteAlunos"),
                           rs.getInt("ativo")
                   );
                  
                   cursos.add(curso);
               }
           }   
        return cursos;
    }
        
        public int pegarIDcurso(String nome) throws SQLException {
            String sql = "SELECT idCurso FROM curso WHERE nome = ?";
            int id = 0;
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setString(1, nome);
                ResultSet rs = stm.executeQuery();

                    if (rs.next()) {
            id = rs.getInt("idCurso");
                }
            }
            return id;
        }

    public void gerarRelatorioAlunosAtivos(String caminhoArquivo) {
    String sql = "SELECT c.nome AS curso, COUNT(a.idAluno) AS qtd_alunos " +
                 "FROM aluno a " +
                 "JOIN curso c ON a.curso = c.idCurso " +
                 "WHERE a.ativo = 1 " +
                 "GROUP BY c.nome";

    try (Connection conn = new ConnectionDB().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {

        writer.write("Relatório de Alunos Ativos por Curso\n");
        writer.write("------------------------------------\n");

        while (rs.next()) {
            String curso = rs.getString("curso");
            int qtd = rs.getInt("qtd_alunos");

            writer.write("Curso: " + curso + " - " + qtd + " aluno(s) ativo(s)\n");
        }

        System.out.println("Relatório gerado com sucesso em: " + caminhoArquivo);

    } catch (Exception e) {
        System.out.println("Erro ao gerar relatório: " + e.getMessage());
    }
    }
    public void gerarRelatorioAlunosDesabilitados(String caminhoArquivo) {
    String sql = "SELECT c.nome AS curso, COUNT(a.idAluno) AS qtd_alunos " +
                 "FROM aluno a " +
                 "JOIN curso c ON a.curso = c.idCurso " +
                 "WHERE a.ativo = 0 " +
                 "GROUP BY c.nome";

    try (Connection conn = new ConnectionDB().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {

        writer.write("Relatório de Alunos Desabilitados por Curso\n");
        writer.write("------------------------------------\n");

        while (rs.next()) {
            String curso = rs.getString("curso");
            int qtd = rs.getInt("qtd_alunos");

            writer.write("Curso: " + curso + " - " + qtd + " aluno(s) desabilitados(s)\n");
        }

        System.out.println("Relatório gerado com sucesso em: " + caminhoArquivo);

    } catch (Exception e) {
        System.out.println("Erro ao gerar relatório: " + e.getMessage());
    }
    }

    public boolean podeCadastrarMaisAlunos(int idCurso) throws SQLException {
    String sql = "SELECT COUNT(*) AS total FROM aluno WHERE curso = ?";
    try (PreparedStatement stm = conn.prepareStatement(sql)) {
        stm.setInt(1, idCurso);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            int totalMatriculados = rs.getInt("total");
            String sqlLimite = "SELECT limiteAlunos FROM curso WHERE idCurso = ?";
            try (PreparedStatement stm2 = conn.prepareStatement(sqlLimite)) {
                stm2.setInt(1, idCurso);
                ResultSet rs2 = stm2.executeQuery();

                if (rs2.next()) {
                    int limite = rs2.getInt("limiteAlunos");
                    return totalMatriculados < limite;  
                }
            }
        }
    }
    return false;
    }

        
      
}
