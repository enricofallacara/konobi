package Core.Rules;

import Core.Entities.StatusSupervisor;

public interface Rule {
    boolean isValid(StatusSupervisor supervisor);
}
