package conversao;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumeroPorExtenso {
    private NumeroPorExtenso() {
    }

    public static String inteiroPorExtensoMasculino(BigInteger numero) {
        return BigIntegerPorExtenso.bigIntegerPorExtenso(numero, true);
    }

    public static String inteiroPorExtensoMasculino(int numero) {
        return BigIntegerPorExtenso.bigIntegerPorExtenso(BigInteger.valueOf(numero), true);
    }

    public static String inteiroPorExtensoFeminino(BigInteger numero) {
        return BigIntegerPorExtenso.bigIntegerPorExtenso(numero, false);
    }

    public static String inteiroPorExtensoFeminino(int numero) {
        return BigIntegerPorExtenso.bigIntegerPorExtenso(BigInteger.valueOf(numero), false);
    }

    public static String moedaPorExtenso(BigDecimal valor) {
        return MoedaPorExtenso.moedaPorExtenso(valor);
    }
}
