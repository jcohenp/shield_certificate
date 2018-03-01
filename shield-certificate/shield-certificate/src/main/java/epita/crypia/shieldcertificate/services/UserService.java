package epita.crypia.shieldcertificate.services;

import epita.crypia.shieldcertificate.domain.User;
import epita.crypia.shieldcertificate.exceptions.UnmatchingUserCredentialsException;
import epita.crypia.shieldcertificate.exceptions.UserNotFoundException;

public interface UserService {
    User save(User user);

    void update(User user);

    User doesUserExist(String email) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;

    User isValidUser(String email, String password) throws UnmatchingUserCredentialsException;
}
