package impl.groovy

import org.junit.Test

import java.util.stream.Collectors

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Utils {

    /**
     * Read files in groovy is really simple, without needs any external library as in Java to avoid InputStreams
     */
    @Test
    def void readFile() {
        println new File("src/resources/test.txt").text
        println new File("src/resources/test.txt").readLines()
    }

    /**
     * Interpolation it´ really handy to avoid have to use String format or other mechanism
     * to add variables into string
     */
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

