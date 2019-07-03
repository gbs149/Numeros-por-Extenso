package conversao;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static util.BigDecimalUtil.isUm;
import static util.BigDecimalUtil.isZero;

public class MoedaPorExtenso {
    static String moedaPorExtenso(BigDecimal valor) {
        BigDecimal centavos = valorCentavos(valor);
        BigDecimal reais = valorReais(valor);
        return formataReais(reais) +
                separador(centavos, reais) +
                formataCentavos(centavos);
    }

    private static String separador(BigDecimal centavos, BigDecimal reais) {
        return isZero(centavos) || isZero(reais)? "" : " e ";
    }

    private static String formataReais(BigDecimal valor) {
        String reais = isUm(valor) ? "real" : "reais";
        return isZero(valor) ? "" :
                String.format("%s %s",
                BigDecimalPorExtenso.bigDecimalPorExtenso(valor, true),
                reais);
    }

    private static String formataCentavos(BigDecimal valor) {
        String centavos = isUm(valor) ? "centavo" : "centavos";
        return isZero(valor) ? "" :
                String.format("%s %s",
                        BigDecimalPorExtenso.bigDecimalPorExtenso(valor.multiply(BigDecimal.valueOf(100)), true),
                        centavos);
    }

    private static BigDecimal valorReais(BigDecimal valor) {
        return valor.setScale(0, RoundingMode.DOWN);
    }

    private static BigDecimal valorCentavos(BigDecimal valor) {
        return valor.subtract(valorReais(valor));
    }
}
