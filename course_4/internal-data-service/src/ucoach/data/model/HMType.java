package ucoach.data.model;

import ucoach.data.dao.UcoachDataDao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The HMType class represents a Health Measure Type.
 * 
 * @author anadaniel
 *
 */
@Entity
@Table(name="hm_type") 
@NamedQuery(name="HMType.findAll", query="SELECT hmt FROM HMType hmt")
public class HMType implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator="mysql_hm_type")
  @TableGenerator(name="mysql_hm_type", table="mysql_sequence",
    pkColumnName="name", valueColumnName="seq",
    pkColumnValue="hm_type")
  @Column(name="id")
  private int id;
  @Column(name="name")
  private String name;
  @Column(name="units")
  private String units;
  
  // Getters
  public int getId(){
    return id;
  }
  public String getName(){
    return name;
  }
  public String getUnits(){
    return units;
  }
  
  // Setters
  public void setId(int id){
    this.id = id;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setUnits(String units){
    this.units = units;
  }

  /**
   * Returns every health measure type in the database.
   * 
   * @return  A list of HM Types
   */
  public static List<HMType> getAll() {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    List<HMType> list = em.createNamedQuery("HMType.findAll").getResultList();
    UcoachDataDao.instance.closeConnections(em);
    return list;
  }

  /**
   * Finds a HM Type given its id.
   * 
   * @param hmTypeId  The id that identifies a health measure type in the database.
   * @return          The found HM Type object.
   */
  public static HMType getHMTypeById(int hmTypeId) {
    EntityManager em = UcoachDataDao.instance.createEntityManager();
    HMType hmType = em.find(HMType.class, hmTypeId);
    UcoachDataDao.instance.closeConnections(em);
    return hmType;
  }
}