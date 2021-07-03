package ascend.java.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadCSVServlet
 */
@WebServlet("/DownloadCSVServlet")
public class DownloadCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadCSVServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			con = DatabaseConnection.getConnection();
			String query = "select username,firstname,lastname,age,phone,email,role from users where role='users'";
			preparedStatement = con.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter(new File("C:\\Users\\sever\\Desktop\\users.csv"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("username");
			stringBuilder.append(",");
			stringBuilder.append("firstname");
			stringBuilder.append(",");
			stringBuilder.append("lastname");
			stringBuilder.append(",");
			stringBuilder.append("age");
			stringBuilder.append(",");
			stringBuilder.append("phone");
			stringBuilder.append(",");
			stringBuilder.append("email");
			stringBuilder.append(",");
			stringBuilder.append("role");
			stringBuilder.append(",");
			stringBuilder.append("\r\n");
			while (resultSet.next()) {
				stringBuilder.append(resultSet.getString("username"));
				stringBuilder.append(",");
				stringBuilder.append(resultSet.getString("firstname"));
				stringBuilder.append(",");
				stringBuilder.append(resultSet.getString("lastname"));
				stringBuilder.append(",");
				stringBuilder.append(resultSet.getInt("age"));
				stringBuilder.append(",");
				stringBuilder.append(resultSet.getString("phone"));
				stringBuilder.append(",");
				stringBuilder.append(resultSet.getString("email"));
				stringBuilder.append(",");
				stringBuilder.append(resultSet.getString("role"));
				stringBuilder.append("\r\n");
			}

			printWriter.write(stringBuilder.toString());
			printWriter.close();
			response.sendRedirect("Admin.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
