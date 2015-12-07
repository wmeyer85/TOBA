/* Test */
package NewCustomer;

import toba.business.User;
import toba.data.UserDB;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.NoSuchAlgorithmException;

import toba.util.PasswordUtil;


public class NewCustomerServlet extends HttpServlet {
        
    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {

        String url = "/index.jsp";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/new_customer.html";    // the "join" page
        }
        else if (action.equals("add")) {                
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zipcode = request.getParameter("zipcode");
            String email = request.getParameter("email");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            // store data in User object and save User object in database
            User user = new User(firstName, lastName, phone, address, city, state, zipcode, email, userName, password);
            
            String message;
             if (firstName.isEmpty() || lastName.isEmpty() ||
               phone.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || zipcode.isEmpty() || email.isEmpty() ||
                userName.isEmpty() || password.isEmpty()) {
                message = "Please fill out all the text boxes.";
                   url = "/new_customer.html";
            }
                else {
                message = null;
                   url = "/success.html";
                   
            
             if (UserDB.user_idExists(user.getUserName())) {
                 message = "This Username already exists.<br>" +
                         "Please enter another Username.";
                 url = "/index.jsp";
             }
             else {
                 message = "";
                 url = "/success.html";
                 UserDB.insert(user);
             }
  }
            // set User object in request object and set URL
            request.setAttribute("user", user);
            request.setAttribute("message", message);
            
        }
        
        // forward request and response objects to specified URL
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }    
    
    @Override
    protected void doGet(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {
        doPost(request, response);
        
        // get parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String message;
        try {
            PasswordUtil.checkPasswordStrength(password);
            message = "";
        } catch (Exception e) {
            message = e.getMessage();
        }
        request.setAttribute("message", message);        
        
        // hash and salt password
        String hashedPassword;
        String salt = "";
        String saltedAndHashedPassword;
        try {
            hashedPassword = PasswordUtil.hashPassword(password);
            salt = PasswordUtil.getSalt();
            saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(password);                    
            
        } catch (NoSuchAlgorithmException ex) {
            hashedPassword = ex.getMessage();
            saltedAndHashedPassword = ex.getMessage();
        }
        request.setAttribute("hashedPassword", hashedPassword);
        request.setAttribute("salt", salt);
        request.setAttribute("saltedAndHashedPassword", saltedAndHashedPassword);
        
        String url = "/index.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }    
}