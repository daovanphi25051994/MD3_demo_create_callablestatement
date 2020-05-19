import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements IUserService {
    DAL dal;

    public UserService() {
        dal = new DAL();
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = null;
        String sql = "call getUserByUserName('" + userName + "'); ";
        ResultSet resultSet = dal.getData(sql);
        try {
            resultSet.next();
            String name = resultSet.getString(1);
            String pass = resultSet.getString(2);
            String phone = resultSet.getString(3);
            String email = resultSet.getString(4);
            String address = resultSet.getString(5);
             user = new User(name, pass, phone, email, address);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertUserInDatabase(User user) {
        String sql = "{call insert_user('" + user.getName() + "','" + user.getPass() + "','" + user.getPhone() + "','" + user.getEmail() + "','" + user.getAddress() + "')}";
        return dal.updateData(sql);
    }
}
