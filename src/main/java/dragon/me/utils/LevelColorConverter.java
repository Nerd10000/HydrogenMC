package dragon.me.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;

public class LevelColorConverter extends CompositeConverter<ILoggingEvent> {

    @Override
    protected String transform(ILoggingEvent event, String in) {
        return convert(event);
    }
    @Override
    public String convert(ILoggingEvent event) {
        switch(event.getLevel().toString()) {
            case "INFO": return "\u001B[32mINFO\u001B[0m";        // green
            case "WARN": return "\u001B[93mWARN\u001B[0m";        // bright yellow
            case "ERROR": return "\u001B[91mERROR\u001B[0m";      // bright red
            default: return event.getLevel().toString();          // default
        }
    }
}
