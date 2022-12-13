import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
 
@WebServlet("/mail")
public class mail extends HttpServlet {
    //private static final long serialVersionUID = 1L;
@Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        //initializing connections
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            HttpSession session = request.getSession(false);
            int id = Integer.parseInt(request.getParameter("id"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mail?useSSL=false&allowPublicKeyRetrieval=true","root","7122");
            stmt = con.prepareStatement("SELECT * FROM messages WHERE id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            pw.write("<!DOCTYPE html>\n" +
"<html lang=\"en\" dir=\"ltr\">\n" +
"  <head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <title>Received</title>\n" +
"    <link rel=\"stylesheet\" href=\"CSS/stylecom.css\">\n" +
"  </head>\n" +
"  <body>\n" +
"    <div class=\"center\">\n" +
"      <h1>Inbox</h1>\n" +
"");
            pw.write("<table  style='width:100%'>");
            while ( rs.next() ) {
                pw.write("<tr><td style='width:30%'>" + rs.getString("sender") + "</td><td style='width:50%'>" + rs.getString("subject")+ "</td><td style='width:20%'>" + rs.getString("curtime") + "</td></tr>");
                pw.write("<tr><td colspan='3'>"+ rs.getString("message") +"</td></tr>");
            }
pw.write("</table>");
            pw.write("    </div>\n" +
"\n" +
"  </body>\n" +
"</html>");
        }
        catch(Exception e) {pw.write(e.toString());}
    }
}