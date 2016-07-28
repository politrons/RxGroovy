import org.junit.Test

/**
 * Created by pabloperezgarcia on 27/7/16.
 *
 * Composition give the feature to combine multiple closures together in one execution
 * The execution order go from right to left
 */
class Composition {


    @Test
    def void compositionNumbers() {
        def times3plus2 = divComposition << sumComposition << multiplyComposition
        println times3plus2(3) //Shall print 4
    }

    @Test
    def void compositionString() {
        def trimAndToUpperCase = replace << toUpperCase
        println trimAndToUpperCase("a b c d e") //Shall print A B * D E
    }

    def toUpperCase = { String word -> word.toUpperCase() }
    def replace = { String word -> word.replace("C", "*") }
    def divComposition = { it / 2 }
    def sumComposition = { it + 2 }
    def multiplyComposition = { it * 2 }


}
