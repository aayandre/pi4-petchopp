package com.senac.petchopp.auxiliares;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AuxiliaresDAO {

    public static String ligaVetorAND(String[] str) {
        String concat = "";
        for (String string : str) {
            if (concat.equals("")) {
                if (string != null) {
                    concat += string;
                }
            } else {
                if (string != null) {
                    concat += " AND " + string;
                }
            }
        }
        return concat;
    }
    
    public static String montaIN(int[] array) {
        String concat = "";
        for (int pos : array) {
            if ("".equals(concat)){
                if (pos != 0) {
                    concat += "(" + pos;
                }
            } else {
                if (pos != 0) {
                    concat += ", " + pos;
                }
            }
        }
        return concat + ")";
    }

    public static String filtraRangeDate(String campo, Date dataInicial, Date dataFinal) {
        if (dataInicial != null || dataFinal != null) {
            Date d = Calendar.getInstance().getTime();
            if (dataInicial == null) {
                return campo +" BETWEEN '1899/01/01 00:00:00' AND '" + new java.sql.Date(dataFinal.getTime()) + " 23:59:59'";
            } else if (dataFinal == null) {
                return campo +" BETWEEN '" + new java.sql.Date(dataInicial.getTime()) + " 00:00:00' AND '" + new java.sql.Date(d.getTime()) + " 23:59:59'";
            }else{
                return campo + " BETWEEN '" + new java.sql.Date(dataInicial.getTime()) + " 00:00:00' AND '" + new java.sql.Date(dataFinal.getTime()) + " 23:59:59'";
            }
        }
        return "";
    }
}
