package metier.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditMetierImplTest {
    //Pre-conditions -> créer les références ici
    private CreditMetierImpl creditMetierObject;

    @BeforeEach //faire pointer les références sur les classes ici
    void setUp() {
        creditMetierObject = new CreditMetierImpl();
    }

    @Test //
    void calculerMensualiteCredit() {
        //Expected
        double expected = 1265.2987;

        //Actual
        double capital = 200000;
        int duree = 240;
        double taux = 4.5;

        double actual = creditMetierObject.calculerMensualiteCredit(capital, taux, duree);

        //Result
        assertEquals(expected, actual, 0.0001); //Le delta c'est la tolérance de précision du résultat
        //Le resultat doit ici etre egal a 4 chiffres apres la virgule
    }
}