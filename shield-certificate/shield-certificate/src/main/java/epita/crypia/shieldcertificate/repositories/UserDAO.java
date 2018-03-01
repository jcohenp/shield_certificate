package epita.crypia.shieldcertificate.repositories;

import java.util.List;

import epita.crypia.shieldcertificate.domain.User;

public interface UserDAO  {

    User save(User user);

    List<User> findByEmail(String email);

    void update(User user);

}
