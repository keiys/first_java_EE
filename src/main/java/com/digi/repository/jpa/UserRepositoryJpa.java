package com.digi.repository.jpa;

import com.digi.enums.Status;
import com.digi.exceptions.UserApiException;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.util.HibernateUtil;
import com.digi.util.MyDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserRepositoryJpa implements UserRepository {

    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void create(User user){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();

    }

    @Override
    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from users where email = ?", User.class);
        nativeQuery.setParameter(1, email);
        return nativeQuery.uniqueResult();
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from users where id = ?", User.class);
        nativeQuery.setParameter(1, id);
        return nativeQuery.uniqueResult();
    }

    @Override
    public void updateStatus(int id, Status status, String verifyCode) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User s set s.status = :status where id = :id");
        query.setParameter("status", String.valueOf(status));
        query.setParameter("id", id);
        transaction.commit();

    }

    @Override
    public void updateResetToken(int id, String resetToken) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User s set s.resetToken = :resetToken where id = :id");
        query.setParameter("resetToken", resetToken);
        query.setParameter("id", id);
        transaction.commit();
    }

    @Override
    public void updatePassword(int id, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User s set s.password = :password where id = :id");
        query.setParameter("password", password);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void updateUser(User user, int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
    }
}
