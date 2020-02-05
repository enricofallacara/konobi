package core.Entities;
// TODO: forse ha senso aggiungere la costante EMPTY, che rende piu esplicito per una Cell l'essere
//  senza un colore; dall'altra parte, non ha senso per un Player essere EMPTY
public enum Color { black, white;

public Color getOppositeColor() {
    return (this == Color.black) ? Color.white : Color.black;
}}


