
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author faelb
 */
public class ConnectionDB {
      Connection con;
        
        public Connection getConnection(){
            try{
                return DriverManager.getConnection("jdbc:mysql://localhost/SGCA","root","fatec");
            }
            catch(SQLException excecao){
                throw new RuntimeException(excecao);
            }
        }
}
