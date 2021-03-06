import org.junit.Test
import rx.Observable

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Creational {


    @Test
    def void create() {
        Observable ob = Observable.create { subscriber ->
            subscriber.onNext "hello reactive"
        }
        ob.subscribe { item -> print item }
    }

    @Test
    def void map() {
        Observable.just("one", "two", "three")
                .map { number -> number.toUpperCase() }
                .subscribe { arg -> println(arg) }
    }

    @Test
    def void flatMap() {
        Observable.from(1, 2, 3, 4, 5)
                .map { n -> n * 100 }
                .subscribe { n -> println n }
    }
}
