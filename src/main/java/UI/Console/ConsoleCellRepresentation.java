package UI.Console;

import core.Entities.Colour;

import java.util.HashMap;
import java.util.Map;


public class ConsoleCellRepresentation {

    private static final Map<Colour, String> cellColourMap = new HashMap<>() {{
        put(Colour.BLACK, "[\u25A0]");
        put(Colour.WHITE, "[\u25A1]");
        put(null, "[ ]");
    }};

    public static String getRepresentation(Colour colour) {
        return cellColourMap.get(colour);
    }

}
