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
                .map { number -> number * 100 }
                .findAll()
    }

    /**
     * Groovy can use Java 8 stream with all same features
     */
    @Test
    def void stream() {
        def newList = [1, 1, 2, 3, 8, 4, 5, 6, 3, 7, 8, 9, 3, 10]
        println newList.stream()
                .map { number -> number * 100 }
                .peek { number -> println("Item emitted:$number") } //Consumer function, like onNext
                .filter { number -> number > 500 } //predicate function
                .distinct()//only unique values
                .sorted()//Order items
                .limit(4)//Max number of items
                .findAll()//Get all values
    }

    /**
     * Java 8 predicate function "filter" which return true/false
     */
    @Test
    def void predicateFunctions() {
        def newList = [1, "2", 3, "4", 5]
        println newList.stream()
                .filter { number -> Character.isDigit(number as char) }
                .map { number -> number * 100 }
                .allMatch { number -> Character.isDigit(number as char) }
    }

    /**
     * Java 8 Consumer function "peek" which receive argument but the function is void
     */
    @Test
    def void consumerFunctions() {
        def newList = [1, "2", 3, "4", 5]
        newList.stream()
                .peek { number -> println("Item emitted:$number") }
                .findAll()
    }

    /**
     * Foreach is so much less verbose than java!, just need to pass the lambdas with the item emitted without type,
     * Only in case that you want autocomplete for your IDE you need to specify the type.
     */
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

