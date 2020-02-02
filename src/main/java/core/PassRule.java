package core;

import java.util.stream.StreamSupport;

public class PassRule implements Rule{
    @Override
    public boolean isValid(Supervisor supervisor){
        return StreamSupport.stream(supervisor.getBoard().spliterator(), false).
                noneMatch(x -> {supervisor.setCurrentPoint(x.getCoordinates());
                          return Rulebook.queryRule(supervisor, ValidPositionRule::new);});
    }
}
