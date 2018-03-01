package epita.crypia.shieldcertificate.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import epita.crypia.shieldcertificate.domain.User;
import epita.crypia.shieldcertificate.exceptions.UnmatchingUserCredentialsException;
import epita.crypia.shieldcertificate.exceptions.UserNotFoundException;
import epita.crypia.shieldcertificate.repositories.UserDAO;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl  implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User doesUserExist(String email) throws UserNotFoundException {
        List<User> users = (List<User>) userDAO.findByEmail(email);
        if(users.size() == 0) {
            throw new UserNotFoundException("User does not exist in the database.");
        }
        return users.get(0);
    }

    @Override
    public User isValidUser(String email, String password) throws UnmatchingUserCredentialsException {
        List<User> users = (List<User>) userDAO.findByEmail(email);
        if(users == null || users.size() == 0) {
            throw new UnmatchingUserCredentialsException("User with given credentials is not found in the database.");
        }
        return users.get(0);
    }

    @Override
    public User getByEmail(String email) throws UserNotFoundException {
        return this.doesUserExist(email);
    }
}
