import java.sql.*;

public class DAL {
    Connection connection;
    CallableStatement callableStatement;

    public DAL() {
        String diverSQL = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/lucy_shop";
        String name = "root";
        String pass = "password";
        try {
            Class.forName(diverSQL);
            connection = DriverManager.getConnection(url, name, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getData(String sql) {
        callableStatement = null;
        try {
            callableStatement = connection.prepareCall(sql);
            return callableStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean updateData(String sql) {
        callableStatement = null;
        try {
            callableStatement = connection.prepareCall(sql);
            return callableStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
