package ucoach.util;

/**
 * Created by szucs on 2/9/2017.
 */

public class ManagerFactory {

    static ICalendarManager m_manager = null;

    public static void SetManager(ICalendarManager mgr) {
        m_manager = mgr;
    }

    public static ICalendarManager Create(){
        if( m_manager != null ){
            return m_manager;
        }
        return new CalendarManager();

    }


}
