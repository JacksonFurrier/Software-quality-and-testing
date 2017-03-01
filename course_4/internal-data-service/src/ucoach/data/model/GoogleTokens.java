package ucoach.data.model;

import ucoach.data.dao.UcoachDataDao;

import java.io.Serializable;

import javax.persistence.*;

import ucoach.data.model.User;


/**
 * The GoogleTokens class contains google access tokens for the users
 * 
 * @author federico-fiorini
 *
 */
@Entity
@Table(name="google_tokens") 
@NamedQuery(name="GoogleTokens.findByUser", query="SELECT gt FROM GoogleTokens gt WHERE gt.user = :user")
public class GoogleTokens implements Serializable {

  private static final long serialVersionUID = 2L;

  @Id
  @GeneratedValue(generator="mysql_google_tokens")
  @TableGenerator(name="mysql_google_tokens", table="mysql_sequence",
    pkColumnName="name", valueColumnName="seq",
    pkColumnValue="google_tokens")
  @Column(name="id")
  private int id;

  @ManyToOne
  @JoinColumn(name="user_id", referencedColumnName="id")
  private User user;
  
  @Column(name="access_token")
  private String accessToken;
  
  @Column(name="refresh_token")
  private String refreshToken;

  public int getId() {
	return this.id;
  }
	
  public User getUser() {
	return this.user;
  }

  public String getAccessToken() {
	  return this.accessToken;
  }
  
  public String getRefreshToken() {
	  return this.refreshToken;
  }
  
  public void setId(int id) {
	this.id = id;
  }
	
  public void setUser(User user) {
	this.user = user;
  }

  public void setAccessToken(String accessToken) {
	  this.accessToken = accessToken;
  }
  
  public void setRefreshToken(String refreshToken) {
	  this.refreshToken = refreshToken;
  }
  
  /**
   * Find tokens by user
   * @param user
   * @return
   */
  public static GoogleTokens getTokensByUser(User user) {
	  
	  EntityManager em = UcoachDataDao.instance.createEntityManager();

      GoogleTokens tokens = new GoogleTokens();

      try {
    	  tokens = (GoogleTokens) em.createNamedQuery("GoogleTokens.findByUser")
	    	.setParameter("user", user)
	    	.getSingleResult();

      } catch (Exception e) {
      	return null;
      }
      
      UcoachDataDao.instance.closeConnections(em);
      return tokens;
  }
  
  /**
   * Create new google access tokens
   * @param tokens
   * @return
   */
  public GoogleTokens create() {
	  
	  EntityManager em = UcoachDataDao.instance.createEntityManager();
	  EntityTransaction tx = em.getTransaction();
	  tx.begin();
	  em.persist(this);
	  tx.commit();
	  UcoachDataDao.instance.closeConnections(em);

	  return this;
  }
  
  /**
   * Update google access tokens
   * @param tokens
   * @return
   */
  public GoogleTokens update() {
	  
	  EntityManager em = UcoachDataDao.instance.createEntityManager();
	  EntityTransaction tx = em.getTransaction();
	  tx.begin();
	  em.merge(this);
	  tx.commit();
	  UcoachDataDao.instance.closeConnections(em);

	  return this;
  }
}
