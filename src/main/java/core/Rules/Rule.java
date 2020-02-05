package core.Rules;

import core.Entities.Supervisor;

public interface Rule {
    boolean isValid(Supervisor supervisor);
}
