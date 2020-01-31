package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Rulebook {
    private static final ArrayList<Rule> positionRules = new ArrayList<>(Arrays.asList(new WeakRule(),
            new CrosscutRule(), new EmptyRule()));

    private static final Rule endGameRule = new EndGameRule();
    private static final Rule pieRule = new PieRule();
    private static final Rule passRule = new PassRule();

    public static boolean queryValidPosition(Supervisor supervisor){
        if(!supervisor.getBoard().isOnBoard(supervisor.getCurrentPoint()))
            return false;
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
