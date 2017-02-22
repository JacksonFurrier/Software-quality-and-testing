package ucoach.external

import spock.lang.Specification

/**
 * Created by szucs on 2/6/2017.
 */
class HelloSpock extends Specification  {

    def "abra"() {
        expect:
        name.size() == length

        where:
        name    | length
        "Spock" | 5
        "Kirk"  | 4
        "Scotty"| 6
    }

}
