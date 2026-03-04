package Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.UtilisateurDAO;

/**
 * Servlet implementation class ServletVerification
 */
public class ServletVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVerification() {
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
		// TODO Auto-generated method stub
		
		
        response.setContentType("text/html");
   
    	String id_user = request.getParameter("id_user");
        String password = request.getParameter("password");
        
        UtilisateurDAO UDAO = new UtilisateurDAO();
        boolean verif =UDAO.login(id_user, password);

    	RequestDispatcher dispatcher = null;
    	
    	if (verif == true) {
            //get the old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession newSession = request.getSession(true);

            //setting session to expiry in 5 mins
            newSession.setMaxInactiveInterval(5*60);
            newSession.setAttribute("id_user", id_user);
            
            response.sendRedirect("index.jsp");
    	}
    	else {
    		dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request, response);
    	}
        
        /*if(verif == true) {
        	dispatcher = request.getRequestDispatcher("index.jsp");
        	
            }
        else {
        	dispatcher = request.getRequestDispatcher("login.jsp");
            }*/
        //dispatcher.forward(request, response);
        }

}
