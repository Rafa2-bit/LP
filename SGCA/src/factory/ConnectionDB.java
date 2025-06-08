
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
                return DriverManager.getConnection("jdbc:mysql://localhost/sgca","root","fatec");
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
}
