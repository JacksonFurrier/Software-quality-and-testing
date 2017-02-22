package ucoach.util

import spock.lang.Specification

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import java.util.Date;

/**
 * Created by szucs on 2/6/2017.
 */
// highligh stubs

class DatePatternsTestSpock extends Specification {

    Calendar m_Calendar

    def setup()
    {
        m_Calendar = Calendar.getInstance();
    }

    @Slow
    def "GetYesterdayDate_CheckGlobalState"() {
        setup:
        def date = new Date()

        when:
        date = m_Calendar.getTime()
        DatePatterns.getYesterdayDate()

        then:
        date  == m_Calendar.getTime()
    }

    @Slow
    def "GetYesterdayDate_CheckCalculation"() {
        setup:
        def today = new Date()
        def yesterday = new Date()

        when:
        today = m_Calendar.getTime()
        yesterday = DatePatterns.getYesterdayDate()

        then:
        today - 1 == yesterday
    }

    @Slow
    def "GetYesterdayDate_CheckCalculation_Expect"() {
        expect: DatePatterns.getYesterdayDate() == m_Calendar.getTime() - 1
    }

    @Slow
    def "GetYesterdayDateDelegate_CheckCalculation_Expect"() {
        expect: DatePatterns.getYesterdayDate_Delegate() == m_Calendar.getTime() - 1;
    }

    @Slow
    def "GetYesterdayDateFactory_CheckCalculation_Expect"() {
        setup:
            def today = new Date()
            def yesterday = new Date()
            def myFakeManager = new CalendarManager()
            ManagerFactory.SetManager(myFakeManager)
        
        when:
            today = m_Calendar.getTime()
            yesterday = DatePatterns.getYesterdayDate_Factory()
        then:
            today - 1 == yesterday
    }


}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Fast {}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Slow {}