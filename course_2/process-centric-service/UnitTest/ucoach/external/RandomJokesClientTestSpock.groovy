package ucoach.external

import spock.lang.Specification

/**
 * Created by szucs on 2/2/2017.
 */
class RandomJokesClientTestSpock extends Specification {
    def "GetJoke"() {
        setup:
            def RandomJokesClient client = new RandomJokesClient()
        when:
            String joke = client.getJoke()
        then:
            joke.size() > 0
    }
}
