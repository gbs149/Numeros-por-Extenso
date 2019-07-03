package conversao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static util.BigIntegerUtil.isUm;
import static util.BigIntegerUtil.isZero;

class MoedaPorExtenso {
    private MoedaPorExtenso() {
    }

    static String moedaPorExtenso(BigDecimal valor) {
        BigInteger centavos = valorCentavos(valor);
        BigInteger reais = valorReais(valor).toBigInteger();
        return formataReais(reais) +
                separador(centavos, reais) +
                formataCentavos(centavos);
    }

    private static String separador(BigInteger centavos, BigInteger reais) {
        return isZero(centavos) || isZero(reais) ? "" : " e ";
    }

    private static String formataReais(BigInteger valor) {
        String reais = isUm(valor) ? "real" : "reais";
        return isZero(valor) ? "" :
                String.format("%s %s",
                        BigIntegerPorExtenso.bigIntegerPorExtenso(valor, true),
                        reais);
    }

    private static String formataCentavos(BigInteger valor) {
        String centavos = isUm(valor) ? "centavo" : "centavos";
        return isZero(valor) ? "" :
                String.format("%s %s",
                        BigIntegerPorExtenso.bigIntegerPorExtenso(valor, true),
                        centavos);
    }

    private static BigDecimal valorReais(BigDecimal valor) {
        return valor.setScale(0, RoundingMode.DOWN);
    }

    private static BigInteger valorCentavos(BigDecimal valor) {
        return valor.subtract(valorReais(valor)).multiply(BigDecimal.valueOf(100)).toBigInteger();
    }
}
