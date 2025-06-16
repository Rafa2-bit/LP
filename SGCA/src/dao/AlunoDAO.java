/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aluno;
/**
 *
 * @author faelb
 */
public class AlunoDAO {
    private Connection conn;

    public AlunoDAO() {
        this.conn = new ConnectionDB().getConnection();
    }
    
    public void insertDB(String nome,String cpf,String telefone,String email,Date datanasc,int ativo,int curso)throws SQLException{
        String sql = "INSERT INTO aluno(nome, cpf, telefone, email, datanasc,ativo, curso) VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);
      try{
        stm.setString(1, nome);
        stm.setString(2, cpf);
        stm.setString(3, telefone);
        stm.setString(4, email);
        stm.setDate(5, (java.sql.Date)datanasc);
        stm.setInt(6, ativo);
        stm.setInt(7, curso);
    
        stm.executeUpdate();
 
      }catch(Exception e){
       e.printStackTrace(); 
        JOptionPane.showMessageDialog(null, "Erro no cadastro: " + e.getMessage());}
        
        stm.close();
    }
    
    public void DeleteAluno(int idAluno)throws SQLException{
        
        String del = "DELETE FROM aluno WHERE idAluno = ?";   
        PreparedStatement stm = conn.prepareStatement(del);
       
        try{
        stm.setInt(1, idAluno);
        int linha = stm.executeUpdate();
        if(linha > 0){
            System.out.println("Aluno deletado com sucesso");
        }
        else{
            System.out.println("Aluno não encontrado");
        }  
        }
        catch(Exception e){
            System.out.println("Erro ao deletar aluno:"+e);
        }
       
        stm.close();
        conn.close();
    }
     
     
    public void atualizar(int idAluno,String nome,String cpf,String telefone,String email,Date datanasc, int curso) throws SQLException {
        
        String sql = "UPDATE aluno SET nome=?, cpf=?, telefone=?, email=?, datanasc=?, curso = ? WHERE idAluno=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        cpf = cpf.replaceAll("[^0-9]", "");
        
        try {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setDate(5, (java.sql.Date) datanasc);
            stmt.setInt(6, curso);
            stmt.setInt(7, idAluno);
        
            stmt.executeUpdate();
        
            stmt.close();
            conn.close();
        }
        catch(Exception ex){
        System.out.println("Erro ao atualizar cliente"+ex);
        }   
    }
    
    
    public void desativarAluno(int idAluno)throws SQLException{
        String sql = "UPDATE aluno SET ativo = FALSE WHERE idAluno = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
            
        try{
            stm.setInt(1, idAluno);
            
            stm.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Erro ao desativar curso:"+e);
        }
    }
        
    
    public void ativarAluno(int idAluno)throws SQLException{
        String sql = "UPDATE aluno SET ativo = TRUE WHERE idAluno = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
            
        try{
            stm.setInt(1, idAluno);
            
            stm.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Erro ao ativar curso:"+e);
        }
    }
    
    public List<Aluno> listar() throws SQLException{ 
        List<Aluno> alunos = new ArrayList<>();
        String sql = "select * from aluno";
        try(Connection con = new ConnectionDB().getConnection(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)){
               while(rs.next()){
                   
                   Aluno aluno = new Aluno(
                           rs.getString("nome"),
                           rs.getString("cpf"),
                           rs.getString("telefone"),
                           rs.getString("email"),
                           rs.getDate("datanasc"),
                           rs.getInt("ativo")
                   );
                  
                   alunos.add(aluno);
               }
           }
        
        return alunos;
    }
    
  
    public List<Aluno> Editar(int idAluno) throws SQLException {  
    List<Aluno> alunos = new ArrayList<>();
    String sql = "SELECT * FROM aluno WHERE idAluno=?";
    
    try (PreparedStatement stm = conn.prepareStatement(sql)) {
        stm.setInt(1, idAluno);
        ResultSet rs = stm.executeQuery();
        
        while (rs.next()) {
            Aluno aluno = new Aluno(
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getDate("datanasc"),
                rs.getInt("ativo")
            );
            alunos.add(aluno);
        }
        
    } catch (Exception e) {
        System.out.println("Erro ao buscar dados do aluno: " + e.getMessage());
    }

    return alunos;
    }
    
    public int pegarID(String nome)throws SQLException{
        String sql = "Select idAluno from aluno where nome = ?";
        int id = 0;
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1,nome);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                 id = rs.getInt("idAluno");
            }
        }
       return id;
    }
    
    public int pegarIDcurso(String nomeAluno) throws SQLException {
    String sql = "SELECT curso FROM aluno WHERE nome = ?";
    int id = 0;
    try (PreparedStatement stm = conn.prepareStatement(sql)) {
        stm.setString(1, nomeAluno);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            id = rs.getInt("curso");
        }
    }
    return id;
    }

    
    public List<Aluno> listarAlunosCurso(int idCurso) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE curso = ?";
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, idCurso);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno(
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getDate("datanasc"),
                rs.getInt("ativo")
            );
            alunos.add(aluno);
        }
            }
            return alunos;
        }

    
  
}
