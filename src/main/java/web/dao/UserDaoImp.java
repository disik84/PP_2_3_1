package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User ").getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void edit(Long id, String name, String lastName, byte age) {
        User user = getUser(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        entityManager.merge(user);
    }
}
