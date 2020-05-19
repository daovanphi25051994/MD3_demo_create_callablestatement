public interface IUserService {
    public User getUserByUserName(String userName);
    public boolean insertUserInDatabase(User user);
}
