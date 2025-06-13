
package dao;

import factory.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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
        
        
        public void editarCurso(int idCurso,String nome , int cargaHoraria, int limiteAlunos, int ativos) throws SQLException {
        String sql = "UPDATE curso SET nome=?, cargaHoraria=?, limiteAlunos=?,limiteAlunos=? WHERE idCurso=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        
        
        try {
            stm.setString(1, nome);
            stm.setInt(2, cargaHoraria);
            stm.setInt(3, limiteAlunos);
            stm.setInt(4, ativos);
            stm.setInt(5, idCurso);
        
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
         
        
        
}
