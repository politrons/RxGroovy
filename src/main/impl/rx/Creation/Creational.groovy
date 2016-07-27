
import org.junit.Test
import rx.Observable

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Creational {

    @Test
    def void map() {
        Observable.just("one", "two", "three")
                .map({ number -> number.toUpperCase() })
                .subscribe({ arg -> println(arg) })
    }
}
