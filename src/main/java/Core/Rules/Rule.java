package Core.Rules;

import Core.Entities.Supervisor;

public interface Rule {
    boolean isValid(Supervisor supervisor);
}
