package ucoach.data.model;

import ucoach.data.dao.UcoachDataDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The Goal class represents a Goal set by a User regarding a Health Measure he keeps track of.
 * 
 * @author anadaniel
 *
 */
@Entity
@NamedQueries({
  @NamedQuery(
    name = "Goal.findGoalsFromUser",
    query = "SELECT g FROM Goal g WHERE g.user.id = :uid ORDER BY g.id DESC"
  ),
  @NamedQuery(
    name = "Goal.findGoalsFromUserByHMType",
    query = "SELECT g FROM Goal g, HMType hmt WHERE g.user.id = :uid AND g.hmType.id = hmt.id AND hmt.id = :hmtid ORDER BY g.id DESC"
  ),
  @NamedQuery(
  name = "Goal.findGoalsFromUserByHMTypeAndAchievedStatus",
  query = "SELECT g FROM Goal g, HMType hmt WHERE g.user.id = :uid AND g.achieved = :a AND g.hmType.id = hmt.id AND hmt.id = :hmtid ORDER BY g.id DESC"
  ),
  @NamedQuery(
    name = "Goal.findGoalsFromUserAfterDueDate",
    query = "SELECT g FROM Goal g WHERE g.user.id = :uid AND g.dueDate >= :date ORDER BY g.id DESC"
  ),
  @NamedQuery(
    name = "Goal.findGoalsFromUserByFrequencyAndDueDate",
    query = "SELECT g FROM Goal g WHERE g.user.id = :uid AND g.frequency = :freq AND g.dueDate = :date ORDER BY g.id DESC"
  )
})
@Table(name="goal") 
public class Goal implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator="mysql_goal")
  @TableGenerator(name="mysql_goal", table="mysql_sequence",
    pkColumnName="name", valueColumnName="seq",
    pkColumnValue="goal")
  @Column(name="id")
  private int id;
  @Column(name="frequency")
  private String frequency;
  @Column(name="objective")
  private String objective;
  @Column(name="value")
  private Float value;
  @Temporal(TemporalType.DATE)
  @Column(name="created_date")
  private Date createdDate;
  @Temporal(TemporalType.DATE)
  @Column(name="due_date")
  private Date dueDate;
  @Column(name="achieved")
  private int achieved;

  @ManyToOne
  @JoinColumn(name="hm_type_id",referencedColumnName="id")
  private HMType hmType;

  @ManyToOne
  @JoinColumn(name="user_id", referencedColumnName="id")
  private User user;
  
  // Getters
  public int getId(){
    return id;
  }
  public String getFrequency(){
    return frequency;
  }
  public String getObjective(){
    return objective;
  }
  public Float getValue(){
    return value;
  }
  public Date getCreatedDate(){
    return createdDate;
  }
  public Date getDueDate(){
    return dueDate;
  }
  public int getAchieved(){
    return achieved;
  }
  @XmlTransient
  public User getUser(){
    return user;
  }
  public HMType getHmType(){
    return hmType;
  }
  
  // Setters
  public void setId(int id){
    this.id = id;
  }
  public void setFrequency(String frequency){
    this.frequency = frequency;
  }
  public void setObjective(String objective){
    this.objective = objective;
  }
  public void setValue(Float value){
    this.value = value;
  }
  public void setCreatedDate(Date createdDate){
    this.createdDate = createdDate;
  }
  public void setDueDate(Date dueDate){
    this.dueDate = dueDate;
  }
  public void setAchieved(int achieved){
    this.achieved = achieved;
  }
  public void setUser(User user){
    this.user = user;
  }
  public void setHmType(HMType hmType){
    this.hmType = hmType;
  }

  /**
   * Finds a Goal in the database given its id.
   * @param id    The id of the goal
   * @return      The found goal
   */
  public static Goal getGoalById(int id) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    Goal goal = em.find(Goal.class, id);
    if(goal != null)
      em.refresh(goal);
    UcoachDataDao.instance.closeConnections(em);
    return goal;
  }

  /**
   * Gets the Goals for a given User.
   * 
   * @param userId    The id of the user.
   * @return          A list of a User's Goals.
   */
  public static List<Goal> getGoalsFromUser(int userId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<Goal> list = em.createNamedQuery("Goal.findGoalsFromUser")
      .setParameter("uid", userId)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Gets the Goals for a given User and a given HM Type.
   * 
   * @param userId    The id of the user.
   * @param hmTypeId  The id of the MeasureType.
   * @return          A list of a User's Goals of a given HM Type.
   */
  public static List<Goal> getGoalsFromUserByHMType(int userId, int hmTypeId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<Goal> list = em.createNamedQuery("Goal.findGoalsFromUserByHMType")
      .setParameter("uid", userId)
      .setParameter("hmtid", hmTypeId)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Gets the Goals for a given User, a given HM Type and a given Achieved status.
   * 
   * @param userId    The id of the user.
   * @param hmTypeId  The id of the MeasureType.
   * @param achieved  Whether a goal has been achieved 
   * @return          A list of a User's Goals of a given HM Type.
   */
  public static List<Goal> getGoalsFromUserByHMTypeAndAchievedStatus(int userId, int hmTypeId, int achieved) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<Goal> list = em.createNamedQuery("Goal.findGoalsFromUserByHMTypeAndAchievedStatus")
      .setParameter("uid", userId)
      .setParameter("hmtid", hmTypeId)
      .setParameter("a", achieved)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Gets the Goals for a given User after a specified dueDate.
   * 
   * @param userId        The id of the user.
   * @param dueDate  
   * @return              A list of a User's Goals.
   */
  public static List<Goal> getGoalsFromUserAfterDueDate(int userId, Date dueDate) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<Goal> list = em.createNamedQuery("Goal.findGoalsFromUserAfterDueDate")
      .setParameter("uid", userId)
      .setParameter("date", dueDate, TemporalType.DATE)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Gets the Goals for a given User a frequency and dueDate.
   * 
   * @param userId      The id of the user.
   * @param frequency   The frequency of the goals.
   * @param dueDate     The dueDate of the Goals.
   * @return            A list of a User's Goals.
   */
  public static List<Goal> getGoalsFromUserByFrequencyAndDueDate(int userId, String frequency, Date dueDate) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<Goal> list = em.createNamedQuery("Goal.findGoalsFromUserByFrequencyAndDueDate")
      .setParameter("uid", userId)
      .setParameter("freq", frequency)
      .setParameter("date", dueDate)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Creates a Goal in the database.
   * 
   * @param Goal      The Goal to be persisted in the database.
   * @param userId    The id of the user the Goal belongs to.
   * @param hmTypeId  The id of the HM Type of the Goal.
   * @return          The created Goal.
   */
  public static Goal createGoal(Goal goal, int userId, int hmTypeId) {
    goal.setUser(User.getUserById(userId));
    goal.setHmType(HMType.getHMTypeById(hmTypeId));
    if ( goal.getCreatedDate() == null ) goal.setCreatedDate(new Date());

    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(goal);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
    return goal;
  }

  /**
   * Sets a goal to achieved = 1
   * 
   * @param Goal      The Goal to be updated in the database.
   * @return          The updated Goal.
   */
  public static Goal achieveGoal(Goal goal) {
    goal.setAchieved(1);

    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    goal = em.merge(goal);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
    return goal;
  }

  /**
   * Deletes a Goal from the database.
   * 
   * @param goalId  The id of the goal that will be deleted.
   */
  public static void deleteGoal(int goalId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Goal goal = em.find(Goal.class, goalId);
    em.remove(goal);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
  }
}