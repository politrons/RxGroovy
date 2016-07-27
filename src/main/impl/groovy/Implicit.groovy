import org.junit.Test

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Implicit {

    @Test
    def void implicit() {
        def greeting = { "Hello, $it!" }
        assert greeting('Patrick') == 'Hello, Patrick!'
    }

}
