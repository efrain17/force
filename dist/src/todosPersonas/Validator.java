/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todosPersonas;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Validator {

    private static final int NUMERO_DE_PROVINCIAS = 24;

    public static void keyType(KeyEvent evt, char n, int op) {
        System.out.println(n);
        switch (op) {
            case 1:
                if (((n < '0') || (n > '9')) && ((n != KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                break;
            case 2:
                if (((n < '0') || (n > '9')) && (n != KeyEvent.VK_BACK_SPACE) && (n != '.')) {
                    evt.consume();
                }
                break;
            case 3:
                if (n == 'ñ' || n == 'Ñ') {
                    evt.setKeyCode(165);
                } else if (((n < 'a') || (n > 'z')) && ((n < 'A') || (n > 'Z')) && (n != '´') && (n != (char) KeyEvent.VK_SPACE)) {
                    evt.consume();
                }
                break;
        }
    }

    public static boolean VerificaCedula(String cedula) {

        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {
            return false;
        }
        int[] d = new int[10];
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }
        int aviso1 = 0, aviso2 = 0, contador1 = 0, contador2 = 0;
        for (int i = 0; i < d.length; i++) {
            if (d[i] % 2 == 0) {
                aviso1 = 1;
                contador1 = contador1 + 1;
            }
        }
        for (int i = 1; i < d.length; i = i + 2) {
            if (d[i] % 2 == 0) {
                aviso2 = 1;
                contador2 = contador2 + 1;
            }
        }
        if ((aviso1 == 1 && contador1 == 10) || (aviso2 == 1 && contador2 == 5)) {
            return false;
        }
        int imp = 0;
        int par = 0;
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }
        int suma = imp + par;
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)
                + "0") - suma;
        d10 = (d10 == 10) ? 0 : d10;
        if (d10 == d[9]) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean validaRucEP(String ruc) {
        if (!((ruc.length() == 13) && ruc.matches("^[0-9]{10}$"))) {
            return false;
        }
        int prov = Integer.parseInt(ruc.substring(0, 2));
        boolean val = false;

        if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {
            return val;
        }

        Integer v1, v2, v3, v4, v5, v6, v7, v8, v9;
        Integer sumatoria;
        Integer modulo;
        Integer digito;
        Integer sustraendo;
        int[] d = new int[ruc.length()];

        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(ruc.charAt(i) + "");
        }

        v1 = d[0] * 3;
        v2 = d[1] * 2;
        v3 = d[2] * 7;
        v4 = d[3] * 6;
        v5 = d[4] * 5;
        v6 = d[5] * 4;
        v7 = d[6] * 3;
        v8 = d[7] * 2;
        v9 = d[8];

        sumatoria = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
        modulo = sumatoria % 11;
        sustraendo = modulo * 11;
        digito = 11 - (sumatoria - sustraendo);
        System.out.println("Digito RUC       --> " + digito);
        System.out.println("Digito Calculado --> " + v9);

        if (digito == v9) {
            val = true;
        } else {
            val = false;
        }
        return val;
    }
}
