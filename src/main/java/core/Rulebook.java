package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Rulebook {
    private static ArrayList<Rule> positionRules = new ArrayList<>(Arrays.asList(new WeakRule(),
            new CrosscutRule(), new EmptyRule()));
    private static Rule endGameRule = new EndGameRule();
    private static Rule pieRule = new PieRule();
    private static Rule passRule = new PassRule();

    /*public static ArrayList<Rule> getPositionRules(){
        return positionRules;
    }*/

    public static boolean queryValidPosition(Supervisor supervisor){
        return positionRules.stream().allMatch(x -> x.isValid(supervisor));
    }

    public static boolean queryEndGameRule(Supervisor supervisor) {
        return endGameRule.isValid(supervisor);
    }

    public static boolean queryPieRule(Supervisor supervisor) {
        return pieRule.isValid(supervisor);
    }

    public static boolean queryPassRule(Supervisor supervisor){ return passRule.isValid(supervisor);}


}
