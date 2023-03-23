package com.springpractice.springpractice.Dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.springpractice.springpractice.Util.HibernateUtil;
import com.springpractice.springpractice.entity.User;

public class UserDao {
	
	private static final Logger
	logg = LogManager.getLogger(UserDao.class);
	
	
    private Session session;

    public UserDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
            logg.debug("got all user");
        } catch (HibernateException e) {
        	logg.error(e);
            handleException(e);
        } finally {
            session.close();
        }
        return users;
    }

    public User getUserById(Long id) {
        User user = null;
        try {
            session.beginTransaction();
            user = (User) session.get(User.class, id);
            session.getTransaction().commit();
            logg.debug("got user by id");
        } catch (HibernateException e) {
        	logg.error(e);
            handleException(e);
        } finally {
            session.close();
        }
        return user;
    }

    public void addUser(User user) {
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            logg.debug("user added");
        } catch (HibernateException e) {
        	logg.error(e);
            handleException(e);
        } finally {
            session.close();
        }
    }

    public void updateUser(User user) {
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            logg.debug("user updated");
        } catch (HibernateException e) {
        	logg.error(e);
            handleException(e);
        } finally {
            session.close();
        }
    }

    public void deleteUser(User user) {
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            logg.debug("user deleted");
        } catch (HibernateException e) {
        	logg.error(e);
            handleException(e);
        } finally {
            session.close();
        }
    }

    private void handleException(HibernateException e) {
        session.getTransaction().rollback();
        e.printStackTrace();
    }
}