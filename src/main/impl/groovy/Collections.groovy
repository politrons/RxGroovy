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

    @Test
    def void collectWithClosure() {
        def list = (1..10).collect { it * 2 }
        println list
    }

    /**
     * Passing the seed and closure to inject it give us the change to iterate the array and check if the items
     * are part of the sentence, in case of true we pass the seed to next item interaction
     */
    @Test
    def void inject() {
        def wordList = ["test", "Groovy", "Grails", "Gradle", "dynamic"]
        def sentence = "This is an example blog talking about Groovy and Gradle."
        println wordList.inject(false) {
            acc, value -> acc || sentence.contains(value)
        }
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

    @Test
    def void groups() {
        def (passed, failed) = [1, 2, 3, 4, 5, 6, 7].split { it > 4 }
        println passed
        println failed
    }

    @Test
    def void groupsHumans() {
        def (male, female) = [new Human("Pablo", "male"),
                              new Human("John", "male"),
                              new Human("Maria", "female"),
                              new Human("Bea", "female"),
                              new Human("Jack", "male")]
                .split { it.sex.equals("male") }
        println male
        println female
    }

    @Test
    def void numbers() {
        def (number, string) = ["1", "2d", "bla", "4f", "foo"]
                .split { it.isNumber() || it.isFloat() || it.isDouble() }
        println "number:" + number
        println "strung:" + string
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


    class Human {

        String name;
        String sex;

        Human(String name, String sex) {
            this.name = name
            this.sex = sex
        }
    }

}

