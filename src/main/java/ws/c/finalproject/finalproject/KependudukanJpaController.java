/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.finalproject.finalproject;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ws.c.finalproject.finalproject.exceptions.NonexistentEntityException;
import ws.c.finalproject.finalproject.exceptions.PreexistingEntityException;

/**
 *
 * @author USER DJOGJA
 */
public class KependudukanJpaController implements Serializable {

    public KependudukanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kependudukan kependudukan) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kependudukan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKependudukan(kependudukan.getId()) != null) {
                throw new PreexistingEntityException("Kependudukan " + kependudukan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kependudukan kependudukan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kependudukan = em.merge(kependudukan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kependudukan.getId();
                if (findKependudukan(id) == null) {
                    throw new NonexistentEntityException("The kependudukan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kependudukan kependudukan;
            try {
                kependudukan = em.getReference(Kependudukan.class, id);
                kependudukan.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kependudukan with id " + id + " no longer exists.", enfe);
            }
            em.remove(kependudukan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kependudukan> findKependudukanEntities() {
        return findKependudukanEntities(true, -1, -1);
    }

    public List<Kependudukan> findKependudukanEntities(int maxResults, int firstResult) {
        return findKependudukanEntities(false, maxResults, firstResult);
    }

    private List<Kependudukan> findKependudukanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kependudukan.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Kependudukan findKependudukan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kependudukan.class, id);
        } finally {
            em.close();
        }
    }

    public int getKependudukanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kependudukan> rt = cq.from(Kependudukan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
