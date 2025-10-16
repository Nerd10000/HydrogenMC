package dragon.me.HydrogenServer.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerException extends RuntimeException {

    private final int code;
    private final String hint;

    public ServerException(String message, String hint, int code) {
        super(message);
        this.hint = hint;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getHint() {
        return hint;
    }

    // Optional: helper method to log this exception
    public void log(Class<?> clazz,String stackTrace) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.warn("⚠ ServerException was caught: {} | Hint: {} | Code: {} | Trace: {}", getMessage(), getHint(), getCode(), stackTrace, this);
    }
}