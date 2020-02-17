package UI.Console;

import core.Entities.Colour;

import java.util.HashMap;
import java.util.Map;


public class ConsoleCellRepresentation {

    private static final Map<Colour, String> cellColorMap = new HashMap<>() {{
        put(Colour.black, "[\u25A0]");
        put(Colour.white, "[\u25A1]");
        put(null, "[ ]");
    }};

    public static String getRepresentation(Colour colour) {
        return cellColorMap.get(colour);
    }

}
