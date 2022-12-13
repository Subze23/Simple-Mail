/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
 
@WebServlet("/compose")
public class compose extends HttpServlet {
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
            HttpSession session = request.getSession(false);
            String username = (String)session.getAttribute("mail");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mail?useSSL=false&allowPublicKeyRetrieval=true","root","7122");
            stmt = con.prepareStatement("Insert Into messages (sender, receiver, message, subject) values(?,?,?,?)");
            String to=request.getParameter("to");
            String subject=request.getParameter("subject");
            String message=request.getParameter("message");
            stmt.setString(1, username);
            stmt.setString(2, to);
            stmt.setString(3, message);
            stmt.setString(4, subject);
            stmt.executeUpdate();
            response.sendRedirect("home.html");
        }
        catch(Exception e) {pw.write(e.toString());}
    }
}