package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class insertMovie
 */
@WebServlet("/insertMovie")
public class insertMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM hours ");
			ResultSet Rs = stmt.executeQuery();

			PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM halls ");
			ResultSet Rs2 = stmt2.executeQuery();


			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='ISO-8859-1'>");
			out.println("<title>Insert title here</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form method='post' action = 'insertMovie'>");
			
			out.println("<h3>Insert movie ID:</h3>");
			out.println("<td><input type='text' name='id'></td>");
			
			out.println("<h3>Insert movie name:</h3>");
			out.println("<td><input type='text' name='moviename'></td>");

			

			out.println("<br><br><input type='submit' value='insert'>");
			out.println("</form>");
			out.println("<h4><a href='login.jsp'>LOGOUT</a></h4>");
			out.println("</html>");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			
			PreparedStatement stmt = conn.prepareStatement("insert into movies values(?, ?)");

			stmt.setInt(1, Integer.valueOf(request.getParameter("id"))); 
			
			stmt.setString(2, request.getParameter("moviename")); 
			
			
			stmt.executeUpdate(); 
			  

			stmt.close(); 
            conn.close(); 
  
            response.sendRedirect("ContentAdminServlet");

        } 
        catch (Exception e) { 
            e.printStackTrace(); 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 

	}

}
