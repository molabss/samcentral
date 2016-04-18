package br.com.constran.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by moises_santana on 16/03/2015.
 *
 * Esta classe eh uma compacta representacao da classe br.com.constran.mobile.view.util.Util;
 * Portanto aqui foram adicionados apenas atributos e metodos realmente usados na aplicacao WEB
*
 */
public class Util {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public static String toCamelCase(String text) {
        if (text != null && !text.isEmpty()) {
            text = text.toLowerCase();
            String primeira = text.substring(0, 1);
            return primeira.toUpperCase() + text.substring(1);
        }
        return "";
    }

    public static Date toDateFormat(String dateStr) {
        try {
            return dateStr != null && !dateStr.isEmpty() ? dateFormat.parse(dateStr) : new Date();
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date toHourFormat(String hourStr) {
        try {
            return hourStr != null && !hourStr.isEmpty() ? hourFormat.parse(hourStr) : null;
        } catch (ParseException e) {
            return null;
        }
    }
}
