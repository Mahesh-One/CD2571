package DBOperations;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCourse extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter(); // explicit Object

		int cid = (int) (Math.random() * 9999) + 1;

		String ccode = request.getParameter("coursecode");

		String ctitle = request.getParameter("coursetitle");

		double credits = Double.parseDouble(request.getParameter("credits"));

		String ltps = request.getParameter("ltps");

		String department = request.getParameter("department");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demotable", "root", "root");

			PreparedStatement pstmt = con.prepareStatement(
					"insert into course values(?,?,?,?,?,?)");

			pstmt.setInt(1, cid);
			pstmt.setString(2, ccode);
			pstmt.setString(3, ctitle);
			pstmt.setDouble(4, credits);
			pstmt.setString(5, ltps);
			pstmt.setString(6, department);

			pstmt.executeUpdate();

			pstmt.close();
			con.close();
			
			response.sendRedirect("coursesuccess.html");
		} catch (Exception e) {
			out.println(e);
		}

	}
}