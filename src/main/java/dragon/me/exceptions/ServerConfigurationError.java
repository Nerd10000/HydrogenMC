package dragon.me.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConfigurationError extends ServerException {

    public ServerConfigurationError(String message, String hint, int code) {
        super(message, hint, code);
        //TODO Auto-generated constructor stub
    }
    public int getCode() {
        return this.getCode();
    }

    public String getHint() {
        return this.getHint();
    }

    // Optional: helper method to log this exception
    public void log(Class<?> clazz,String stackTrace) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.warn("⚠ ServerConfigurationError was caught: {} | Hint: {} | Code: {} | Trace: {}", getMessage(), getHint(), getCode(), stackTrace, this);
    }
    
}
