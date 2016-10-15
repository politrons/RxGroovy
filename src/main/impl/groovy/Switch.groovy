package impl.groovy

import groovy.transform.CompileStatic
import org.junit.Test

/**
 * Groovy is so groovy that introduce switch with multi type, as match patter of scala introduce
 * means that we can provide an object type in the switch and valorate all possibilities
 */
@CompileStatic
class Switch {

    @Test
    def void switchTest(){
        println switchCase("S")
        println switchCase("X")
        println switchCase("s")
        println switchCase(10d)
        println switchCase(10)
        println switchCase(null)
    }

    public static String switchCase(Object input) {
        switch (input) {
            case "S":
                return "It's the String 'S'."
            case { it in String && (it as String).startsWith('X') }:
                return "It starts with 'X'."
            case ~/^[a-z].*/:
                return "It starts in lower case."
            case Double:
                return "It's a Double."
            case Integer:
                return "It's an Integer."
            default:
                return "It's anything else."

        }
    }

}
