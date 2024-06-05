package br.com.borsoitech.pdv.layout.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormatarUtil {

    public static int arredondaParaCima(double valor) {
        return (int) Math.ceil(valor);
    }

    private static final NumberFormat numberFormatBR = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

    public static String valorParaBR(double valor) {
        return numberFormatBR.format(valor);
    }

    public static Double valorParaDouble(String valor) {
        try {
            return numberFormatBR.parse(valor).doubleValue();
        } catch (ParseException ex) {
            return null;
        }
    }
}
