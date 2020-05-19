import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DemoCallableServlet", urlPatterns = "/home")
public class DemoCallableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
//        User user = userService.getUserByUserName("dao");
//
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//        request.setAttribute("user", user);
//        requestDispatcher.forward(request, response);

//        User user = new User("phidao", "123321", "0912345678", "phidv@gmail.com", "hai duong");
//        boolean isInserted = userService.insertUserInDatabase(user);
//        if (isInserted) {
//            String message = "insert successfully !";
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//            request.setAttribute("message", message);
//            requestDispatcher.forward(request, response);
//        } else {
//            String message = "insert not successfully !";
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//            request.setAttribute("message", message);
//            requestDispatcher.forward(request, response);
//        }

//        boolean isCommited = userService.testInsertListUsers();
//        if (isCommited) {
//            String message = "insert successfully !";
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//            request.setAttribute("message", message);
//            requestDispatcher.forward(request, response);
//        } else {
//            String message = "insert not successfully !";
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//            request.setAttribute("message", message);
//            requestDispatcher.forward(request, response);
//
//        }

        ResultSet listUser = userService.getListUser();
        ArrayList<User> userArrayList = new ArrayList<>();
        while (true) {
            try {
                if (!listUser.next()) break;
                String name = listUser.getString(1);
                String pass = listUser.getString(2);
                String phone = listUser.getString(3);
                String email = listUser.getString(4);
                String address = listUser.getString(5);
                userArrayList.add(new User(name, pass, phone, email, address));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        request.setAttribute("listUser" , userArrayList);
        requestDispatcher.forward(request, response);
    }
}
