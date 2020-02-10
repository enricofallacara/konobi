package Core.Rules;

import Core.Entities.Rulebook;
import Core.Entities.StatusSupervisor;

import java.util.stream.StreamSupport;

public class PassRule implements Rule{
    @Override
    public boolean isValid(StatusSupervisor supervisor){
        return StreamSupport.stream(supervisor.getBoard().spliterator(), false).
                noneMatch(x -> {supervisor.setCurrentPoint(x.getCoordinates());
                          return Rulebook.queryRule(supervisor, ValidPositionRule::new);});
    }
}
