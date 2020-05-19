import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        User user = new User("phidao", "123321", "0912345678", "phidv@gmail.com", "hai duong");
        boolean isInserted = userService.insertUserInDatabase(user);
        if (isInserted) {
            String message = "insert successfully !";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("message", message);
            requestDispatcher.forward(request, response);
        } else {
            String message = "insert not successfully !";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("message", message);
            requestDispatcher.forward(request, response);
        }

    }
}
