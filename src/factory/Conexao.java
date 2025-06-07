
package factory;

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
    }
    
}
