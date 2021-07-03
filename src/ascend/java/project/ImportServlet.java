package ascend.java.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/ImportServlet")
@MultipartConfig
public class ImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportServlet() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part part = request.getPart("filename");
		String filename = part.getSubmittedFileName();
		File f = new File(filename);
		String absolute = f.getAbsolutePath();

		String line = "";
		String splitBy = ",";
		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(absolute));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] user = line.split(splitBy);
				UserIDGenerator userIDGenerator = new UserIDGenerator();
				String username = userIDGenerator.getUserId(user[0], user[1]);
				String password = userIDGenerator.getRandomPassword();
				PasswordEncryptor encryptor = new BasicPasswordEncryptor();
				password = encryptor.encryptPassword(password);
				int age = Integer.parseInt(user[2]);
				insertRowInDB(username, user[0], user[1], age, user[3], user[4], password);
			}
			response.sendRedirect("Admin.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertRowInDB(String username, String firstname, String lastname, int age, String phone, String email,
			String password) throws SQLException {

		Connection con = null;
		PreparedStatement statement = null;
		con = DatabaseConnection.getConnection();
		String sql = "insert into users(userName,firstName,lastName,age,phone,email,password) values (?,?,?,?,?,?,?)";
		statement = con.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, firstname);
		statement.setString(3, lastname);
		statement.setInt(4, age);
		statement.setString(5, phone);
		statement.setString(6, email);
		statement.setString(7, password);
		statement.executeUpdate();
		con.close();
	}
}
