package constantes;

import java.util.HashMap;
import java.util.Map;

public class DicionariosNumeros {
    public static final Map<Integer, String> UNIDADES = new HashMap<>();
    public static final Map<Integer, String> DEZENAS = new HashMap<>();
    public static final Map<Integer, String> CENTENAS = new HashMap<>();
    public static final Map<Integer, String> UNIDADES_FEMININO;
    public static final Map<Integer, String> CENTENAS_FEMININO = new HashMap<>();

    static {
        UNIDADES.put(0, "zero");
        UNIDADES.put(1, "um");
        UNIDADES.put(2, "dois");
        UNIDADES.put(3, "três");
        UNIDADES.put(4, "quatro");
        UNIDADES.put(5, "cinco");
        UNIDADES.put(6, "seis");
        UNIDADES.put(7, "sete");
        UNIDADES.put(8, "oito");
        UNIDADES.put(9, "nove");
        UNIDADES.put(10, "dez");
        UNIDADES.put(11, "onze");
        UNIDADES.put(12, "doze");
        UNIDADES.put(13, "treze");
        UNIDADES.put(14, "quatorze");
        UNIDADES.put(15, "quinze");
        UNIDADES.put(16, "dezesseis");
        UNIDADES.put(17, "dezessete");
        UNIDADES.put(18, "dezoito");
        UNIDADES.put(19, "dezenove");

        UNIDADES_FEMININO = new HashMap<>(UNIDADES);
        UNIDADES_FEMININO.put(1, "uma");
        UNIDADES_FEMININO.put(2, "duas");

        DEZENAS.put(2, "vinte");
        DEZENAS.put(3, "trinta");
        DEZENAS.put(4, "quarenta");
        DEZENAS.put(5, "cinquenta");
        DEZENAS.put(6, "sessenta");
        DEZENAS.put(7, "setenta");
        DEZENAS.put(8, "oitenta");
        DEZENAS.put(9, "noventa");

        CENTENAS.put(1, "cem");
        CENTENAS.put(2, "duzentos");
        CENTENAS.put(3, "trezentos");
        CENTENAS.put(4, "quatrocentos");
        CENTENAS.put(5, "quinhentos");
        CENTENAS.put(6, "seiscentos");
        CENTENAS.put(7, "setecentos");
        CENTENAS.put(8, "oitocentos");
        CENTENAS.put(9, "novecentos");

        CENTENAS_FEMININO.put(1, "cem");
        CENTENAS_FEMININO.put(2, "duzentas");
        CENTENAS_FEMININO.put(3, "trezentas");
        CENTENAS_FEMININO.put(4, "quatrocentas");
        CENTENAS_FEMININO.put(5, "quinhentas");
        CENTENAS_FEMININO.put(6, "seiscentas");
        CENTENAS_FEMININO.put(7, "setecentas");
        CENTENAS_FEMININO.put(8, "oitocentas");
        CENTENAS_FEMININO.put(9, "novecentas");
    }

    private DicionariosNumeros() {
    }
}