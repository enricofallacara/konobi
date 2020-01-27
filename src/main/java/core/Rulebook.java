package core;

public class Rulebook {
    private static PositionRule[] positionRules = new PositionRule[]{new WeakRule(), new CrosscutRule(), new EmptyRule()};

    public static PositionRule[] getPositionRules(){
        return positionRules;
    }

}
