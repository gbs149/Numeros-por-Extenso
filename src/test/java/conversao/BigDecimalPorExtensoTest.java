package conversao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigDecimalPorExtensoTest {

    @ParameterizedTest
    @CsvSource({
            "123, 3",
            "100, 0",
            "1, 1",
            "0, 0"
    })
    @DisplayName("Deve retornar o valor da unidade")
    void numeroPorExtenso_dadoUmNumero_deveRetornarUnidade(BigDecimal numero, BigDecimal esperado) {
        assertEquals(0, BigDecimalPorExtenso.digitoUnidade(numero).compareTo(esperado));
    }

    @ParameterizedTest
    @CsvSource({
            "123, 2",
            "50.0, 5",
            "3, 0"
    })
    @DisplayName("Deve retornar o valor do digito da dezena")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoDezena(BigDecimal numero, BigDecimal esperado) {
        assertEquals(0, BigDecimalPorExtenso.digitoDezena(numero.setScale(0, RoundingMode.DOWN)).compareTo(esperado));
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 2",
            "234, 2",
            "34, 0"
    })
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoCentena(BigDecimal numero, BigDecimal esperado) {
        assertEquals(0, BigDecimalPorExtenso.digitoCentena(numero).compareTo(esperado));
    }

    @ParameterizedTest
    @CsvSource({
            "15235, 15",
            "5235, 5",
            "159235, 159",
            "7159235, 159"
    })
    @DisplayName("Deve retornar o valor dos milhares")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDigitoDeMilhar(BigDecimal numero, BigDecimal esperado) {
        assertEquals(esperado, BigDecimalPorExtenso.casasMilhar(numero));
    }

    @ParameterizedTest
    @CsvSource({
            "15999235, 15",
            "5235999, 5",
            "159235999, 159"
    })
    @DisplayName("Deve retornar o valor ate os digitos de milhoes")
    void numeroPorExtenso_dadoUmNumero_deveRetornarMilhao(BigDecimal numero, BigDecimal esperado) {
        assertEquals(0, BigDecimalPorExtenso.casasMilhoes(numero).compareTo(esperado));
    }

    @ParameterizedTest
    @CsvSource({
            "15235999, 235999",
            "5000000, 0",
            "159000001, 1"
    })
    @DisplayName("Deve retornar o valor ate as centenas de milhar")
    void numeroPorExtenso_dadoUmNumero_deveRetornarCentenaDeMilhar(BigDecimal numero, BigDecimal esperado) {
        assertEquals(0, BigDecimalPorExtenso.valorCentenasDeMilhar(numero).compareTo(esperado));
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
    void numeroPorExtenso_dadoUmNumero_deveRetornarDezenaDeMilhar(BigDecimal esperado, BigDecimal numero) {
        assertEquals(0, BigDecimalPorExtenso.valorDezenasDeMilhar(numero).compareTo(esperado));
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 34",
            "34, 34",
            "3, 3"
    })
    @DisplayName("Deve retornar o valor ate as dezenas")
    void numeroPorExtenso_dadoUmNumero_deveRetornarDezena(BigDecimal numero, BigDecimal esperado) {
        assertEquals(0, BigDecimalPorExtenso.valorDezenas(numero).compareTo(esperado));
    }

    @ParameterizedTest
    @CsvSource({
            "5234, 234",
            "234, 234",
            "34, 34",
            "4, 4"
    })
    @DisplayName("Deve retornar o valor ate as centenas")
    void numeroPorExtenso_dadoUmNumero_deveRetornarCentena(BigDecimal numero, BigDecimal esperado) {
        assertEquals(0, BigDecimalPorExtenso.valorCentenas(numero).compareTo(esperado));
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 2, 1200",
            "123456, 3, 123000"
    })
    void testeTruncar(int numero, int digitos, int esperado) {
        assertEquals(0, BigDecimal.valueOf(esperado).compareTo(BigDecimalPorExtenso.zerarDigitos(digitos, BigDecimal.valueOf(numero))));
    }

}