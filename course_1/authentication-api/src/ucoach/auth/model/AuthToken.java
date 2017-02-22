package ucoach.auth.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.MessageDigest;

import ucoach.auth.dao.*;


@Entity
@NamedQuery(name="AuthToken.findAll", query="SELECT t FROM AuthToken t")
@Table(name = "tokens")
@XmlRootElement
public class AuthToken implements Serializable{
	@Id
	@GeneratedValue(generator="mysql_authtoken")
	@TableGenerator(name="mysql_authtoken", table="mysql_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="AuthToken")
	@Column(name = "id")
	private long id;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "user_id")
	private long userid;
	
	
	@Column(name = "created")
	private Date created;
	
	
	
	public AuthToken(long id, String token, long uId, Date created) {
		super();
		this.id = id;
		this.token = token;
		this.userid = uId;
		this.created = created;
	}
	public AuthToken(){
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long uId) {
		this.userid = uId;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	 public static AuthToken getTokenById(long tokenId) {
	        EntityManager em = AuthDao.instance.createEntityManager();
	        AuthToken t = em.find(AuthToken.class, tokenId);
	        AuthDao.instance.closeConnections(em);
	        return t;
	 }
	 
	 public static AuthToken getIdPersonByToken(String token){
		 EntityManager em = AuthDao.instance.createEntityManager();
	        AuthToken at = new AuthToken();
	        try{
	        	//SELECT THE LAST ENTRY WITH THE GIVEN USER ID AND TOKEN
	             at = (AuthToken) em.createQuery(        
	            "SELECT at FROM AuthToken at WHERE at.token = :token ORDER BY at.id DESC")
	            .setParameter("token", token).setMaxResults(1)
	            .getSingleResult();
	        }catch(Exception e){
	            System.out.println("Error"+e);
	            return null;
	            
	        }
	        
	        AuthDao.instance.closeConnections(em);
	        return at;
	 }
	 
	 public static AuthToken getTokenByPerson(long personId, String token) {
	        EntityManager em = AuthDao.instance.createEntityManager();
	        AuthToken at = new AuthToken();
	        try{
	        	//SELECT THE LAST ENTRY WITH THE GIVEN USER ID AND TOKEN
	             at = (AuthToken) em.createQuery(        
	            "SELECT at FROM AuthToken at WHERE at.userid = :pID AND at.token = :token ORDER BY at.id DESC")
	            .setParameter("pID", personId)
	            .setParameter("token", token).setMaxResults(1)
	            .getSingleResult();
	        }catch(Exception e){
	            System.out.println("Error"+e);
	            return null;
	            
	        }
	        
	        AuthDao.instance.closeConnections(em);
	        return at;
	 }
	 
	 public void generateNewRandonToken() throws Exception{
		Date date = new Date();
		String content = ""+ userid + "secret" + date;
		content = content.replaceAll("\\s","");
		
	    	
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(content.getBytes());
        
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
       
        this.setToken(sb.toString());
	 }
	 
	
	public static AuthToken saveToken(AuthToken t) {
		try{
			//System.out.println("SAVING");
			EntityManager em = AuthDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(t);
			tx.commit();
			AuthDao.instance.closeConnections(em);
		    return t;
		}catch(Exception e){
			return null;
		}
		
		
	}
	
	public static AuthToken updateLifeStatus(AuthToken t) {
		EntityManager em = AuthDao.instance.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		t=em.merge(t);
		tx.commit();
		AuthDao.instance.closeConnections(em);
	    return t;
	}
	
	public static void removeAuthToken(AuthToken t) throws Exception{
		EntityManager em = AuthDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		System.out.println(t.id);
		System.out.println(t.token);
		System.out.println(t.userid);
		tx.begin();
	    t=em.merge(t);
	    em.remove(t);
	    tx.commit();
	    AuthDao.instance.closeConnections(em);
	}
	
}
