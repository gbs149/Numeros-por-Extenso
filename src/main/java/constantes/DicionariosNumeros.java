package constantes;

import java.util.HashMap;
import java.util.Map;

public class DicionariosNumeros {
    public static final Map<Integer, String> UNIDADES = criaUnidades();
    public static final Map<Integer, String> DEZENAS = criaDezenas();
    public static final Map<Integer, String> CENTENAS = criaCentenas();
    public static final Map<Integer, String> UNIDADES_FEMININO = criaUnidadesFeminino();
    public static final Map<Integer, String> CENTENAS_FEMININO = criaCentenasFeminino();

    private static Map<Integer, String> criaCentenas() {
        Map<Integer, String> centenas = new HashMap<Integer, String>();
        centenas.put(1, "cem");
        centenas.put(2, "duzentos");
        centenas.put(3, "trezentos");
        centenas.put(4, "quatrocentos");
        centenas.put(5, "quinhentos");
        centenas.put(6, "seiscentos");
        centenas.put(7, "setecentos");
        centenas.put(8, "oitocentos");
        centenas.put(9, "novecentos");
        return centenas;
    }

    private static Map<Integer, String> criaCentenasFeminino() {
        Map<Integer, String> centenas = new HashMap<Integer, String>();
        centenas.put(1, "cem");
        centenas.put(2, "duzentas");
        centenas.put(3, "trezentas");
        centenas.put(4, "quatrocentas");
        centenas.put(5, "quinhentas");
        centenas.put(6, "seiscentas");
        centenas.put(7, "setecentas");
        centenas.put(8, "oitocentas");
        centenas.put(9, "novecentas");
        return centenas;
    }

    private static Map<Integer, String> criaDezenas() {
        Map<Integer, String> dezenas = new HashMap<Integer, String>();
        dezenas.put(2, "vinte");
        dezenas.put(3, "trinta");
        dezenas.put(4, "quarenta");
        dezenas.put(5, "cinquenta");
        dezenas.put(6, "sessenta");
        dezenas.put(7, "setenta");
        dezenas.put(8, "oitenta");
        dezenas.put(9, "noventa");
        return dezenas;
    }

    private static Map<Integer, String> criaUnidades() {
        Map<Integer, String> unidades = new HashMap<Integer, String>();
        unidades.put(0, "zero");
        unidades.put(1, "um");
        unidades.put(2, "dois");
        unidades.put(3, "três");
        unidades.put(4, "quatro");
        unidades.put(5, "cinco");
        unidades.put(6, "seis");
        unidades.put(7, "sete");
        unidades.put(8, "oito");
        unidades.put(9, "nove");
        unidades.put(10, "dez");
        unidades.put(11, "onze");
        unidades.put(12, "doze");
        unidades.put(13, "treze");
        unidades.put(14, "quatorze");
        unidades.put(15, "quinze");
        unidades.put(16, "dezesseis");
        unidades.put(17, "dezessete");
        unidades.put(18, "dezoito");
        unidades.put(19, "dezenove");
        return unidades;
    }

    private static Map<Integer, String> criaUnidadesFeminino() {
        Map<Integer, String> unidadesFeminino = new HashMap<>(UNIDADES);
        unidadesFeminino.put(1, "uma");
        unidadesFeminino.put(2, "duas");
        return unidadesFeminino;
    }

}

