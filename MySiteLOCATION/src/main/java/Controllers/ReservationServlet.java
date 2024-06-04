package Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DAO.ReservationDAO;
import Model.Reservation;
//Servlet pour l'ajout d'une nvelle reservation
public class ReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReservationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        
        String Status = "Dispo";
        String debut = request.getParameter("debut");
        String fin = request.getParameter("fin");

        HttpSession oldSession = request.getSession(false);
        String ID_user = (String) oldSession.getAttribute("id_user");
        String productId = request.getParameter("productId");
        
        // Définir le format de la chaîne de date entrée
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            // Convertir la chaîne de caractères en java.util.Date
        	java.util.Date debutDate = dateFormat.parse(debut);
            java.util.Date finDate = dateFormat.parse(fin);

            // Convertir Date a Timestamp
            Timestamp date_deb = new Timestamp(debutDate.getTime());
            Timestamp date_fin = new Timestamp(finDate.getTime());
             
            String prixSTR = request.getParameter("prix");
            double prix = Double.parseDouble(prixSTR);
            
            double Montant_total = prix * differenceInDays(date_deb, date_fin);
            
            Reservation reservation = new Reservation(Status, date_deb, date_fin, Montant_total);
            ReservationDAO RDAO = new ReservationDAO();
            
            int rowcount = RDAO.AddReservation(reservation,productId , ID_user);
            dispatcher = request.getRequestDispatcher("Catalog.jsp");
            if (rowcount > 0) {
                request.setAttribute("statusRES", "success");
            } else {
                request.setAttribute("statusRES", "failed");
            }
            
        } catch (ParseException e) {
            // Gérer l'exception si la chaîne de date n'est pas au bon format
            e.printStackTrace();
        }
        
        dispatcher.forward(request, response);
    }
    
    //convertir date en nombre de jour
    public static long differenceInDays(Timestamp date_deb, Timestamp date_fin) {
        // Convertir les Timestamps en millisecondes
        long deb = date_deb.getTime();
        long fin = date_fin.getTime();
        
        // Calculer la différence en millisecondes
        long differenceInMilliseconds = Math.abs(fin - deb);
        
        // Convertir la différence en jours
        long differenceInDays = differenceInMilliseconds / (1000 * 60 * 60 * 24);
        
        return differenceInDays;
    }
    
}
