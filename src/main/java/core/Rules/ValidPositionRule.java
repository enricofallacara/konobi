package core.Rules;

import core.Entities.Supervisor;
import java.util.stream.Stream;

public class ValidPositionRule implements Rule {
    private Stream<Rule> positionRules;

    public ValidPositionRule() { positionRules = ValidPositionRulesFactory.create(); }

    @Override
    public boolean isValid(Supervisor supervisor) {
        return positionRules.allMatch(x -> x.isValid(supervisor));
    }
}
