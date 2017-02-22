package ucoach.external

import java.lang.annotation.*;
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by szucs on 2/3/2017.
 */
class RandomJokesClientTestSpockShared extends Specification {

    static{
        System.setProperty "spock.configuration", "IncludeFastConfig.groovy"
    }

    @Shared client = new RandomJokesClient();

    @Fast
    def "GetJoke_Shared_Fast"() {
        expect: true
    }

    @Slow
    def "GetJoke_Shared_Slow"() {
        expect: true

    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Fast {}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Slow {}