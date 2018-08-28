package pl.coderslab.preschool_web_service.validation.security;

@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}