package core.Entities;

import core.Rules.Rule;

import java.util.function.Supplier;

public class Rulebook {
    public static boolean queryRule(Supervisor supervisor, Supplier<Rule> ruleSupplier) {
        return ruleSupplier.get().isValid(supervisor);
    }


}
