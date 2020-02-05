package UI.GUI;

import javafx.event.Event;
import javafx.event.EventType;

public class PassRuleEvent extends Event {
    public static final EventType<PassRuleEvent> PASS_RULE_EVENT_TYPE = new EventType<>(Event.ANY, "PASS_RULE");

    public PassRuleEvent() { super(PASS_RULE_EVENT_TYPE); }

}
