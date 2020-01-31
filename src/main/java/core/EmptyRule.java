package core;

public class EmptyRule implements Rule {
    public boolean isValid(Supervisor supervisor) {
        return supervisor.getBoard().isOnBoard(supervisor.getCurrentPoint())
                && supervisor.getBoard().getCell(supervisor.getCurrentPoint()).getColor() == null;
    }
}

