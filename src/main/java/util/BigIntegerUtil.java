package util;

import java.math.BigInteger;

public class BigIntegerUtil {
    private BigIntegerUtil() {
    }

    public static boolean isZero(BigInteger numero) {
        return numero.compareTo(BigInteger.ZERO) == 0;
    }

    public static boolean isUm(BigInteger numero) {
        return numero.compareTo(BigInteger.ONE) == 0;
    }

}
