package impl.groovy

import org.junit.Test

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Closures {

    @Test
    def void main() {
        println "upper case:" + upperCase("pablo")
        println "default upper case:" + upperCase()
        println "sum:" + sum(3, 2)
        println "sum:${3 + 2}"
        println "default sum:" + sum()
        println "Array:" + array('one', 'two', 'three')
    }

    def upperCase = { String name = "default" -> name.toUpperCase() }

    def sum = { x = 1, y = 2 -> x + y }

    def array = { String... words -> words.each { word -> word.toUpperCase() }.toList() }

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

    /**
     * Apply memorize operator over a Closure catch the execution of this one with the argument passed
     * In this example the first two invokation since we are passing the same argument the second call it´s cached
     * The third call since the argument is new the execution take the normal time since it´s not cached
     * shall print
     *
     *      exec_1:588
     *      exec_2:1
     *      exec_3:503
     *      exec_4:0
     */
    @Test
    def void memoize() {
        def start = System.currentTimeMillis();
        memoizeTest(1)
        println "exec_1:${System.currentTimeMillis() - start}"
        start = System.currentTimeMillis();
        memoizeTest(1)
        println "exec_2:${System.currentTimeMillis() - start}"
        start = System.currentTimeMillis();
        memoizeTest(2)
        println "exec_3:${System.currentTimeMillis() - start}"
        start = System.currentTimeMillis();
        memoizeTest(1)
        println "exec_4:${System.currentTimeMillis() - start}"
    }

    def memoizeTest = { s -> Thread.sleep(500) }.memoize()

}
