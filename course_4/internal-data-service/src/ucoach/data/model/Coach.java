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
 * The Coach class represents a coach that will monitor and set goals for a coach.
 * 
 * @author anadaniel
 *
 */
@Entity
@Table(name="coach") 
@NamedQuery(name="Coach.findAll", query="SELECT u FROM Coach u")
@XmlType(propOrder={"id","firstname","lastname", "birthdate", "email", "password"})
public class Coach implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator="mysql_coach")
  @TableGenerator(name="mysql_coach", table="mysql_sequence",
    pkColumnName="name", valueColumnName="seq",
    pkColumnValue="coach")
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

  @OneToMany(mappedBy="coach", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  private List<User> users;
  
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
  @XmlTransient
  public List<User> getUsers(){
    return users;
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
  public void setUsers(List<User> users){
    this.users = users;
  }

  /**
   * Finds a Coach in the database given its id.
   * @param id    The id of the coach
   * @return      The found coach
   */
  public static Coach getCoachById(int id) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    Coach coach = em.find(Coach.class, id);
    if(coach != null)
      em.refresh(coach);
    UcoachDataDao.instance.closeConnections(em);
    return coach;
  }
  
  /**
   * Creates a coach in the database.
   * 
   * @param coach  The coach to be persisted in the database
   * @return        The saved coach
   */
  public static Coach createCoach(Coach coach) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(coach);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
    return coach;
  }
  
  /**
   * This method is used by the updateCoach method to sync a coach before updating
   * it on the database. When updating only specific fields, other fields will be set to
   * null, this method makes sure that the old values of the attributes that will not be 
   * updated remain in the database.
   * @param updatedCoach   The coach containing only the attributes that will be updated.
   * @return              A coach with updated information but also keeping its old attributes
   */
  public static Coach syncCoach(Coach updatedCoach) {
    Coach oldCoach = getCoachById(updatedCoach.id);
    updatedCoach.setUsers(oldCoach.getUsers());

    if (updatedCoach.getFirstname() == null) updatedCoach.setFirstname(oldCoach.getFirstname());

    if (updatedCoach.getLastname() == null) updatedCoach.setLastname(oldCoach.getLastname());

    if (updatedCoach.getEmail() == null) updatedCoach.setEmail(oldCoach.getEmail());

    if (updatedCoach.getPassword() == null) updatedCoach.setPassword(oldCoach.getPassword());

    if (updatedCoach.getBirthdate() == null) updatedCoach.setBirthdate(oldCoach.getBirthdate());

    return updatedCoach;
  }

  /**
   * Updates a Coach in the database. It makes sure to NOT override any data that 
   * is not being updated.
   * 
   * @param coach  The coach with the updated data that will be saved.
   * @return      The updated coach
   */
  public static Coach updateCoach(Coach coach) {
    Coach updatedCoach = syncCoach(coach);

    EntityManager em = UcoachDataDao.instance.createEntityManager(); 
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    updatedCoach = em.merge(updatedCoach);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
    return updatedCoach;
  }

  /**
   * Deletes a Coach from the database.
   * 
   * @param coachId  The id of coach that will be deleted.
   */
  public static void deleteCoach(int coachId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Coach coach = em.find(Coach.class, coachId);
    em.remove(coach);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
  }
}