package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class showHistory
 */
@WebServlet("/showHistory")
public class showHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PrintWriter out = response.getWriter();
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			PreparedStatement stmtc = conn.prepareStatement("select * from clientsres where username = '"+ClientServlet.realname+"' ;");
			
			ResultSet Rs = stmtc.executeQuery();
			
			out.println("<h1>Welcome "+ClientServlet.realname+"!</h1>(role:<u>Client</u>)");
			out.println("<h4><a href='login.jsp'>LOGOUT</a></h4>");
			
			out.println("<table><tr>");
			out.println("<th> Movie name</th> <th> Time</th> <th> Hall</th> </tr>");	
			while (Rs.next()) {
				out.println("<tr>");
				out.println("<th>"+ Rs.getString(2)+"</th>" + "<th>"+Rs.getString(3)+"</th>" + "<th>"+Rs.getString(4) +"</th>");
				out.println("</tr>");
			}
			out.println("</table>");
			

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
