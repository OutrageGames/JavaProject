package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.EncryptionClass;
import mainpackage.serverTest;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	
	static {
	    try {
	      Class.forName("org.postgresql.Driver");
	    } catch (ClassNotFoundException e) {
	      System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
	    }
	  }
	
	
	private static final long serialVersionUID = 1L;
       
	public static String realname;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		out.println("<h1>Welcome "+realname+"!</h1>(role:<u>Client</u>)");
		out.println("<h4><a href='login.jsp'>LOGOUT</a></h4>");
		out.println("<html>");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			System.out.println("Connected to the PostgreSQL server successfully.");

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movies ");
			ResultSet Rs = stmt.executeQuery();
				
			
			out.println("<form method='post' action = 'buyTicket'>");
			out.println("<h3>Choose movie: </h3>");
			out.println("<select name = 'asd1' ><br>");
			while (Rs.next()) {
				out.println("<option value=" + Rs.getString(1) + ">" + Rs.getString(2) + "</option><br>");
			}
			out.println("</select> <br><br>");
			out.println("<input  name='user' type = 'hidden' value = "+realname+">");
			out.println("<input type='submit' value='buy ticket'>");
			out.println("</form>");

			
			out.println("</html>");
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

        
        
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


			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clients");
			ResultSet Rs = stmt.executeQuery();

			while (Rs.next()) {
				out.println(Rs.getString(2));
				out.println(Rs.getString(3));
				if (Rs.getString(2).equals(username) && Rs.getString(3).equals(EncryptionClass.getHashMD5(password,random.toString()))) {
					done = true;
					response.sendRedirect("ClientServlet");
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
