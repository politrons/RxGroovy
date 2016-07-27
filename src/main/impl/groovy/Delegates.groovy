package impl.groovy

import groovy.transform.CompileStatic
import org.junit.Test
/**
 * Created by pabloperezgarcia on 27/7/16.
 */
@CompileStatic
class Delegates {

    static class Person {
        String name
    }

    class Numbers {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5)

        def foo(){
            println "delegate"
        }
    }

    @Test
    def void delegatePerson() {
        def person = new Person(name: 'Paul')
        println personDelegate(person){
            name.toUpperCase()
        }
    }

    def personDelegate(Person person,@DelegatesTo(value = Person, strategy = Closure.DELEGATE_FIRST)  Closure cl) {
        cl.delegate = person
        cl.call()
    }

    @Test
    def void delegateNumber() {
        println numbersDelegate {
            println owner.class
            println delegate.class
            foo()
            foo1()
            numbers.sum()
        }
    }

    def numbersDelegate(@DelegatesTo(value = Numbers, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        cl.delegate = new Numbers()
        cl.call()
    }

    static def foo() {
        println "owner"
    }

    static def foo1() {
        println "owner1"
    }

}
