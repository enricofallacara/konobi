package core.Rules;

import core.Entities.Supervisor;
import java.util.stream.Stream;

public class ValidPositionRule implements Rule {
    private Stream<Rule> positionRules;

    public ValidPositionRule() { positionRules = ValidPositionRulesFactory.create(); }

    @Override
    public boolean isValid(Supervisor supervisor) {
        // TODO: spostare questo if in una nuova regola
        //  oppure eccezione in console al momento dell'input
        if(!supervisor.getBoard().isOnBoard(supervisor.getCurrentPoint()))
            return false;
        return positionRules.allMatch(x -> x.isValid(supervisor));
    }
}
