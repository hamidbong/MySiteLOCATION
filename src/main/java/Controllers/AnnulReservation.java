package Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.ReservationDAO;

/**
 * Servlet implementation class AnnulReservation
 */
public class AnnulReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnulReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession oldSession = request.getSession(false);
        if (oldSession == null) {
        	response.sendRedirect("login.jsp");
        }
        
        RequestDispatcher dispatcher = null;
		
		String id_user = request.getParameter("id_user");
		String id_reser = request.getParameter("id_reser");
		
		System.out.println("iddddddd"+ id_reser);
		ReservationDAO RDAO = new ReservationDAO();
		int rowcount = RDAO.DelReservation(id_reser, id_user);
		
        dispatcher = request.getRequestDispatcher("LesRESERVATION.jsp");
        if (rowcount > 0) {
        	oldSession.setAttribute("statusDEL_RESERVATION", "success");
        }else {
        	oldSession.setAttribute("statusDEL_RESERVATION", "failed");
        }
            
        
        dispatcher.forward(request, response);
	}

}
