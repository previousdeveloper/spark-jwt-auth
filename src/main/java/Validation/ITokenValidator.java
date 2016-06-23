package Validation;


public interface ITokenValidator {

    boolean validate(String token);

    boolean validateOauth(String accessToken);
}
