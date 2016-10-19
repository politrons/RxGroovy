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

    @Test
    def void defaultConstructor() {
        new A(a: "A", a1: "A1")
    }

    public class A {
        String a
        String a1
    }

    @Test
    def void nullValues() {
        def nullValue = null
        if (nullValue) {
            println "If this happens I wont trust Groovy anymore"
        } else {
            println "This value is null"
        }
        //Elvis operator
        def result = nullValue ?: "Unknown"
        println result
    }

    @Test
    def void multiMethods(){
        Object o = "Object";
        assert method(o) == "string"
         o = 20d;
        assert method(o) == "double"
        o = 20.0f;
        assert method(o) == "float"
    }

    String method(String arg) {
        return "string";
    }
    String method(Double arg) {
        return "double";
    }
    String method(Float arg) {
        return "float";
    }

    @Test
    def void wrapTest(){
        println wrap("hello groovy", 5)
    }

    String wrap(String input, int limit) {
        def m = (input =~ /(.{1,$limit})( |\n|$)/)
        m.collect{ it[1] }.join("\n")
    }



}

