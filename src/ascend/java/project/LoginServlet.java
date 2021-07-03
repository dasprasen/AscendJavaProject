package ascend.java.project;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private final static Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUserName(userName);
		loginBean.setPassword(password);
		LoginDao loginDao = new LoginDao();

		try {
			String userValidate = loginDao.authenticateUser(loginBean);

			if (userValidate.equals("Admin_Role")) {
				LoginServlet.LOGGER.log(Level.INFO, userName + " " + "Admin Logged in");
				HttpSession session = request.getSession();
				session.setAttribute("Admin", userName);
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("Admin.jsp").forward(request, response);
			} else if (userValidate.equals("User_Role")) {
				LoginServlet.LOGGER.log(Level.INFO, userName + " " + "User Logged in");
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10 * 60);
				session.setAttribute("User", userName);
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("User.jsp").forward(request, response);
			} else {
				LoginServlet.LOGGER.log(Level.WARNING, userValidate);
				HttpSession session = request.getSession();
				session.setAttribute("errMessage", userValidate);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} catch (IOException e1) {
			LoginServlet.LOGGER.log(Level.WARNING, userName, e1.getMessage());
		} catch (Exception e2) {
			LoginServlet.LOGGER.log(Level.WARNING, userName, e2.getMessage());
		}
	}

}
