package br.com.constran.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class TextUtil {

	
	public static BigDecimal toENmoney(String value){
        Locale in_ID = new Locale("in","ID");
        DecimalFormat nf = (DecimalFormat)NumberFormat.getInstance(in_ID);
        nf.setParseBigDecimal(true);
        return (BigDecimal)nf.parse(value, new ParsePosition(0));
	}
}
