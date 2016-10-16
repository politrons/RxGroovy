package impl.groovy

import groovy.transform.CompileStatic
import org.junit.Test

/**
 * With operator allow us to open a closure inside the context of the instance,
 * Also in this context we can consume the external class context
 */
@CompileStatic
class With {

    static def main() { "main" }

    static def conflict() { "this method may cause conflicts" }

    @Test
    def void withInPerson() {
        new Person().with { person ->
            println name
            printDescription()
            println main()
            println person //<--- the instance in case you rather use it inside
        }
    }

    @Test
    def void contextConflictWith() {
        new Person().with {
            println conflict()
            println this.conflict()//--> Using "this" we mark that the method belongs to owner class
        }
    }

    class Person {

        def name = "Pablo"

        def printDescription() {
            println "I´ a human"
        }

        def conflict() { "this method may cause conflicts for no humans" }


    }

}

