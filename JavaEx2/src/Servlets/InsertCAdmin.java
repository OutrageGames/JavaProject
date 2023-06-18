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
 * Servlet implementation class InsertCAdmin
 */
@WebServlet("/InsertCAdmin")
public class InsertCAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCAdmin() {
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


		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		SecureRandom random = new SecureRandom();
		byte bytes[]= new byte[20];
		random.nextBytes(bytes);
		
		PrintWriter out = response.getWriter();
		out.println(id);
		out.println(username);
		out.println(password);
		Connection conna = null;

		try {
			conna = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

			PreparedStatement st = conna.prepareStatement("insert into contentadmins values(?, ?, ?)"); 
			st.setInt(1, Integer.valueOf(id)); 
	        st.setString(2, username); 
	        st.setString(3, EncryptionClass.getHashMD5(password,random.toString())); 
	        st.executeUpdate();
	        st.close(); 
	        conna.close(); 
            out.println("<html><body><b><br>Successfully Inserted"+ "</b></body></html>"); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
