package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Rulebook {
    private static ArrayList<PositionRule> positionRules = new ArrayList<>(Arrays.asList(new WeakRule(),
            new CrosscutRule(), new EmptyRule()));

    public static ArrayList<PositionRule> getPositionRules(){
        return positionRules;
    }

}
