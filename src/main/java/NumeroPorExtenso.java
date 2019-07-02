import org.omg.CORBA.INTERNAL;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static constantes.DicionariosNumeros.*;


public class NumeroPorExtenso {
    private static final String E = " e ";
    private static final String ESPACO = " ";
    private static final String BLANK = "";
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

    public static String numeroPorExtensoMasculino(int numero) {
        return numeroPorExtenso(BigDecimal.valueOf(numero), true);
    }

    public static String numeroPorExtensoFeminino(BigDecimal numero) {
        return numeroPorExtenso(numero, false);
    }

    public static String numeroPorExtensoFeminino(Integer numero) {
        return numeroPorExtenso(BigDecimal.valueOf(numero), false);
    }

    private static String numeroPorExtenso(BigDecimal numero, boolean masculino) {
        return converteMenosQueBilhao(numero, masculino);
    }

    private static String converteMenosQueBilhao(BigDecimal numero, boolean masculino) {
        if (numero.compareTo(MILHAO) < 0) {
            return converteMenosQueMilhao(numero, masculino);
        } else {
            BigDecimal milhao = digitosMilhoes(numero);
            BigDecimal centenaDeMilhar = centenasDeMilhar(numero);
            return converteMenosQueMilhao(milhao, true) +
                    ESPACO +
                    (milhao.compareTo(BigDecimal.ONE) > 0 ? MILHOES_POR_EXTENSO : MILHAO_POR_EXTENSO) +
                    formataCentenaDeMilhar(centenaDeMilhar, masculino);
        }
    }

    private static String converteMenosQueMilhao(BigDecimal numero, boolean masculino) {
        if (numero.compareTo(MIL) < 0) {
            return converteMenosQueMil(numero, masculino);
        } else {
            BigDecimal milhar = digitosMilhar(numero);
            BigDecimal centenas = centenas(numero);
            return (milhar.compareTo(BigDecimal.ONE) == 0 ? BLANK : converteMenosQueMil(milhar, masculino) + ESPACO) +
                    MIL_POR_EXTENSO +
                    formataCentena(centenas, masculino);
        }
    }

    private static String converteMenosQueMil(BigDecimal numero, boolean masculino) {
        Map<Integer, String> dicionarioCentenas = masculino ? CENTENAS : CENTENAS_FEMININO;

        if (numero.compareTo(CEM) < 0) {
            return converteMenosQueCem(numero, masculino);
        } else {
            BigDecimal digitoCentena = digitoCentena(numero);
            boolean dezenaZero = dezenas(numero).compareTo(BigDecimal.ZERO) == 0;
            boolean cento = (!dezenaZero) && digitoCentena.compareTo(BigDecimal.ONE) == 0;
            return (cento ? CENTO : dicionarioCentenas.get(digitoCentena.intValue())) +
                    (dezenaZero ? BLANK : E + converteMenosQueCem(dezenas(numero), masculino));
        }
    }

    private static String converteMenosQueCem(BigDecimal numero, boolean masculino) {
        Map<Integer, String> dicionarioUnidades = masculino ? UNIDADES : UNIDADES_FEMININO;

        if (numero.compareTo(VINTE) < 0) {
            return dicionarioUnidades.get(numero);
        } else {
            return DEZENAS.get(digitoDezena(numero)) +
                    (digitoUnidade(numero).compareTo(BigDecimal.ZERO) == 0
                            ? BLANK
                            : (E + dicionarioUnidades.get(digitoUnidade(numero).intValue())));
        }
    }

    private static String formataCentena(BigDecimal centena, boolean masculino) {
        BigDecimal dezena = dezenas(centena);
        String separador = (dezena.compareTo(BigDecimal.ZERO) == 0 || digitoCentena(centena).compareTo(BigDecimal.ZERO) == 0) ? E : ESPACO;
        return centena.compareTo(BigDecimal.ZERO) == 0
                ? BLANK
                : separador + converteMenosQueMil(centena, masculino);
    }

    private static String formataCentenaDeMilhar(BigDecimal centenaDeMilhar, boolean masculino) {
        BigDecimal dezenaDeMilhar = dezenasDeMilhar(centenaDeMilhar);
        String separador = ESPACO;
        if (dezenaDeMilhar.compareTo(BigDecimal.ZERO) == 0
                || (digitoCentena(digitosMilhar(centenaDeMilhar)).compareTo(BigDecimal.ZERO) == 0 && centenas(centenaDeMilhar).compareTo(BigDecimal.ZERO) == 0)
                || (digitosMilhar(centenaDeMilhar).compareTo(BigDecimal.ZERO) == 0 && centenas(centenaDeMilhar).compareTo(BigDecimal.ZERO) != 0)
        ) {
            separador = E;
        }
        return centenaDeMilhar.compareTo(BigDecimal.ZERO) == 0
                ? BLANK
                : separador + converteMenosQueMilhao(centenaDeMilhar, masculino);
    }

    static BigDecimal digitoUnidade(BigDecimal numero) {
        return numero.subtract(truncar(1, numero));
    }

    static BigDecimal digitoDezena(BigDecimal numero) {
        return digitoUnidade(numero.divide(DEZ));
    }

    static BigDecimal digitoCentena(BigDecimal numero) {
        return digitoUnidade(numero.divide(CEM));
    }

    static BigDecimal digitosMilhar(BigDecimal numero) {
        return truncar(3, numero.subtract(truncar(6, numero))).divide(MIL);
    }

    static BigDecimal digitosMilhoes(BigDecimal numero) {
        return numero.divide(MILHAO);
    }

    static BigDecimal dezenas(BigDecimal numero) {
        return numero.subtract(truncar(2, numero));
    }

    static BigDecimal centenas(BigDecimal numero) {
        return numero.subtract(truncar(3, numero));
    }

    static BigDecimal dezenasDeMilhar(BigDecimal numero) {
        return numero.subtract(truncar(5, numero));
    }

    static BigDecimal centenasDeMilhar(BigDecimal numero) {
        return numero.subtract(truncar(6, numero));
    }

    static BigDecimal truncar(int digitos, BigDecimal numero) {
        BigDecimal fator =  BigDecimal.TEN.pow(digitos);
        return numero.divide(fator).setScale(0, RoundingMode.DOWN).multiply(fator);
    }
}
