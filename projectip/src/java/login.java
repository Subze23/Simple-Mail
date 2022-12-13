/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
 
@WebServlet("/login")
public class login extends HttpServlet {
    //private static final long serialVersionUID = 1L;
@Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        //initializing connections
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mail?useSSL=false&allowPublicKeyRetrieval=true","root","7122");
            stmt = con.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            
            if ( rs.next() ) {  
                    HttpSession session = request.getSession(true);
                    session.setAttribute("mail", rs.getString("mailid"));
                    response.sendRedirect("home.html");
            } else {
                    pw.write("<h3>Incorrect Username or Password!!!</h3>");
                }
        }// End of try block
        catch(Exception e) {pw.write(e.toString());}
    }
}