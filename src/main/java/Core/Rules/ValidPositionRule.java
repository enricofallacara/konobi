package Core.Rules;

import Core.Entities.StatusSupervisor;
import java.util.stream.Stream;

public class ValidPositionRule implements Rule {
    private final Stream<Rule> positionRules;

    public ValidPositionRule() { positionRules = ValidPositionRulesFactory.create(); }

    @Override
    public boolean isValid(StatusSupervisor supervisor) {
        return positionRules.allMatch(x -> x.isValid(supervisor));
    }
}
