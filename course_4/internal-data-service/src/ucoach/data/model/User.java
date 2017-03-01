package ucoach.data.model;

import ucoach.data.dao.UcoachDataDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The User class represents a user that will record health measures.
 * 
 * @author anadaniel
 *
 */
@Entity
@Table(name="user")
@NamedQueries({
  @NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
  @NamedQuery(name="User.findByEmail", query="SELECT u FROM User u WHERE u.email = :email")
})
@XmlType(propOrder={"id","firstname","lastname", "birthdate", "email", "password", "twitterUsername", "currentHealthMeasures", "coach"})
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator="mysql_user")
  @TableGenerator(name="mysql_user", table="mysql_sequence",
    pkColumnName="name", valueColumnName="seq",
    pkColumnValue="user")
  @Column(name="id")
  private int id;
  @Column(name="lastname")
  private String lastname;
  @Column(name="firstname")
  private String firstname;
  @Temporal(TemporalType.DATE)
  @Column(name="birthdate")
  private Date birthdate;
  @Column(name="email")
  private String email;
  @Column(name="password")
  private String password;
  @Column(name="twitter_username")
  private String twitterUsername;

  @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  private List<HealthMeasure> healthMeasures;

  @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  private List<Goal> goals;

  @ManyToOne
  @JoinColumn(name="coach_id",referencedColumnName="id")
  private Coach coach;
  
  /**
   * This attribute is not persisted in the database, it's just used to represent the
   * latest (current) measures that a user has recorded.
   */
  @Transient
  private List<HealthMeasure> currentHealthMeasures;

  // Getters
  public int getId(){
    return id;
  }
  public String getLastname(){
    return lastname;
  }
  public String getFirstname(){
    return firstname;
  }
  public Date getBirthdate(){
    return birthdate;
  }
  public String getEmail(){
    return email;
  }
  public String getPassword(){
    return password;
  }
  public String getTwitterUsername(){
    return twitterUsername;
  }
  @XmlTransient
  public List<HealthMeasure> getHealthMeasures(){
    return healthMeasures;
  }
  @XmlTransient
  public List<Goal> getGoals(){
    return goals;
  }
  @XmlElementWrapper(name="currentHealthMeasures")
  @XmlElement(name="healthMeasure")
  public List<HealthMeasure> getCurrentHealthMeasures(){
    // When the currentHealthMeasures is not empty it means it's reading the measures of a user that will be created with some initial measures.
    if (this.currentHealthMeasures == null)
      this.currentHealthMeasures = HealthMeasure.getCurrentMeasuresForUser(this.id); // If the currentHealthMeasures is empty then it loads the lates measures of a person to represent the Health Profile 

    return currentHealthMeasures;
  }
  // @XmlTransient
  public Coach getCoach(){
    return coach;
  }

  
  // Setters
  public void setId(int id){
    this.id = id;
  }
  public void setLastname(String lastname){
    this.lastname = lastname;
  }
  public void setFirstname(String firstname){
    this.firstname = firstname;
  }
  public void setBirthdate(Date birthdate){
    this.birthdate = birthdate;
  }
  public void setEmail(String email){
    this.email = email;
  }
  public void setPassword(String password){
    this.password = password;
  }
  public void setTwitterUsername(String twitterUsername){
    this.twitterUsername = twitterUsername;
  }
  public void setHealthMeasures(List<HealthMeasure> healthMeasures){
    this.healthMeasures = healthMeasures;
  }
  public void setGoals(List<Goal> goals){
    this.goals = goals;
  }
  public void setCoach(Coach coach){
    this.coach = coach;
  }

  /**
   * Returns every user in the database.
   * 
   * @return  A list of Users
   */
  public static List<User> getAll() {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<User> list = em.createNamedQuery("User.findAll").getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Finds a User in the database given its id.
   * @param id    The id of the user
   * @return      The found user
   */
  public static User getUserById(int id) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    User user = em.find(User.class, id);
    if(user != null)
      em.refresh(user);
    UcoachDataDao.instance.closeConnections(em);
    return user;
  }

  /**
   * Finds a User in the database given its email.
   * @param email   The email of the user
   * @return        The found user
   */
  public static User getUserByEmail(String email) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<User> list = em.createNamedQuery("User.findByEmail")
      .setParameter("email", email)
      .getResultList();
    User user = null;
    if (list.size() > 0){
      user = list.get(0);
      em.refresh(user);
    }
    UcoachDataDao.instance.closeConnections(em);
    return user;
  }
  
  /**
   * Creates a user in the database.
   * 
   * @param user  The user to be persisted in the database
   * @return        The saved user
   */
  public static User createUser(User user) {
  	try {
	    EntityManager em = UcoachDataDao.instance.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(user);
	    tx.commit();
	    UcoachDataDao.instance.closeConnections(em);

	    return user;
  	} catch (Exception e) {
  		System.out.println(e.getMessage());
  		return null;
  	}
  }
  
  /**
   * This method is used by the updateUser method to sync a user before updating
   * it on the database. When updating only specific fields, other fields will be set to
   * null, this method makes sure that the old values of the attributes that will not be 
   * updated remain in the database.
   * @param updatedUser   The user containing only the attributes that will be updated.
   * @return              A user with updated information but also keeping its old attributes
   */
  public static User syncUser(User updatedUser) {
    User oldUser = getUserById(updatedUser.id);
    updatedUser.setHealthMeasures(oldUser.getHealthMeasures());

    if (updatedUser.getFirstname() == null) updatedUser.setFirstname(oldUser.getFirstname());

    if (updatedUser.getLastname() == null) updatedUser.setLastname(oldUser.getLastname());

    if (updatedUser.getEmail() == null) updatedUser.setEmail(oldUser.getEmail());

    if (updatedUser.getPassword() == null) updatedUser.setPassword(oldUser.getPassword());

    if (updatedUser.getBirthdate() == null) updatedUser.setBirthdate(oldUser.getBirthdate());

    return updatedUser;
  }

  /**
   * Updates a User in the database. It makes sure to NOT override any data that 
   * is not being updated.
   * 
   * @param user  The user with the updated data that will be saved.
   * @return      The updated user
   */
  public static User updateUser(User user) {
    User updatedUser = syncUser(user);

    EntityManager em = UcoachDataDao.instance.createEntityManager(); 
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    updatedUser = em.merge(updatedUser);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
    return updatedUser;
  }

  /**
   * Deletes a User from the database.
   * 
   * @param userId  The id of user that will be deleted.
   */
  public static void deleteUser(int userId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    User user = em.find(User.class, userId);
    em.remove(user);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
  }
}