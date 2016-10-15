package impl.groovy

import org.junit.Test

import java.util.stream.Collectors

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Syntax {


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

    @Test
    def void interpolation() {
        def name = "Pablo"
        println "Hello my name is $name"
        println "2 + 2 = ${2 + 2}"
        println "2 potencial of 3 = ${2**3}"
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

    @Test
    def void replaceInMutable() {
        def newList = [1, 2, 3, 4, 5]
        print newList.stream()
                .map { n ->
            if (n == 2) {
                n = n * 100
            }
            n
        }.collect(Collectors.toList())
    }

    @Test
    def void defaultArguments() {
        printString("test")
        printString()
    }

    static def void printString(String arg = "Hello groovy") {
        println arg
    }


}

