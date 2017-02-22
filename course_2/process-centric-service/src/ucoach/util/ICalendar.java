package ucoach.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by szucs on 2/6/2017.
 */
public interface ICalendar {
    abstract public void add(int field, int amount);

    abstract public Date getTime();
}
