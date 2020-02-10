package Core.Rules;

import java.util.stream.Stream;

public class ValidPositionRulesFactory {

    static Stream<Rule> create() {
        return Stream.of(new EmptyRule(), new CrosscutRule(), new WeakRule());
    }
}
