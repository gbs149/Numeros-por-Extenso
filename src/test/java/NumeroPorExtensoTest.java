import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumeroPorExtensoTest {

    @ParameterizedTest
    @CsvSource({
            "0, zero",
            "1, um",
            "2, dois",
            "3, três",
            "10, dez",
            "19, dezenove",
            "20, vinte",
            "33, trinta e três",
            "200, duzentos",
            "100, cem",
            "897, oitocentos e noventa e sete",
            "145, cento e quarenta e cinco",
            "307, trezentos e sete",
            "1000, mil",
            "1042, mil e quarenta e dois",
            "1002, mil e dois",
            "2400, dois mil e quatrocentos",
            "1763, mil setecentos e sessenta e três",
            "9876, nove mil oitocentos e setenta e seis",
            "120000, cento e vinte mil",
            "999999, novecentos e noventa e nove mil novecentos e noventa e nove",
            "1000000, um milhão",
            "1200000, um milhão e duzentos mil",
            "1200022, um milhão duzentos mil e vinte e dois",
            "1025000, um milhão e vinte e cinco mil",
            "1025100, um milhão vinte e cinco mil e cem",
            "2000055, dois milhões e cinquenta e cinco",
            "999999999, novecentos e noventa e nove milhões novecentos e noventa e nove mil novecentos e noventa e nove"
    })
    @DisplayName("Deve converter números para representação textual")
    void numeroPorExtenso_dadoUmNumero_deveConverterParaTexto(BigDecimal numero, String resultado) {
        assertAll(() -> {
            assertEquals(resultado, NumeroPorExtenso.numeroPorExtensoMasculino(numero));
            assertEquals(resultado, NumeroPorExtenso.numeroPorExtensoMasculino(numero.intValue()));
        });
    }

    @ParameterizedTest
    @CsvSource({
            "1, uma",
            "2, duas",
            "32, trinta e duas",
            "200, duzentas",
            "301, trezentas e uma",
            "897, oitocentas e noventa e sete",
            "1002, mil e duas",
            "1042, mil e quarenta e duas",
            "2400, duas mil e quatrocentas",
            "1761, mil setecentas e sessenta e uma",
            "991992, novecentas e noventa e uma mil novecentas e noventa e duas",
            "1200000, um milhão e duzentas mil",
            "1200022, um milhão duzentas mil e vinte e duas",
            "2000052, dois milhões e cinquenta e duas",
            "992991991, novecentos e noventa e dois milhões novecentas e noventa e uma mil novecentas e noventa e uma"
    })
    @DisplayName("Deve converter números para representação textual no feminino")
    void numeroPorExtenso_dadoUmNumero_deveConverterParaTextoFeminino(BigDecimal numero, String resultado) {
        assertAll(() -> {
            assertEquals(resultado, NumeroPorExtenso.numeroPorExtensoFeminino(numero));
            assertEquals(resultado, NumeroPorExtenso.numeroPorExtensoFeminino(numero.intValue()));
        });
    }

    @ParameterizedTest
    @CsvSource({
            "123, 3",
            "100, 0",
            "1, 1",
            "0, 0"
    })
    @DisplayName("Deve retornar o valor da unidade")
    void numeroPorExtenso_dadoUmNumero_deveRetornarUnidade(BigDecimal numero, BigDecimal resultado) {
        assertEquals(0, NumeroPorExtenso.digitoUnidade(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "123, 2",
            "3, 0"
    })
    @DisplayName("Deve retornar o valor do digito da dezena")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoDezena(BigDecimal numero, BigDecimal resultado) {
        assertEquals(0, NumeroPorExtenso.digitoDezena(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 2",
            "234, 2",
            "34, 0"
    })
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoCentena(BigDecimal numero, BigDecimal resultado) {
        assertEquals(0, NumeroPorExtenso.digitoCentena(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "15235, 15",
            "5235, 5",
            "159235, 159",
            "7159235, 159"
    })
    @DisplayName("Deve retornar o valor dos milhares")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoDeMilhar(BigDecimal numero, BigDecimal resultado) {
        assertEquals(resultado, NumeroPorExtenso.casasMilhar(numero));
    }

    @ParameterizedTest
    @CsvSource({
            "15999235, 15",
            "5235999, 5",
            "159235999, 159"
    })
    @DisplayName("Deve retornar o valor ate os digitos de milhoes")
    void numeroPorExtenso_dadoUmNumero_deveRetornarMilhao(BigDecimal numero, BigDecimal resultado) {
        assertEquals(0, NumeroPorExtenso.casasMilhoes(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "15235999, 235999",
            "5000000, 0",
            "159000001, 1"
    })
    @DisplayName("Deve retornar o valor ate as centenas de milhar")
    void numeroPorExtenso_dadoUmNumero_deveRetornarCentenaDeMilhar(BigDecimal numero, BigDecimal resultado) {
        assertEquals(0, NumeroPorExtenso.valorCentenasDeMilhar(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "35999, 15235999",
            "35999, 35999",
            "5999, 5999",
            "0, 5000000",
            "1, 159000001"
    })
    @DisplayName("Deve retornar o valor ate as dezenas de milhar")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDezenaDeMilhar(BigDecimal resultado, BigDecimal numero) {
        assertEquals(0, NumeroPorExtenso.valorDezenasDeMilhar(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 34",
            "34, 34",
            "3, 3"
    })
    @DisplayName("Deve retornar o valor ate as dezenas")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDezena(BigDecimal numero, BigDecimal resultado) {
        assertEquals(0, NumeroPorExtenso.valorDezenas(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "5234, 234",
            "234, 234",
            "34, 34",
            "4, 4"
    })
    @DisplayName("Deve retornar o valor ate as centenas")
    void numeroPorExtenso_dadoUmNumero_deveRetornarCentena(BigDecimal numero, BigDecimal resultado) {
        assertEquals(0, NumeroPorExtenso.valorCentenas(numero).compareTo(resultado));
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 2, 1200",
            "123456, 3, 123000"
    })
    void testeTruncar(int numero, int digitos, int resultado) {
        assertEquals(0, BigDecimal.valueOf(resultado).compareTo(NumeroPorExtenso.zerarDigitos(digitos, BigDecimal.valueOf(numero))));
    }


    @Disabled
    @Test
    void teste() {
        assertEquals(2, BigDecimal.valueOf(123));
    }
}