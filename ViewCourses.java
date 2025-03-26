package DBOperations;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCourses extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter(); // explicit Object

		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demotable", "root", "root");

			PreparedStatement pstmt = con.prepareStatement("select * from course");

			ResultSet rs = pstmt.executeQuery();
			out.println("<h3 align='center'>View Courses</h3>");
			out.println("<table align=center border=1>");
			out.println("<tr bgcolor=lightblue>");
			out.println("<td>ID</td>");
			out.println("<td>Course Code</td>");
			out.println("<td>Title</td>");
			out.println("<td>Credits</td>");
			out.println("<td>LTPS</td>");
			out.println("<td>Department</td>");
			out.println("</tr>");
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getInt(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getDouble(4) + "</td>");
				out.println("<td>" + rs.getString(5) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			out.println(e);
		}	
	}
}