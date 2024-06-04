package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import DAO.ReservationDAO;

import Model.Reservation;

/**
 * Servlet implementation class GestionReservation
 */

//servlet qui renvoie la liste des toutes les reservations de l'utilisateur
public class GestionReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession oldSession = request.getSession(false);
        if (oldSession == null) {
        	response.sendRedirect("login.jsp");
        }
        
        String id_user=(String) oldSession.getAttribute("id_user");
        ReservationDAO RDAO = new ReservationDAO();
        
        List<Reservation> listofRESER = RDAO.allReservation(id_user);
        oldSession.setAttribute("listRESERVATION", listofRESER);
        response.sendRedirect("LesRESERVATION.jsp");
	}

	

}
