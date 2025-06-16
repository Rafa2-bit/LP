
package factory;

import gui.CadastroAluno;
import gui.CadastroCurso;
import gui.SplashScreen;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author faelb
 */
public class Conexao {

    public static void main(String[] args)  throws SQLException{
       Connection connection = new ConnectionDB().getConnection();
        System.out.println("Conexão aberta");
        connection.close();
    
     java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    
}}
