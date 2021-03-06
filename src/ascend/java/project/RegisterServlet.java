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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	private final static Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String password = request.getParameter("password");
		int age = Integer.parseInt(request.getParameter("age"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		UserIDGenerator userIDGenerator = new UserIDGenerator();
		String userName = userIDGenerator.getUserId(firstName, lastName);
		String successMessage = "You are successfully registered and your Username is " + userName;

		RegisterBean registerBean = new RegisterBean();
		registerBean.setFirstName(firstName);
		registerBean.setLastName(lastName);
		registerBean.setUserName(userName);
		registerBean.setPassword(password);
		registerBean.setAge(age);
		registerBean.setPhone(phone);
		registerBean.setEmail(email);

		RegisterDao registerDao = new RegisterDao();
		String userRegistered = registerDao.registerUser(registerBean);
		if (userRegistered.equals("SUCCESS")) {
			HttpSession session = request.getSession();
			session.setAttribute("successMessage", successMessage);
			RegisterServlet.LOGGER.log(Level.INFO, userName + " " + successMessage);
			response.sendRedirect(request.getContextPath() + "/Registration.jsp");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("errMessage", userRegistered);
			RegisterServlet.LOGGER.log(Level.INFO, userName + " " + userRegistered);
			response.sendRedirect(request.getContextPath() + "/Registration.jsp");
		}
	}

}
