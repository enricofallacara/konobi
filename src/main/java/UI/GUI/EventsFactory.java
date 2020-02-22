package UI.GUI;

import UI.GUI.Events.EndGameEvent;
import UI.GUI.Events.PassRuleEvent;
import UI.GUI.Events.PieRuleEvent;
import javafx.event.Event;
import java.util.stream.Stream;


public class EventsFactory {

    public static Stream<? extends Event> createEvents() {
        return Stream.of(new EndGameEvent(), new PieRuleEvent(), new PassRuleEvent());
    }

}
