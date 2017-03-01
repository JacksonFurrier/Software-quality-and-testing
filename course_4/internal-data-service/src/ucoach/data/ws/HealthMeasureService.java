package ucoach.data.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import ucoach.data.model.HMType;
import ucoach.data.model.HealthMeasure;

import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@WebService(endpointInterface="ucoach.data.ws.HealthMeasureInterface",
  serviceName="HealthMeasureService")
public class HealthMeasureService implements HealthMeasureInterface {

  @Resource
  WebServiceContext context;

  @Override
  public List<HMType> getAllHMTypes() {
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return HMType.getAll();
  }

  @Override
  public HealthMeasure createHealthMeasure(HealthMeasure healthMeasure, int userId, int hmTypeId) {
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return HealthMeasure.createHealthMeasure(healthMeasure, userId, hmTypeId);
  }

  @Override
  public List<HealthMeasure> getHealthMeasuresFromUserByType(int userId, int hmTypeId){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }
    
    return HealthMeasure.getHealthMeasuresFromUserByHMType(userId, hmTypeId);
  }

  @Override
  public List<HealthMeasure> getHealthMeasuresFromUserByTypeAfterDate(int userId, int hmTypeId, String afterDate){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }

    Date date;    
    try{
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
      date = df.parse(afterDate);
    } catch (ParseException e){
      return null;
    }
    
    return HealthMeasure.getHealthMeasuresFromUserByHMTypeAfterDate(userId, hmTypeId, date);
  }

  @Override
  public List<HealthMeasure> getHealthMeasuresFromUserByTypeBetweenDates(int userId, int hmTypeId, String fromDate, String toDate){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
      return null;
    }

    Date from;
    Date to;  
    try{
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
      from = df.parse(fromDate);
      to = df.parse(toDate);
    } catch (ParseException e){
      return null;
    }
    
    return HealthMeasure.getHealthMeasuresFromUserByHMTypeBetweenDates(userId, hmTypeId, from, to);
  }

  @Override
  public void deleteHealthMeasure(int healthMeasureId){
    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check AuthenticationKey");
    } else{
      HealthMeasure.deleteHealthMeasure(healthMeasureId);
    }
  }
}
