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
 * Servlet implementation class RemoveCAdmin
 */
@WebServlet("/RemoveCAdmin")
public class RemoveCAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adminName = request.getParameter("removeSelection");
		
		PrintWriter out = response.getWriter();
		Connection conna = null;
		out.println(adminName);

		try {
			conna = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");
			PreparedStatement stmta = conna.prepareStatement("DELETE FROM contentadmins WHERE id ="+adminName+"");
			stmta.executeQuery();
			stmta.executeUpdate();
			ResultSet Rsa = stmta.executeQuery();

			
			response.sendRedirect("AdminServlet");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
