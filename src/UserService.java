import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean testInsertListUsers() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("a","b","c","d","e"));
        list.add(new User("f","g","h","j","k"));
        list.add(new User("l","m","n","b","v"));
        try {
            dal.connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (User user : list){
            if (user.getName().equals("l")){
                try {
                    dal.connection.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return false;
            }
            String sql = "{call insert_user('" + user.getName() + "','" + user.getPass() + "','" + user.getPhone() + "','" + user.getEmail() + "','" + user.getAddress() + "')}";
             dal.updateData(sql);
        }
        try {
            dal.connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
