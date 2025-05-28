package support.core.exception;

public class ConsultaConflitanteException extends RuntimeException {
    
    public ConsultaConflitanteException(String message) {
        super(message);
    }
    
    public ConsultaConflitanteException(String message, Throwable cause) {
        super(message, cause);
    }
} 