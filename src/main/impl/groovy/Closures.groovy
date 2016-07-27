package impl.groovy

import org.junit.Test

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
class Closures {

    @Test
    def void main() {
        println upperCase("pablo")
        println sum(3, 2)
        println array("one", "two", "three")
    }

    def upperCase = { String name -> name.toUpperCase() }

    def sum = { x, y -> x + y }

    def array = { String... words -> words.each { word -> word.toUpperCase() }.toList() }

}
