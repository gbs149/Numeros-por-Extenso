package conversao;

import java.math.BigInteger;
import java.util.Map;

import static constantes.DicionariosNumeros.*;
import static util.BigIntegerUtil.isUm;
import static util.BigIntegerUtil.isZero;


class BigIntegerPorExtenso {
    private static final String E = " e ";
    private static final String ESPACO = " ";
    private static final String EM_BRANCO = "";
    private static final String CENTO = "cento";
    private static final String MIL_POR_EXTENSO = "mil";
    private static final String MILHAO_POR_EXTENSO = "milhão";
    private static final String MILHOES_POR_EXTENSO = "milhões";

    private static final BigInteger VINTE = BigInteger.valueOf(20);
    private static final BigInteger CEM = BigInteger.valueOf(100);
    private static final BigInteger MIL = BigInteger.valueOf(1000);
    private static final BigInteger MILHAO = BigInteger.valueOf(1000000);
    private static final BigInteger DEZ = BigInteger.valueOf(10);

    private BigIntegerPorExtenso() {
    }

    static String bigIntegerPorExtenso(BigInteger numero, boolean masculino) {
        return converteMilhoes(numero, masculino);
    }

    private static String converteMilhoes(BigInteger numero, boolean masculino) {
        if (numero.compareTo(MILHAO) < 0) {
            return converteMilhares(numero, masculino);
        } else {
            BigInteger milhao = casasMilhoes(numero);
            BigInteger centenaDeMilhar = valorCentenasDeMilhar(numero);
            return String.format("%s %s%s",
                    converteMilhares(milhao, true),
                    (milhao.compareTo(BigInteger.ONE) > 0 ? MILHOES_POR_EXTENSO : MILHAO_POR_EXTENSO),
                    (isZero(centenaDeMilhar) ? "" :
                            separadorCentenaDeMilhar(centenaDeMilhar) + converteMilhares(centenaDeMilhar, masculino)));
        }
    }

    private static String converteMilhares(BigInteger numero, boolean masculino) {
        if (numero.compareTo(MIL) < 0) {
            return converteCentenas(numero, masculino);
        } else {
            BigInteger milhar = casasMilhar(numero);
            BigInteger centenas = valorCentenas(numero);
            return (isUm(milhar) ? EM_BRANCO : converteCentenas(milhar, masculino) + ESPACO) +
                    MIL_POR_EXTENSO +
                    (isZero(centenas)
                            ? EM_BRANCO
                            : separadorCentena(centenas) + converteCentenas(centenas, masculino));
        }
    }

    private static String converteCentenas(BigInteger numero, boolean masculino) {
        Map<Integer, String> dicionarioCentenas = masculino ? CENTENAS : CENTENAS_FEMININO;

        if (numero.compareTo(CEM) < 0) {
            return converteDezenas(numero, masculino);
        } else {
            BigInteger digitoCentena = digitoCentena(numero);
            boolean dezenaZero = isZero(valorDezenas(numero));
            boolean cento = (!dezenaZero) && isUm(digitoCentena);
            return (cento ? CENTO : dicionarioCentenas.get(digitoCentena.intValue())) +
                    (dezenaZero ? EM_BRANCO : E + converteDezenas(valorDezenas(numero), masculino));
        }
    }

    private static String converteDezenas(BigInteger numero, boolean masculino) {
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

    private static String separadorCentena(BigInteger centena) {
        return isZero(valorDezenas(centena)) || isZero(digitoCentena(centena)) ? E : ESPACO;
    }

    private static String separadorCentenaDeMilhar(BigInteger centenaDeMilhar) {
        return isZero(valorDezenasDeMilhar(centenaDeMilhar))
                || (isZero(digitoCentena(casasMilhar(centenaDeMilhar))) && isZero(valorCentenas(centenaDeMilhar)))
                || (isZero(casasMilhar(centenaDeMilhar)) && !isZero(valorCentenas(centenaDeMilhar)))
                ? E
                : ESPACO;
    }

    static BigInteger digitoUnidade(BigInteger numero) {
        return numero.subtract(zerarDigitos(1, numero));
    }

    static BigInteger digitoDezena(BigInteger numero) {
        return digitoUnidade(numero.divide(DEZ));
    }

    static BigInteger digitoCentena(BigInteger numero) {
        return digitoUnidade(numero.divide(CEM));
    }

    static BigInteger casasMilhar(BigInteger numero) {
        return zerarDigitos(3, numero.subtract(zerarDigitos(6, numero))).divide(MIL);
    }

    static BigInteger casasMilhoes(BigInteger numero) {
        return numero.divide(MILHAO);
    }

    static BigInteger valorDezenas(BigInteger numero) {
        return numero.subtract(zerarDigitos(2, numero));
    }

    static BigInteger valorCentenas(BigInteger numero) {
        return numero.subtract(zerarDigitos(3, numero));
    }

    static BigInteger valorDezenasDeMilhar(BigInteger numero) {
        return numero.subtract(zerarDigitos(5, numero));
    }

    static BigInteger valorCentenasDeMilhar(BigInteger numero) {
        return numero.subtract(zerarDigitos(6, numero));
    }

    static BigInteger zerarDigitos(int digitos, BigInteger numero) {
        BigInteger fator = BigInteger.TEN.pow(digitos);
        return numero.divide(fator).multiply(fator);
    }

}
