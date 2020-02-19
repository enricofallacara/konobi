package core.Rules;

import java.util.stream.Stream;


public class ValidPositionRulesFactory {

    static Stream<PositionRule> create() {
        return Stream.of(new EmptyRule(), new CrosscutRule(), new WeakRule());
    }

}
