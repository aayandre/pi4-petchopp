package com.senac.petchopp.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Auxiliares {

	public static Calendar UtilDateToCalendar(Date data) {
		Calendar dataCal = Calendar.getInstance();
		dataCal.setTime(data);
		return dataCal;
	}

	public static Date InputDateToUtilDate(String inputDate) throws ParseException, Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date resultado = format.parse(inputDate);
			return resultado;
		} catch (ParseException e) {
			throw new ParseException("Erro ao formatar data de input para util date.", 0);
		}

	}

	public static java.util.Date SqlDateToUtilDate(java.sql.Date sqlDate) {
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
		return utilDate;
	}
	
	public static String formatLocalDateTimeToStringPadraoBrasil(LocalDateTime data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formatDateTime = data.format(formatter);
		return formatDateTime;
	}
public static String formatLocalDateTimeToStringPadraoHoraMinSec(LocalDateTime data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formatDateTime = data.format(formatter);
		return formatDateTime;
	}
	
}
