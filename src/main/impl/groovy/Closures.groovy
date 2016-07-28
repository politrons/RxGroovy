package impl.groovy

import org.junit.Test

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Closures {

    /**
     * Basic syntax of Closure which fundamental like functions on Java 8 or Scala
     */
    @Test
    def void main() {
        println "upper case:" + upperCase("pablo")
        println "default upper case:" + upperCase()
        println "sum:" + sum(3, 2)
        println "sum:${3 + 2}"
        println "default sum:" + sum()
        println "Array:" + array('one', 'two', 'three')
        println "Implicit:" + implicit("this is an implicit value")
    }

    def upperCase = { String name = "default" -> name.toUpperCase() }

    def sum = { x = 1, y = 2 -> x + y }

    def array = { String... words -> words.each { word -> word.toUpperCase() }.toList() }

    def implicit = { it.toUpperCase()}

    /**
     * Since we dont force a lazy evaluation in the Closure, the new value it´ not applied
     */
    @Test
    def void mutableString() {
        println mutable
        p = "B"
        println mutable
        assert mutable == "Name: B"

    }

    /**
     * To force a lazy evaluation we muast specify -> in the Closure expresion
     */
    @Test
    def void unMutableString() {
        println unMutable
        p = "B"
        println unMutable
        assert unMutable == "Name: A"
    }

    def p = "A"

    def mutable = "Name: ${-> p}"

    def unMutable = "Name: ${p}"

    /**
     * Curry argument will create the n number of functions copies which will accept 1 String argument
     */
    @Test
    def void copy() {
        def functions = nCopies.curry(3)
        println functions("test")
    }

    def nCopies = { int n, String str -> str.concat(" ") * n }



}

