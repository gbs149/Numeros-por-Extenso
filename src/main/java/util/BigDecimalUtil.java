package util;

import java.math.BigDecimal;

public class BigDecimalUtil {
    private BigDecimalUtil() {
    }

    public static boolean isZero(BigDecimal numero) {
        return numero.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isUm(BigDecimal numero) {
        return numero.compareTo(BigDecimal.ONE) == 0;
    }

}
