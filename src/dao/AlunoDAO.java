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
/**
 *
 * @author faelb
 */
public class AlunoDAO {
    private Connection conn;

    public AlunoDAO() {
        this.conn = new ConnectionDB().getConnection();
    }
    
    public void insertDB(int idAluno,String nome,String cpf,int telefone,String email,Date datanasc)throws SQLException{
        String sql = "INSERT INTO clientes(idAluno, nome, cpf, telefone, email, datanasc) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, idAluno);
        stm.setString(2, nome);
        stm.setString(3, cpf);
        stm.setInt(4, telefone);
        stm.setString(5, email);
        stm.setDate(6, (java.sql.Date)datanasc);
        
        stm.close();
    }
    
    
}
