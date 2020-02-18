package core.Rules;

import core.Entities.StatusSupervisor;

public interface PositionRule {

    public boolean isValidPosition(StatusSupervisor supervisor);
}
