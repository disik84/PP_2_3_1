package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("from User ").getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void editUser(Long id, String name, String lastName, byte age) {
        User user = getUserById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        entityManager.merge(user);
    }
}
