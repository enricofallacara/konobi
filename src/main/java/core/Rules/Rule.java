package core.Rules;

import core.Entities.StatusSupervisor;


public interface Rule {

    boolean isValid(StatusSupervisor supervisor);

}
