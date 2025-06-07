
package dao;

import factory.ConnectionDB;
import java.sql.Connection;

/**
 *
 * @author faelb
 */
public class CursoDAO {
    private Connection conn;

    public CursoDAO() {
        this.conn = new ConnectionDB().getConnection();
    }
    
    
}
