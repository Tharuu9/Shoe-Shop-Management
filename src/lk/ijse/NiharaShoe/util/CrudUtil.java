package lk.ijse.NiharaShoe.util;

import lk.ijse.NiharaShoe.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static PreparedStatement execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject((i + 1), params[i]);
        }
        return statement;
    }
    public static ResultSet executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException {
        return execute(sql, params).executeQuery();
    }
    public static boolean executeUpdate(String sql, Object... params) throws SQLException, ClassNotFoundException {
        return execute(sql, params).executeUpdate()>0;
    }
}
