package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.UtilisateurDAO;
import Model.Utilisateur;

/**
 * Servlet implementation class UpdateProfileServlet
 */
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
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
    	//PrintWriter out = response.getWriter();
    	
    	String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String ID = request.getParameter("ID");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Utilisateur utilisateur = new Utilisateur(nom, prenom, email, password);
        UtilisateurDAO UDAO = new UtilisateurDAO();
        
        int rowcount = UDAO.ProfilUpdate(utilisateur, ID);
        if (rowcount > 0) {
        	request.setAttribute("status", "success");
        }else {
        	request.setAttribute("status", "failed");
        }
        response.sendRedirect("profil.jsp");
        
		//doGet(request, response);*/
	}

}
