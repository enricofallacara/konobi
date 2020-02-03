package core;

public enum Color { black, white;

public Color getOppositeColor() {
    return (this == Color.black) ? Color.white : Color.black;
}}


