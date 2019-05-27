package web;

import metier.ICreditMetier;
import metier.impl.CreditMetierImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "cs", urlPatterns = {"/controler", "*.do"})
public class ControleurServlet extends HttpServlet {
    //3. Referencer l'interface METIER
    private ICreditMetier metier;

    @Override
    public void init() throws ServletException {
        //4. Instancier les objets de la couche metier qui sont enmembre prive dans ce controleur ici
        metier = new CreditMetierImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //8. Attention, la page jsp exploitant un objet creditModel de type CreditModel avec doPost, il faut aussi un objet credit modele dans doGet sinon NullPointerException sur creditModel dans la page jsp avec doGet()
        CreditModel creditModel = new CreditModel();
        request.setAttribute("credit", creditModel);


        request.getRequestDispatcher("VueCredit.jsp").forward(request, response);
        //par defaut getRequestDispatcher va chercher les vues jsp dans le dossier web sous intellij ou WebContent sous eclipse, jamais ailleurs


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. RECUPERATION CHAMPS DE FORMULAIRE PAR LE CONTROLEUR

//LECTURE DONNEES REQUETE

        //Lire les donnees saisies par utilisateur de l'objet REQ -> c'est la requete HTTP avec un tableau de valeurs, issues du formulaire de la page jsp
        double montant = Double.parseDouble(req.getParameter("montant")); //dans une requete HTTP on suppose tout ce qui vient c'est des String, il faudra les convertir ici dans la methode en cas de valeur numerique
        double taux = Double.parseDouble(req.getParameter("taux"));
        int duree = Integer.parseInt(req.getParameter("duree"));

        //2. APPEL A LA COUCHE METIER ET STOCKAGE PAR LE CONTROLEUR DES DONNEES DANS LE MODELE

//STOCKAGE DONNEES REQUETE

        //2.1 Création du modèle web -> CreditModel
        //2.2 Stocker les donnees dans le modele
        CreditModel creditModel = new CreditModel(); //Instanciation du modele
        creditModel.setMontant(montant); //affecter la recuperation issu du champ de formulaire dans l'attribut de l'objet model
        creditModel.setTaux(taux);
        creditModel.setDuree(duree);

//UTILISATION COUCHE METIER SUR DONNEES

        //5.1 utilisation de la couche metier pour recuperation du resultat
        double resultat = metier.calculerMensualiteCredit(montant, taux, duree);

//STOCKER RESULTATS COUCHE METIER DANS MODELE

        //5.2 stockage du resultat dans le couche modele
        creditModel.setMensualite(resultat);

        //5.3 stocker l'objet modele dans l'objet request
        req.setAttribute("credit", creditModel); //L'objet modele est stocké dans un attribut de request

//FAIRE UN FORWARD VERS LA VUE JSP

        req.getRequestDispatcher("VueCredit.jsp").forward(req, resp);

    }
}
