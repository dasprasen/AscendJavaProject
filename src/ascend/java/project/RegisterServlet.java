package ascend.java.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		 String firstName = request.getParameter("firstname");
         String lastName = request.getParameter("lastname");
         String password = request.getParameter("password");
         UserIDGenerator userIDGenerator = new UserIDGenerator();
         String userName=userIDGenerator.getUserId(firstName,lastName);
         
         
         RegisterBean registerBean = new RegisterBean();
         registerBean.setFirstName(firstName);
         registerBean.setLastName(lastName);
         registerBean.setUserName(userName);
         registerBean.setPassword(password); 
         
         RegisterDao registerDao = new RegisterDao();
         String userRegistered = registerDao.registerUser(registerBean);
         if(userRegistered.equals("SUCCESS")){
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
         }else {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/Registration.jsp").forward(request, response);
         }
	}

}
