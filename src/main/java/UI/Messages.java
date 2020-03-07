package UI;


public class Messages {

    public final static String INVALID_MOVE = "Invalid move, try again!";
    public final static String END_GAME = "Congratulations %s (%s), you won!";
    public final static String PLAYER_TURN = "%s's turn! %s";
    public final static String PASS = "YOU SHALL PASS";
    public final static String PIE_RULE_QUERY = "PlayerTwo, do you want to apply the Pie Rule? (y/n)";
    public final static String PIE_RULE = "Pie Rule applied: PlayerOne and PlayerTwo have switched colors!";
    public final static String X_INPUT = "Please insert next X coordinate:";
    public final static String Y_INPUT = "Please insert next Y coordinate:";
    public final static String CONSOLE_LOGO =  " _   __                  _     _ \n" +
                                        "| | / /                 | |   (_)\n" +
                                        "| |/ /  ___  _ __   ___ | |__  _ \n" +
                                        "|    \\ / _ \\| '_ \\ / _ \\| '_ \\| |\n" +
                                        "| |\\  \\ (_) | | | | (_) | |_) | |\n" +
                                        "\\_| \\_/\\___/|_| |_|\\___/|_.__/|_|\n" +
                                        "                                 ";
    public final static String INVALID_INTEGER_INPUT = "Invalid input, please insert a valid integer!";
    public final static String ASK_SIZE = "Please choose the size of the board (between 3 and 11):";
    public final static String INSTRUCTIONS = "%s should connect edges vertically\n" +
                                              "%s should connect edges horizontally";
    public final static String INVALID_STRING_INPUT = "Invalid input, please insert a valid string!";
    public final static String INVALID_SIZE_INPUT = "Invalid size, please choose a valid size for the board!";
    public final static String INVALID_COORDINATES_INPUT = "Invalid coordinates, please choose a point inside the board!";

    private Messages() {}

}
