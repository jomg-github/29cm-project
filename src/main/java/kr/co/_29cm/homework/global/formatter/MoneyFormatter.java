package kr.co._29cm.homework.global.formatter;

import java.text.DecimalFormat;

public class MoneyFormatter {
    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    public static String won(Long digit) {
        return formatter.format(digit) + "원";
    }
}
