package core.Entities;

public enum Colour { black, white;

public Colour getOppositeColor() {
    return (this == Colour.black) ? Colour.white : Colour.black;
}}


