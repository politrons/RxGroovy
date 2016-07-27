package impl.groovy

import groovy.transform.CompileStatic
import org.junit.Test
/**
 * Created by pabloperezgarcia on 27/7/16.
 */
@CompileStatic
class Delegates {

    class Person {
        String name

        Person(String name) {
            this.name = name
        }

        def foo(){
            println "delegate"
        }
    }

    class Numbers {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5)
        def foo(){
            println "delegate"
        }
    }

    @Test
    def void delegatePerson() {
        println personDelegate{
            name.toUpperCase()
            foo()
        }
    }

    def personDelegate(@DelegatesTo(value = Person, strategy = Closure.OWNER_FIRST)  Closure cl) {
        cl.delegate = new Person('Paul')
        cl.call()
    }

    @Test
    def void delegateNumber() {
        println numbersDelegate {
            foo()
            foo1()
            numbers.sum()
        }
    }

    def numbersDelegate(@DelegatesTo(value = Numbers, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        cl.delegate = new Numbers()
        cl.call()
    }

    def foo() {
        println "owner"
    }

    def foo1() {
        println "owner1"
    }

}
