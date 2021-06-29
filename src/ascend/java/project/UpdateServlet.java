package ascend.java.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		UpdateDao updateDao = new UpdateDao();
		String userUpdated = updateDao.updateRecords(username, age, phone, email);
		if (userUpdated.equals("Record Updated Successfully")) {
			HttpSession session = request.getSession();
			session.setAttribute("successMessage", userUpdated);
			response.sendRedirect(request.getContextPath() + "/User.jsp");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("errMessage", userUpdated);
			response.sendRedirect(request.getContextPath() + "/User.jsp");
		}
	}

}
