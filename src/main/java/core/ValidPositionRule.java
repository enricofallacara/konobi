package core;

import java.awt.*;
import java.util.ArrayList;


public class ValidPositionRule {
    public static boolean query(ArrayList<PositionRule> rules, Point point, Board board, Player player) {

        return rules.stream().allMatch(x -> x.isValid(point, board, player));

    }
}


