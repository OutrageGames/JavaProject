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
 * Servlet implementation class buyTicket
 */
@WebServlet("/buyTicket")
public class buyTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				

		String user = request.getParameter("user");
		String movieid = request.getParameter("asd1");
		PrintWriter out = response.getWriter();

		String b = null;
		String c = null;
		String d = null;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			PreparedStatement stmta = conn.prepareStatement("select * from movies where id = "+movieid+"");
			
			ResultSet Rs = stmta.executeQuery();
			
			while (Rs.next()) {
				out.println(Rs.getString(1) + Rs.getString(2) + Rs.getString(3) +Rs.getString(4) );

				b = 	Rs.getString(2);
				c = 	Rs.getString(3);
				d = 	Rs.getString(4);
			}

			
			PreparedStatement stmtb = conn.prepareStatement("insert into clientsres values(?, ?, ?, ?)");
			stmtb.setString(1, user); 
			stmtb.setString(2, b); 
			stmtb.setString(3, c);
			stmtb.setString(4, d);

			  
			
			stmtb.executeUpdate(); 
			  

			stmtb.close(); 
			

			
            conn.close(); 
  
            response.sendRedirect("showHistory");

        } 
        catch (Exception e) { 
            e.printStackTrace(); 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 
	}

}
