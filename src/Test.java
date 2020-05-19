public class Test {
    public static void main(String[] args) {
        UserService userService = new UserService();
       User user = userService.getUserByUserName("dao");
        System.out.println(user.getName());
    }
}
