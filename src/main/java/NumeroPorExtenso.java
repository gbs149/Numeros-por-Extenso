import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static constantes.DicionariosNumeros.*;

public class NumeroPorExtenso {
    private static final String E = " e ";
    private static final String ESPACO = " ";
    private static final String EM_BRANCO = "";
    private static final String CENTO = "cento";
    private static final String MIL_POR_EXTENSO = "mil";
    private static final String MILHAO_POR_EXTENSO = "milhão";
    private static final String MILHOES_POR_EXTENSO = "milhões";

    private static final BigDecimal VINTE = BigDecimal.valueOf(20);
    private static final BigDecimal CEM = BigDecimal.valueOf(100);
    private static final BigDecimal MIL = BigDecimal.valueOf(1000);
    private static final BigDecimal MILHAO = BigDecimal.valueOf(1000000);
    private static final BigDecimal DEZ = BigDecimal.valueOf(10);

    public static String numeroPorExtensoMasculino(BigDecimal numero) {
        return numeroPorExtenso(numero, true);
    }

    public static String numeroPorExtensoFeminino(BigDecimal numero) {
        return numeroPorExtenso(numero, false);
    }

    public static String numeroPorExtensoMasculino(int numero) {
        return numeroPorExtenso(BigDecimal.valueOf(numero), true);
    }

    public static String numeroPorExtensoFeminino(int numero) {
        return numeroPorExtenso(BigDecimal.valueOf(numero), false);
    }

    private static String numeroPorExtenso(BigDecimal numero, boolean masculino) {
        return converteMilhoes(numero, masculino);
    }

    private static String converteMilhoes(BigDecimal numero, boolean masculino) {
        if (numero.compareTo(MILHAO) < 0) {
            return converteMilhares(numero, masculino);
        } else {
            BigDecimal milhao = casasMilhoes(numero);
            BigDecimal centenaDeMilhar = valorCentenasDeMilhar(numero);
            return converteMilhares(milhao, true) +
                    ESPACO +
                    (milhao.compareTo(BigDecimal.ONE) > 0 ? MILHOES_POR_EXTENSO : MILHAO_POR_EXTENSO) +
                    (isZero(centenaDeMilhar) ? "" :
                            separadorCentenaDeMilhar(centenaDeMilhar) + converteMilhares(centenaDeMilhar, masculino));
        }
    }

    private static String converteMilhares(BigDecimal numero, boolean masculino) {
        if (numero.compareTo(MIL) < 0) {
            return converteCentenas(numero, masculino);
        } else {
            BigDecimal milhar = casasMilhar(numero);
            BigDecimal centenas = valorCentenas(numero);
            return (isUm(milhar) ? EM_BRANCO : converteCentenas(milhar, masculino) + ESPACO) +
                    MIL_POR_EXTENSO +
                    (isZero(centenas)
                            ? EM_BRANCO
                            : separadorCentena(centenas) + converteCentenas(centenas, masculino));
        }
    }

    private static String converteCentenas(BigDecimal numero, boolean masculino) {
        Map<Integer, String> dicionarioCentenas = masculino ? CENTENAS : CENTENAS_FEMININO;

        if (numero.compareTo(CEM) < 0) {
            return converteDezenas(numero, masculino);
        } else {
            BigDecimal digitoCentena = digitoCentena(numero);
            boolean dezenaZero = isZero(valorDezenas(numero));
            boolean cento = (!dezenaZero) && isUm(digitoCentena);
            return (cento ? CENTO : dicionarioCentenas.get(digitoCentena.intValue())) +
                    (dezenaZero ? EM_BRANCO : E + converteDezenas(valorDezenas(numero), masculino));
        }
    }

    private static String converteDezenas(BigDecimal numero, boolean masculino) {
        Map<Integer, String> dicionarioUnidades = masculino ? UNIDADES : UNIDADES_FEMININO;

        if (numero.compareTo(VINTE) < 0) {
            return dicionarioUnidades.get(numero.intValue());
        } else {
            return DEZENAS.get(digitoDezena(numero).intValue()) +
                    (isZero(digitoUnidade(numero))
                            ? EM_BRANCO
                            : (E + dicionarioUnidades.get(digitoUnidade(numero).intValue())));
        }
    }

    private static String separadorCentena(BigDecimal centena) {
        return isZero(valorDezenas(centena)) || isZero(digitoCentena(centena)) ? E : ESPACO;
    }

    private static String separadorCentenaDeMilhar(BigDecimal centenaDeMilhar) {
        return isZero(valorDezenasDeMilhar(centenaDeMilhar))
                || (isZero(digitoCentena(casasMilhar(centenaDeMilhar))) && isZero(valorCentenas(centenaDeMilhar)))
                || (isZero(casasMilhar(centenaDeMilhar)) && !isZero(valorCentenas(centenaDeMilhar)))
                ? E
                : ESPACO;
    }

    static BigDecimal digitoUnidade(BigDecimal numero) {
        return numero.subtract(zerarDigitos(1, numero));
    }

    static BigDecimal digitoDezena(BigDecimal numero) {
        return digitoUnidade(numero.divide(DEZ, RoundingMode.DOWN));
    }

    static BigDecimal digitoCentena(BigDecimal numero) {
        return digitoUnidade(numero.divide(CEM, RoundingMode.DOWN));
    }

    static BigDecimal casasMilhar(BigDecimal numero) {
        return zerarDigitos(3, numero.subtract(zerarDigitos(6, numero))).divide(MIL, RoundingMode.DOWN);
    }

    static BigDecimal casasMilhoes(BigDecimal numero) {
        return numero.divide(MILHAO, RoundingMode.DOWN);
    }

    static BigDecimal valorDezenas(BigDecimal numero) {
        return numero.subtract(zerarDigitos(2, numero));
    }

    static BigDecimal valorCentenas(BigDecimal numero) {
        return numero.subtract(zerarDigitos(3, numero));
    }

    static BigDecimal valorDezenasDeMilhar(BigDecimal numero) {
        return numero.subtract(zerarDigitos(5, numero));
    }

    static BigDecimal valorCentenasDeMilhar(BigDecimal numero) {
        return numero.subtract(zerarDigitos(6, numero));
    }

    static BigDecimal zerarDigitos(int digitos, BigDecimal numero) {
        BigDecimal fator = BigDecimal.TEN.pow(digitos);
        return numero.divide(fator, RoundingMode.DOWN).multiply(fator);
    }

    private static boolean isZero(BigDecimal numero) {
        return numero.compareTo(BigDecimal.ZERO) == 0;
    }

    private static boolean isUm(BigDecimal numero) {
        return numero.compareTo(BigDecimal.ONE) == 0;
    }
}
