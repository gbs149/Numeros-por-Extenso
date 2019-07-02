import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NumeroPorExtensoTest {

    static Stream<Arguments> dadoEsperadoProvider() {
        return Stream.of(
                arguments(BigDecimal.ZERO, "zero"),
                arguments(BigDecimal.ONE, "um"),
                arguments(BigDecimal.valueOf(10.21), "dez e vinte e um centesimos")
        );
    }

    @ParameterizedTest
    @MethodSource("dadoEsperadoProvider")
    @DisplayName("Deve converter números tipo BigDecimal para representação textual")
    void numeroPorExtenso_dadoUmNumeroBigDecimal_deveConverterParaTexto(BigDecimal numero, String resultado) {
        assertEquals(resultado, NumeroPorExtenso.numeroPorExtensoMasculino(numero));
    }


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
    void numeroPorExtenso_dadoUmNumero_deveConverterParaTexto(Integer numero, String resultado) {
        assertEquals(resultado, NumeroPorExtenso.numeroPorExtensoMasculino(numero));
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
    void numeroPorExtenso_dadoUmNumero_deveConverterParaTextoFeminino(Integer numero, String resultado) {
        assertEquals(resultado, NumeroPorExtenso.numeroPorExtensoFeminino(numero));
    }

    @Test
    @DisplayName("Deve retornar o valor da unidade")
    void numeroPorExtenso_dadoUmNumero_deveRetornarUnidade() {
        assertEquals(3, NumeroPorExtenso.digitoUnidade(BigDecimal.valueOf(123)));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 123",
            "0, 3"
    })
    @DisplayName("Deve retornar o valor do digito da dezena")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoDezena(int resultado, int numero) {
        assertEquals(resultado, NumeroPorExtenso.digitoDezena(BigDecimal.valueOf(numero)));
    }

    @Test
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoCentena() {
        assertEquals(2, NumeroPorExtenso.digitoCentena(BigDecimal.valueOf(1234)));
    }

    @ParameterizedTest
    @CsvSource({
            "15, 15235",
            "5, 5235",
            "159, 159235",
            "159, 7159235"
    })
    @DisplayName("Deve retornar o valor dos milhares")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoDeMilhar(int resultado, BigDecimal numero) {
        assertEquals(resultado, NumeroPorExtenso.digitosMilhar(numero));
    }

    @ParameterizedTest
    @CsvSource({
            "15, 15999235",
            "5, 5235999",
            "159, 159235999"
    })
    @DisplayName("Deve retornar o valor ate os digitos de milhoes")
    void numeroPorExtenso_dadoUmNumero_deveRetornarMilhao(int resultado, int numero) {
        assertEquals(resultado, NumeroPorExtenso.digitosMilhoes(BigDecimal.valueOf(numero)));
    }

    @ParameterizedTest
    @CsvSource({
            "235999, 15235999",
            "0, 5000000",
            "1, 159000001"
    })
    @DisplayName("Deve retornar o valor ate as centenas de milhar")
    void numeroPorExtenso_dadoUmNumero_deveRetornarCentenaDeMilhar(int resultado, int numero) {
        assertEquals(resultado, NumeroPorExtenso.centenasDeMilhar(BigDecimal.valueOf(numero)));
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
    void numeroPorExtenso_dadoUmNumero_deveRetornarDezenaDeMilhar(int resultado, BigDecimal numero) {
        assertEquals(resultado, NumeroPorExtenso.dezenasDeMilhar(numero));
    }

    @Test
    @DisplayName("Deve retornar o valor ate as dezenas")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDezena() {
        assertEquals(34, NumeroPorExtenso.dezenas(BigDecimal.valueOf(1234)));
    }

    @Test
    @DisplayName("Deve retornar o valor ate as centenas")
    void numeroPorExtenso_dadoUmNumero_deveRetornarCentena() {
        assertEquals(BigDecimal.valueOf(234), NumeroPorExtenso.centenas(BigDecimal.valueOf(5234)));
    }

    @Test
    void testeTruncar() {
        assertEquals(0, BigDecimal.valueOf(1200).compareTo(NumeroPorExtenso.truncar(2 , BigDecimal.valueOf(1234))));
    }


    @Disabled
    @Test
    void teste() {
        assertEquals(2, BigDecimal.valueOf(123));
    }
}