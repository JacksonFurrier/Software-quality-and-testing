package ucoach.data.model;

import ucoach.data.dao.UcoachDataDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The HealthMeasure class represents recorded health measures of a User
 * 
 * @author anadaniel
 *
 */
@Entity
@NamedQueries({
  @NamedQuery(
    name = "HealthMeasure.findCurrentMeasuresForUser",
    query = "SELECT hm FROM HealthMeasure hm, HMType hmt WHERE hm.user.id = :uid AND hm.hmType.id = hmt.id GROUP BY hmt.id"
  ),
  @NamedQuery(
    name = "HealthMeasure.findHealthMeasuresFromUserByHMType",
    query = "SELECT hm FROM HealthMeasure hm, HMType hmt WHERE hm.user.id = :uid AND hm.hmType.id = hmt.id AND hmt.id = :hmtid ORDER BY hm.id DESC"
  ),
  @NamedQuery(
    name = "HealthMeasure.findHealthMeasuresFromUserByHMTypeAfterDate",
    query = "SELECT hm FROM HealthMeasure hm, HMType hmt WHERE hm.user.id = :uid AND hm.hmType.id = hmt.id AND hmt.id = :hmtid AND hm.createdDate >= :date ORDER BY hm.id DESC"
  ),
  @NamedQuery(
    name = "HealthMeasure.findHealthMeasuresFromUserByHMTypeBetweenDates",
    query = "SELECT hm FROM HealthMeasure hm, HMType hmt WHERE hm.user.id = :uid AND hm.hmType.id = hmt.id AND hmt.id = :hmtid AND hm.createdDate BETWEEN :from AND :to ORDER BY hm.id DESC"
  )
})
@Table(name="health_measure") 
public class HealthMeasure implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator="mysql_health_measure")
  @TableGenerator(name="mysql_health_measure", table="mysql_sequence",
    pkColumnName="name", valueColumnName="seq",
    pkColumnValue="health_measure")
  @Column(name="id")
  private int id;
  @Column(name="value")
  private Float value;
  @Temporal(TemporalType.DATE)
  @Column(name="created_date")
  private Date createdDate;

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
  public Float getValue(){
    return value;
  }
  public Date getCreatedDate(){
    return createdDate;
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
  public void setValue(Float value){
    this.value = value;
  }
  public void setCreatedDate(Date createdDate){
    this.createdDate = createdDate;
  }
  public void setUser(User user){
    this.user = user;
  }
  public void setHmType(HMType hmType){
    this.hmType = hmType;
  }

  /**
   * Finds a HealthMeasure in the database given its id.
   * @param id    The id of the healthMeasure
   * @return      The found healthMeasure
   */
  public static HealthMeasure getHealthMeasureById(int id) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    HealthMeasure healthMeasure = em.find(HealthMeasure.class, id);
    if(healthMeasure != null)
      em.refresh(healthMeasure);
    UcoachDataDao.instance.closeConnections(em);
    return healthMeasure;
  }

  /**
   * Gets the last recorded measure of every HMType for a given User.
   * 
   * @param userId   The id of the user.
   * @return           A list of the current HealthMeasures of a User
   */
  public static List<HealthMeasure> getCurrentMeasuresForUser(int userId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<HealthMeasure> list = em.createNamedQuery("HealthMeasure.findCurrentMeasuresForUser")
      .setParameter("uid", userId)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Gets the Health Measures for a given User and a given HM Type.
   * 
   * @param userId    The id of the user.
   * @param hmTypeId  The id of the MeasureType.
   * @return          A list of a User's Health Measures of a given HM Type.
   */
  public static List<HealthMeasure> getHealthMeasuresFromUserByHMType(int userId, int hmTypeId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<HealthMeasure> list = em.createNamedQuery("HealthMeasure.findHealthMeasuresFromUserByHMType")
      .setParameter("uid", userId)
      .setParameter("hmtid", hmTypeId)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Gets the Health Measures for a given User and a given HM Type and after a specified createdDate.
   * 
   * @param userId        The id of the user.
   * @param hmTypeId      The id of the MeasureType.
   * @param createdAfter  
   * @return              A list of a User's Health Measures of a given HM Type.
   */
  public static List<HealthMeasure> getHealthMeasuresFromUserByHMTypeAfterDate(int userId, int hmTypeId, Date afterDate) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<HealthMeasure> list = em.createNamedQuery("HealthMeasure.findHealthMeasuresFromUserByHMTypeAfterDate")
      .setParameter("uid", userId)
      .setParameter("hmtid", hmTypeId)
      .setParameter("date", afterDate, TemporalType.DATE)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Gets the Health Measures for a given User and a given HM Type and bewteen two dates.
   * 
   * @param userId        The id of the user.
   * @param hmTypeId      The id of the MeasureType.
   * @param fromDate
   * @param toDate
   * @return              A list of a User's Health Measures of a given HM Type.
   */
  public static List<HealthMeasure> getHealthMeasuresFromUserByHMTypeBetweenDates(int userId, int hmTypeId, Date fromDate, Date toDate) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<HealthMeasure> list = em.createNamedQuery("HealthMeasure.findHealthMeasuresFromUserByHMTypeBetweenDates")
      .setParameter("uid", userId)
      .setParameter("hmtid", hmTypeId)
      .setParameter("from", fromDate, TemporalType.DATE)
      .setParameter("to", toDate, TemporalType.DATE)
      .getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Creates a Health Measure in the database.
   * 
   * @param healthMeasure   The Health Measure to be persisted in the database.
   * @param userId          The id of the user the Health Measure belongs to.
   * @param hmTypeId        The id of the HM Type of the Health Measure.
   * @return                The created Health Measure.
   */
  public static HealthMeasure createHealthMeasure(HealthMeasure healthMeasure, int userId, int hmTypeId) {
    healthMeasure.setUser(User.getUserById(userId));
    healthMeasure.setHmType(HMType.getHMTypeById(hmTypeId));
    if ( healthMeasure.getCreatedDate() == null ) healthMeasure.setCreatedDate(new Date());

    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(healthMeasure);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
    return healthMeasure;
  }

  /**
   * Deletes a HealthMeasure from the database.
   * 
   * @param healthMeasureId  The id of the healthMeasure that will be deleted.
   */
  public static void deleteHealthMeasure(int healthMeasureId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    HealthMeasure healthMeasure = em.find(HealthMeasure.class, healthMeasureId);
    em.remove(healthMeasure);
    tx.commit();
    UcoachDataDao.instance.closeConnections(em);
  }
}