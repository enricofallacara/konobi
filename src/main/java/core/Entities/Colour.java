package core.Entities;


public enum Colour {

    BLACK, WHITE;

    public Colour getOppositeColor() {
        return (this == Colour.BLACK) ? Colour.WHITE : Colour.BLACK;
    }

}


