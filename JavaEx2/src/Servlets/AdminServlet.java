package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.EncryptionClass;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	static {
	    try {
	      Class.forName("org.postgresql.Driver");
	    } catch (ClassNotFoundException e) {
	      System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
	    }
	  }
	
	private static final long serialVersionUID = 1L;
	
	String realname;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<h1>Welcome "+realname+"!</h1>(role:<u>Admin</u>)");
		out.println("<h4><a href='login.jsp'>LOGOUT</a></h4>");
		
		out.println("<form method='post' action = 'InsertCAdmin'>");
		out.println("<u><h3>Insert new Content Admin: </h3></u>");

		out.println("<td>ID: </td>");
		out.println("<td><input type='text' name='id'></td>");
		out.println("<td>Username: </td>");
		out.println("<td><input type='text' name='username'></td>");
		out.println("<td>Password: </td>");
		out.println("<td><input type='text' name='password'></td>");
		
		out.println("<input type='submit' value='insert'>");
		out.println("</form>");
		
		
		
		out.println("<form method='post' action = 'RemoveCAdmin'>");
		out.println("<u><h3>Remove Content Admin: </h3></u>");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM contentadmins");
			ResultSet Rs = stmt.executeQuery();
			
		out.println("<select name = 'removeSelection'><br>");
		while (Rs.next()) {
			out.println("<td><option value=" + Rs.getString(1) + ">" + Rs.getString(2) + "</option></td>");
		}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		out.println("</select> <br><br>");
		
		out.println("<td><input type='submit' value='remove'></td>");

		
		out.println("</form>");

		
		
		out.println("</html>" ) ;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SecureRandom random = new SecureRandom();
		byte bytes[]= new byte[20];
		random.nextBytes(bytes);
        
        String username = request.getParameter("username");
		String password = request.getParameter("password");
		realname = username;

		boolean done = false;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");


			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admins");
			ResultSet Rs = stmt.executeQuery();

			while (Rs.next()) {
				out.println(Rs.getString(2));
				out.println(Rs.getString(3));
				if (Rs.getString(2).equals(username) && Rs.getString(3).equals(EncryptionClass.getHashMD5(password,random.toString()))) {
					done = true;
					response.sendRedirect("AdminServlet");
					break;
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if (done == false) {
			response.sendRedirect("ERROR.jsp");
		}
	}

}
