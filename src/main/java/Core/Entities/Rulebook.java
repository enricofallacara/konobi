package Core.Entities;

import Core.Rules.Rule;

import java.util.function.Supplier;

public class Rulebook {
    public static boolean queryRule(StatusSupervisor supervisor, Supplier<Rule> ruleSupplier) {
        return ruleSupplier.get().isValid(supervisor);
    }

}
