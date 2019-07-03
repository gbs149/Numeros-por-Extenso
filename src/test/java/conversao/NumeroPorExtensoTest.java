package conversao;

import org.junit.jupiter.api.DisplayName;
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
            "1.0, um",
            "2, dois",
            "3, três",
            "10, dez",
            "19, dezenove",
            "20, vinte",
            "33, trinta e três",
            "50.0, cinquenta",
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
    void numeroPorExtenso_dadoUmNumero_deveConverterParaTexto(BigDecimal numero, String esperado) {
        assertAll(() -> {
            assertEquals(esperado, NumeroPorExtenso.numeroPorExtensoMasculino(numero));
            assertEquals(esperado, NumeroPorExtenso.numeroPorExtensoMasculino(numero.intValue()));
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
    void numeroPorExtenso_dadoUmNumero_deveConverterParaTextoFeminino(BigDecimal numero, String esperado) {
        assertAll(() -> {
            assertEquals(esperado, NumeroPorExtenso.numeroPorExtensoFeminino(numero));
            assertEquals(esperado, NumeroPorExtenso.numeroPorExtensoFeminino(numero.intValue()));
        });
    }

    @ParameterizedTest
    @CsvSource({
            "1, um real",
            "2, dois reais",
            "1.5, um real e cinquenta centavos",
            "1.25, um real e vinte e cinco centavos",
            "2.25, dois reais e vinte e cinco centavos",
            "2.05, dois reais e cinco centavos",
            "0.05, cinco centavos"
    })
    @DisplayName("Deve converter números para representação textual no feminino")
    void numeroPorExtenso_dadoUmNumero_deveConverterParaValorEmMoeda(BigDecimal numero, String esperado) {
            assertEquals(esperado, NumeroPorExtenso.moedaPorExtenso(numero));
    }
}