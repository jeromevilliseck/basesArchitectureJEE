<!--6. importer le modele dans la page jsp-->
<%@ page import="web.CreditModel" %>
<%
    CreditModel monModele = (CreditModel)request.getAttribute("credit"); //recuperer l'attribut de l'objet request qui est dans le controleur, comme ça retourne un objet de type Object cet appel , on caste
%> <!--Ceci est du jsp standard, different des tags JSP-->


<!DOCTYPE html>
<html>
<head>
    <title>Crédit bancaire</title>
    <meta charset="utf-8">
</head>
<body>
    <div>
        <form action="calculerMensualite.do" method="post">
            <table>
                <tr>
                    <td>Montant:</td>
                    <td><input type="text" name="montant" /></td>
                    <td>DH</td>
                </tr>
                <tr>
                    <td>Taux:</td>
                    <td><input type="text" name="taux" /></td>
                    <td>%</td>
                </tr>
                <tr>
                    <td>Duree:</td>
                    <td><input type="text" name="duree" /></td>
                    <td>ans</td>
                </tr>
            </table>
            <button type="submit">ENvoyer</button>
        </form>
    </div>

    <p>Resultat</p>

    <div>
        Mensualité = <%= monModele.getMensualite() %>
        <!--Un scriptlet en syntaxe courte, c'est en fait un out.println()-->
    </div>
</body>
</html>
