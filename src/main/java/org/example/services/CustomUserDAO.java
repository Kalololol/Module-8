package org.example.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.datamodel.CustomUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CustomUserDAO {
    private final SessionFactory sessionFactory = CustomUserSessionFactory.getUserSessionFactory();


    public void createUser(CustomUser user){
        Transaction transaction = null;

        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void editUser(CustomUser user) {
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        s.merge(user);
        t.commit();
        s.close();
    }
    public void deleteUser(CustomUser user){
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        s.delete(user);
        t.commit();
        s.close();
    }

    public CustomUser findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CustomUser> userQuery = cb.createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root).where(cb.equal(root.get("email"), email));
        return session.createQuery(userQuery).getSingleResultOrNull();
    }
    public CustomUser findUserById(Long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CustomUser> userQuery = cb.createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root).where(cb.equal(root.get("id"), id));
        return session.createQuery(userQuery).getSingleResultOrNull();
    }



}
