package fachada;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;  
  
@SuppressWarnings("deprecation")
public class HibernateUtil {  
  
    private static final SessionFactory sessionFactory;  
      
    private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();  
  
    static {  
        sessionFactory = new Configuration().configure().buildSessionFactory();  
    }  
  
    public static Session getSession() {  
          
       if (sessions.get() != null) {  
 
       }  
        sessions.set(sessionFactory.openSession());  
        return sessions.get();  
    }  
  
    public static void closeCurrentSession() {  
        sessions.get().close();  
        sessions.set(null);  
    }  
  
    public static Session currentSession() {  
        return sessions.get();  
    }  
      
    public static SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
      
} 