package impl.groovy

import org.junit.Test

/**
 * Created by pabloperezgarcia on 16/10/2016.
 */
class Implicits {

    def value = 5

    def setDouble = { i = value -> i * 2 }

    @Test
    def void main() {
        println setDouble(6)  // Got 6
        println setDouble()   // Got 5
        value = 0
        println setDouble()   // Got 0
    }

    @Test
    def void customString() {
        println "Welcome implicits world in groovy".upperCaseNoSpace
        println "Welcome implicits world in groovy".removeSpaces

    }

    static {
        String.metaClass.getUpperCaseNoSpace = {
            delegate.toUpperCase().replace(" ", "_")
        }

        String.metaClass.getRemoveSpaces = {
            delegate.replace(" ", "")
        }
    }

    @Test
    def void numbers() {
        println 2.doubleVal
    }

    static {
        Integer.metaClass.getDoubleVal = {
            2 * delegate
        }
    }


}
