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
 * Servlet implementation class matchToHall
 */
@WebServlet("/matchToHall")
public class matchToHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public matchToHall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM hours ");
			ResultSet Rs = stmt.executeQuery();

			PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM halls ");
			ResultSet Rs2 = stmt2.executeQuery();

			PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM movies ");
			ResultSet Rs3 = stmt3.executeQuery();

			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='ISO-8859-1'>");
			out.println("<title>choose hall</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form method='post' action = 'matchToHall'>");
			out.println("<h3>choose movie: </h3>");
			out.println("<select name = 'asd1' ><br>");
			while (Rs3.next()) {
				out.println("<option value=" + Rs3.getString(1) + ">" + Rs3.getString(2) + "</option><br>");
			}
			out.println("</select> <br>");

			out.println("<h3>choose time: </h3>");
			out.println("<select name = 'asd2' ><br>");
			while (Rs.next()) {
				out.println("<option value=" + Rs.getString(1) + ">" + Rs.getString(2) + "</option><br>");
			}
			out.println("</select> <br>");

			out.println("<h3>choose hall:</h3>");
			out.println("<select name = 'asd3' ><br>");
			while (Rs2.next()) {
				out.println("<option value=" + Rs2.getString(1) + ">" + Rs2.getString(2) + "</option><br>");
			}
			out.println("</select><br><br>");

			out.println("<input type='submit' value='submit'>");
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
        
        String movie = request.getParameter("asd1");
		String time = request.getParameter("asd2");
		String hall = request.getParameter("asd3");
		
		PrintWriter out = response.getWriter();
		out.println("Done!");
		Connection conna = null;

		try {
			conna = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");
			PreparedStatement stmta = conna.prepareStatement("update movies set hall = (select nameofhall from halls where halls.id = "+hall+"),time = (select time from hours where hours.id = "+time+") where movies.id = "+movie+"");
			stmta.executeUpdate(); 

			stmta.close(); 
            conna.close(); 

			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		
	}

}
