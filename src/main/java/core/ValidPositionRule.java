package core;

import java.util.ArrayList;
import java.util.Arrays;

// TODO: forse le regole potrebbero diventare funzioni di questa classe, non viola l'OCP,
// si evita di tenere un array di regole che a sua volta lo viola
public class ValidPositionRule implements Rule{
    private ArrayList<Rule> positionRules;

    public ValidPositionRule() {
        positionRules = new ArrayList<>(Arrays.asList(new EmptyRule(), new CrosscutRule(), new WeakRule()));
    }

    @Override
    public boolean isValid(Supervisor supervisor) {
        // TODO: spostare questo if in una nuova regola
        if(!supervisor.getBoard().isOnBoard(supervisor.getCurrentPoint()))
            return false;
        return positionRules.stream().allMatch(x -> x.isValid(supervisor));
    }
}
