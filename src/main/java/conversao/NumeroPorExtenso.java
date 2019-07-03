package conversao;

import java.math.BigDecimal;

public class NumeroPorExtenso {
    public static String numeroPorExtensoMasculino(BigDecimal numero) {
        return BigDecimalPorExtenso.bigDecimalPorExtenso(numero, true);
    }

    public static String numeroPorExtensoMasculino(int numero) {
        return BigDecimalPorExtenso.bigDecimalPorExtenso(BigDecimal.valueOf(numero), true);
    }

    public static String numeroPorExtensoFeminino(BigDecimal numero) {
        return BigDecimalPorExtenso.bigDecimalPorExtenso(numero, false);
    }

    public static String numeroPorExtensoFeminino(int numero) {
        return BigDecimalPorExtenso.bigDecimalPorExtenso(BigDecimal.valueOf(numero), false);
    }

    public static String moedaPorExtenso(BigDecimal valor) {
        return MoedaPorExtenso.moedaPorExtenso(valor);
    }
}
