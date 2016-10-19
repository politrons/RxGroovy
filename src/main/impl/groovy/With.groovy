package impl.groovy

import org.junit.Test
/**
 * With operator allow us to open a closure inside the context of the instance,
 * Also in this context we can consume the external class context
 */
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

    @Test
    def void creation() {
        new Person().with {
            name = "John"
            age = 35
            sex = "male"
            nationality = "UK"
        }
    }

    @Test
    def void compare() {
        def person1 = new Person().with {
            name = "John"
            age = 35
            sex = "male"
            nationality = "UK"
        }

        def person2 = new Person(name: "Pablo", age: 35, sex: "male", nationality: "Spain")
        println person1.is(person2)//To compare reference objects
        println person1 == null// == as Equals in java Control null elements
    }

    class Person {

        String name = "Pablo"
        int age
        String sex
        String nationality

        def printDescription() {
            println "I´ a human"
        }

        def conflict() { "this method may cause conflicts for no humans" }


    }

}

