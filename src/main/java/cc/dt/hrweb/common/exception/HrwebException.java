package cc.dt.hrweb.common.exception;

/**
 * HRWEB的系统内部异常
 */
public class HrwebException extends Exception {

    private static final long serialVersionUID = -994962710559017255L;

    public HrwebException(String message) {
        super(message);
    }
}
