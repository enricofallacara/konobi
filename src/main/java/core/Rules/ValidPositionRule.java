package core.Rules;

import core.Entities.StatusSupervisor;
import java.util.stream.Stream;


public class ValidPositionRule implements Rule {

    private final Stream<PositionRule> positionRules;

    public ValidPositionRule() { positionRules = ValidPositionRulesFactory.create(); }

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return positionRules.allMatch(x -> x.isValidPosition(supervisor));
    }

}
