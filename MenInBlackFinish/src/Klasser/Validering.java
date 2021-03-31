/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 *
 * @author Josef
 */
public class Validering {

    public static boolean lengthCheck(String string, int lengthInclusive) {
        return string.length() <= lengthInclusive;
    }

    public static boolean notBlankString(String string) {
        if (string == null) {
            JOptionPane.showMessageDialog(null, "Här får du ej lämna fältet tomt");
            return false;
        }
        return !string.isBlank();
    }

    public static boolean checkKontorsbeteckning(String kBeteckning) {
        return lengthCheck(kBeteckning, 25);
    }

    public static boolean validPassword(String pass) {

        if (notBlankString(pass) && lengthCheck(pass, 6)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Lösenordet får ej vara blankt eller mer än 6 siffror");
        }
        return false;

    }

    public static boolean validDate(String date) {
        String[] dividedDate = date.split("-");
        if (dividedDate.length == 3) {
            for (int i = 0; i < dividedDate.length; i++) {
                if (isDigit(dividedDate[i]) == false) {
                    JOptionPane.showMessageDialog(null, "Skriv in ett datum med formatet yyyy-mm-dd");
                    return false;

                }
            }
            if (Integer.parseInt(dividedDate[0]) < 0 || Integer.parseInt(dividedDate[1]) < 0 || Integer.parseInt(dividedDate[2]) < 0) {

                return false;
            }
            if (Integer.parseInt(dividedDate[1]) > 12 || Integer.parseInt(dividedDate[2]) > 31) {
                JOptionPane.showMessageDialog(null, "Skriv in ett datum med formatet yyyy-mm-dd");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Skriv in ett datum med formatet yyyy-mm-dd");
            return false;
        }
        return true;
    }

    public static boolean validName(String name) {
        if (notBlankString(name) && lengthCheck(name, 20)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Skriv in ett namn med som är max 20 tecken");
        }
        return false;
    }

    public static boolean validTelefon(String telefon) {
        if (checkTelefon(telefon)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Skriv in ett telefonnummer med max 10 siffror");
            return false;
        }
    }

    public static boolean isDigit(String string) {
        try {
            int d = Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Skriv in ett id med heltalssiffror.");
            return false;
        }
        return true;

    }

    public static boolean isPlats(String string) {
        if (isDigit(string) && notBlankString(string) && lengthCheck(string, 10)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Skriv in ett plats id med siffror på max 10");
        }
        return false;
    }

    public static boolean isID(String string) {
        if (isDigit(string) && notBlankString(string) && lengthCheck(string, 10)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Skriv in ett id med siffror på max 10");
        }
        return false;
    }

    public static boolean validRas(String string) {
        if (string.equals("Boglodite") || string.equals("Squid") || string.equals("Worm")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ogiltig ras, kolla stavning och stor bokstav i början");
        }
        return false;
    }

    public static boolean checkDigit(String string) {
        try {
            int d = Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNotBlankString(String string) {
        if (string == null) {
            return false;
        }
        return !string.isBlank();
    }

    public static boolean checkPassword(String string) {
        if (!isNotBlankString(string)) {
            return false;
        } else {
            return lengthCheck(string, 6);
        }
    }

    public static boolean checkName(String string) {
        if (isNotBlankString(string)) {
            return lengthCheck(string, 20);
        } else {
            return false;
        }
    }

    public static boolean checkDate(String date) {
        if (isNotBlankString(date)) {

        } else {
            return false;
        }
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        try {
            Date javaDate = sdfrmt.parse(date);
        } catch (ParseException e) {
            System.out.println("Klasser.Validering.checkDate(): " + e);
            return false;
        }
        return true;
    }

    public static boolean notNegative(String i) {
        try {
            Integer.parseInt(i);
        } catch (NumberFormatException e) {
            return false;
        }
        return Integer.parseInt(i) >= 0;
    }

    public static boolean checkTelefon(String telefon) {
        if (isNotBlankString(telefon) && lengthCheck(telefon, 25)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPlatsBenamning(String string) {
        if (isNotBlankString(string) && lengthCheck(string, 10)) {
            return true;
        } else {
            return false;
        }
    }

}
