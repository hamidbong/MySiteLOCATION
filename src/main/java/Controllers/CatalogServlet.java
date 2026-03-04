package Controllers;
import java.io.*;
import java.util.List;

import DAO.BienDAO;
import Model.Bien;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


//servlet qui envoie tous les biens dans la base de données
public class CatalogServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Votre logique pour le catalogue de produits ici
        BienDAO BDAO = new BienDAO();
        
        List<Bien> products = BDAO.allBien();
        
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
        	oldSession.setAttribute("products", products);

        	System.out.println("email dans oldSession "+oldSession.getAttribute("email"));
        	
        }
        response.sendRedirect("Catalog.jsp");
        
        
        // Utilisation de RequestDispatcher pour envoyer les attributs à Catalog.jsp
        
    }
}
