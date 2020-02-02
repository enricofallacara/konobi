package user_interface;

import core.Color;
import core.Player;

import java.util.HashMap;
import java.util.Map;

public class ConsoleCellRepresentation {

    private static final Map<Color, String> cellColorMap = new HashMap<>() {{
        put(core.Color.black, "[\u25A0]");
        put(Color.white, "[\u25A1]");
        put(null, "[ ]");
    }};

    public static String getRepresentation(Color color) {
        return cellColorMap.get(color);
    }

}
