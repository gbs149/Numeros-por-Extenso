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

    private static final BigInteger DEZ = BigInteger.TEN;
    private static final BigInteger VINTE = BigInteger.valueOf(20);
    private static final BigInteger CEM = BigInteger.valueOf(100);
    private static final BigInteger MIL = BigInteger.valueOf(1000);
    private static final BigInteger CEM_MIL = BigInteger.valueOf(100000);
    private static final BigInteger MILHAO = BigInteger.valueOf(1000000);

    private BigIntegerPorExtenso() {
    }

    static String bigIntegerPorExtenso(BigInteger numero, boolean masculino) {
        return converteMilhoes(numero, masculino);
    }

    private static String converteMilhoes(BigInteger numero, boolean masculino) {
        if (numero.compareTo(MILHAO) < 0) {
            return converteMilhares(numero, masculino);
        } else {
            BigInteger milhoes = casasMilhoes(numero);
            BigInteger centenasDeMilhar = valorCentenasDeMilhar(numero);
            return String.format("%s %s%s",
                    converteMilhares(milhoes, true),
                    (milhoes.compareTo(BigInteger.ONE) > 0 ? MILHOES_POR_EXTENSO : MILHAO_POR_EXTENSO),
                    (isZero(centenasDeMilhar) ? "" :
                            separadorCentenaDeMilhar(centenasDeMilhar) + converteMilhares(centenasDeMilhar, masculino)));
        }
    }

    private static String converteMilhares(BigInteger numero, boolean masculino) {
        if (numero.compareTo(MIL) < 0) {
            return converteCentenas(numero, masculino);
        } else {
            BigInteger milhares = casasMilhares(numero);
            BigInteger centenas = valorCentenas(numero);
            return (isUm(milhares) ? EM_BRANCO : converteCentenas(milhares, masculino) + ESPACO) +
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
                    (isZero(unidade(numero))
                            ? EM_BRANCO
                            : (E + dicionarioUnidades.get(unidade(numero).intValue())));
        }
    }

    private static String separadorCentena(BigInteger centena) {
        return isZero(valorDezenas(centena)) || isZero(digitoCentena(centena)) ? E : ESPACO;
    }

    private static String separadorCentenaDeMilhar(BigInteger centenaDeMilhar) {
        return isZero(valorDezenasDeMilhar(centenaDeMilhar))
                || (isZero(digitoCentena(casasMilhares(centenaDeMilhar))) && isZero(valorCentenas(centenaDeMilhar)))
                || (isZero(casasMilhares(centenaDeMilhar)) && !isZero(valorCentenas(centenaDeMilhar)))
                ? E
                : ESPACO;
    }

    static BigInteger unidade(BigInteger numero) {
        return numero.mod(DEZ);
    }

    static BigInteger digitoDezena(BigInteger numero) {
        return unidade(numero.divide(DEZ));
    }

    static BigInteger digitoCentena(BigInteger numero) {
        return unidade(numero.divide(CEM));
    }

    static BigInteger casasMilhares(BigInteger numero) {
        return valorCentenasDeMilhar(numero).divide(MIL);
    }

    static BigInteger casasMilhoes(BigInteger numero) {
        return numero.divide(MILHAO);
    }

    static BigInteger valorDezenas(BigInteger numero) {
        return numero.mod(CEM);
    }

    static BigInteger valorCentenas(BigInteger numero) {
        return numero.mod(MIL);
    }

    static BigInteger valorDezenasDeMilhar(BigInteger numero) {
        return numero.mod(CEM_MIL);
    }

    static BigInteger valorCentenasDeMilhar(BigInteger numero) {
        return numero.mod(MILHAO);
    }
}
