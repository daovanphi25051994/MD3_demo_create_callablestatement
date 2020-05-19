import java.sql.ResultSet;

public interface IUserService {
    public User getUserByUserName(String userName);
    public boolean insertUserInDatabase(User user);
    public boolean testInsertListUsers();
    public ResultSet getListUser();
    public void editUser(String userName);
    public boolean deleteUser(String userName);
}
