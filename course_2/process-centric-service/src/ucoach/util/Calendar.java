package ucoach.util;

import java.util.Date;

/**
 * Created by szucs on 2/6/2017.
 */
class Calendar implements ICalendar  {
    //I am a fake
    static final int DATE = 5;

    @Override
    public void add(int field, int amount) {
        return;
    }

    @Override
    public Date getTime() {
        return new Date();
    }

    public static Calendar getInstance(){
        return new Calendar();
    }
}


