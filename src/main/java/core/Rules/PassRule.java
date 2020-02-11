package core.Rules;

import core.Entities.Rulebook;
import core.Entities.StatusSupervisor;

import java.util.stream.StreamSupport;

public class PassRule implements Rule{
    @Override
    public boolean isValid(StatusSupervisor supervisor){
        return StreamSupport.stream(supervisor.getBoard().spliterator(), true).
                noneMatch(x -> {supervisor.setCurrentPoint(x.getCoordinates());
                          return Rulebook.queryRule(supervisor, ValidPositionRule::new);});
    }
}
