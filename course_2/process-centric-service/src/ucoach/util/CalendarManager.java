package ucoach.util;

import java.util.Date;

/**
 * Created by szucs on 2/10/2017.
 */
public class CalendarManager implements ICalendarManager{

    @Override
    public Date getTime() throws ExceptionInInitializerError {

//        if(true) {
//            throw new ExceptionInInitializerError();
//        }

        return new Date();
    }
}
