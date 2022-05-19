package es.upm.miw.mariavernia.vod.vodspring.domain.exceptions;

public class ConflictException extends Throwable {
    private static final String DESCRIPTION = "Conflict Exception";

    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
