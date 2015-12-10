package Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toba.business.User;
import toba.data.UserDB;
public class ResetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String passwordOld = request.getParameter("passwordOld");
        String passwordNew = request.getParameter("passwordNew");

        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            

            if (passwordOld == null || passwordOld.isEmpty() || passwordNew == null || passwordNew.isEmpty()) {
                request.setAttribute("msg", "All fields are required");
                getServletContext().getRequestDispatcher("/password_reset.jsp").forward(request, response);

            } else if (!user.getPassword().equals(passwordOld)) {
                request.setAttribute("msg", "Old password is not valid");
                getServletContext().getRequestDispatcher("/password_reset.jsp").forward(request, response);
            } else {
                user.setPassword(passwordNew);
                UserDB.update(user);
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
