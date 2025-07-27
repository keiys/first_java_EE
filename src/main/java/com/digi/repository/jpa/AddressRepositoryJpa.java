package com.digi.repository.jpa;

import com.digi.model.Address;
import com.digi.model.User;
import com.digi.repository.AddressRepository;
import com.digi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.ResultSet;

public class AddressRepositoryJpa implements AddressRepository {

    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(Address address, int user_id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
    }

    @Override
    public Address getAddressById(int id) {
        Session session = sessionFactory.openSession();
        NativeQuery<Address> nativeQuery = session.createNativeQuery("select * from address where id = ?", Address.class);
        nativeQuery.setParameter(1, id);
        return nativeQuery.uniqueResult();
    }

    @Override
    public void updateAddress(int user_id, Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(address);
        transaction.commit();
    }
}
