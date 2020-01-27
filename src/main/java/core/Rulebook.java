package core;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Rulebook {
    private static ArrayList<PositionRule> positionRules = new ArrayList<>(Arrays.asList(new WeakRule(),
            new CrosscutRule(), new EmptyRule()));

    public static boolean queryValidPosition(Point point, Board board, Player player){
        return getPositionRules().stream().allMatch(x -> x.isValid(point, board, player));
    }

    public static ArrayList<PositionRule> getPositionRules(){
        return positionRules;
    }

}
