package impl.groovy

import org.junit.Test

import java.util.stream.Collectors

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Syntax {

    @Test
    def void interpolation() {
        def name = "Pablo"
        println "Hello my name is $name"
        println "2 + 2 = ${2 + 2}"
        println "2 potencial of 3 = ${2**3}"
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

