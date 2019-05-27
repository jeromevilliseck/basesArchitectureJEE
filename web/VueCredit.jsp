<!--6. importer le modele dans la page jsp-->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="web.CreditModel" %>
<%
    CreditModel monModele = (CreditModel) request.getAttribute("credit"); //recuperer l'attribut de l'objet request qui est dans le controleur, comme ça retourne un objet de type Object cet appel , on caste
%> <!--Ceci est du jsp standard, different des tags JSP-->

<!DOCTYPE html>
<html>
<head>
    <title>Crédit bancaire</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"> <!--Faire pointer la page jsp sur la page css-->
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<p class="spacer"></p>
<div class="container col-md-6 col-xm-12 col-sm-6">
    <div class="card bg-primary">
        <div class="card-header">Simulation du crédit</div>
        <div class="card-body">
            <form action="calculerMensualite.do" method="post">
                <div class="form-group">
                    <label for="mont">Montant:</label>
                    <input class="form-control" type="text" id="mont" name="montant"
                           value="<%=monModele.getMontant()%>"/>
                </div>
                <div class="form-group">
                    <label for="tx">Taux:</label>
                    <input class="form-control" type="text" id="tx" name="taux" value="<%=monModele.getTaux()%>"/>
                </div>
                <div class="form-group">
                    <label for="dur">Duree:</label>
                    <input class="form-control" type="text" id="dur" name="duree" value="<%=monModele.getDuree()%>"/>
                </div>
                <button class="btn btn-danger" type="submit">Calcul</button>
            </form>

            <div class="spacer">
                <label for="mens">Mensualité:</label>
                <label id="mens"><%= monModele.getMensualite() %>
                </label>
                <!--Un scriptlet en syntaxe courte, c'est en fait un out.println()-->
            </div>

        </div>
    </div>
</div>

<p>Resultat</p>


</body>
</html>
