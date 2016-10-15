package impl.groovy

import org.junit.Test
/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Collections {


    @Test
    def void map() {
        def mapNumber = [:]
        mapNumber."one" = 1
        mapNumber."two" = 2
        println mapNumber."one"
        println mapNumber."two"

        def defaultMap = [1: "one", 2: "two"]
        println defaultMap.get(1)
        println defaultMap.get(2)

    }

    /**
     * You can just define the array of elements and Groovy automatically
     * will detect as arrayList by default
     */
    @Test
    def void list() {
        def newList = [1, 2, 3, 4, 5]
        println newList.stream()
                .map({ number -> number * 100 })
                .findAll()
    }

    @Test
    def void foreach() {
        def newList = [1, 2, 3, 4, 5]
        newList.each { n -> print(n) }
    }

    /**
     * The * apply the method of the element list type for every element on the collection
     */
    @Test
    def void spreadDotUpperCase() {
        List<String> list = ["a", "b", "c"].asImmutable()
        println list*.toUpperCase()
    }

    @Test
    def void spreadDotCustom() {
        List<A> list = [new A(1), new A(2), new A(3)].asImmutable()
        println list*.multiplyBy(10)
    }

    class A {
        int a

        public def int multiplyBy(int number) {
            a * number
        }

        A(int a) {
            this.a = a
        }
    }
}

