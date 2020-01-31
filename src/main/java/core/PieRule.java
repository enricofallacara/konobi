package core;


public class PieRule implements Rule{
    public boolean isValid(Supervisor supervisor){return supervisor.getTurn() == 2;}
}
