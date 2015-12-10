package toba.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import Account.Account;
import toba.business.User;
import toba.util.AccountType;

public class AccountDB {
		
	public static void insert(Account account) {
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
            EntityTransaction trans = em.getTransaction();
            trans.begin();        
            try {
                em.persist( account);
                trans.commit();
            } catch (Exception e) {
                System.out.println(e);
                trans.rollback();
            } finally {
                em.close();
            }
         }
        
        
        public static Account select(User user, AccountType accountType) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Account acct = null;
        String qString = "SELECT u FROM Account u";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        List<Account> results = q.getResultList();

        try {
            for ( Account list : results) {
             if ( (user.getFirstName().equals( list.getUser().getFirstName())) &&
                  (user.getLastName().equals( list.getUser().getLastName())) &&
                  (accountType == list.getAccountType()) ) {
                    acct = list;
                    break;
             }
        }
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return acct;
    }
	public static void update(Account account) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge( account);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
        
    public static void delete( Account account) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge( account));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
}
