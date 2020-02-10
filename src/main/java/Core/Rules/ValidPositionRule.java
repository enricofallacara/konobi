package Core.Rules;

import Core.Entities.Supervisor;
import java.util.stream.Stream;

public class ValidPositionRule implements Rule {
    private final Stream<Rule> positionRules;

    public ValidPositionRule() { positionRules = ValidPositionRulesFactory.create(); }

    @Override
    public boolean isValid(Supervisor supervisor) {
        return positionRules.allMatch(x -> x.isValid(supervisor));
    }
}
