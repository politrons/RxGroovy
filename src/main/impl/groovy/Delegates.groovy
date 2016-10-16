package impl.groovy

import groovy.transform.CompileStatic
import org.junit.Test

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
@CompileStatic
class Delegates {

    def foo() {
        println "owner"
    }

    def onlyOnMain() {
        println "This method only is in the main instance so delegate cannot override it"
    }

    class Person {

        def onlyOnPerson() {
            println "This method only exist on delegate instance"
        }

        def foo() {
            println "delegate person"
        }
    }

    class Person1 {

        def foo() {
            println "delegate person1"
        }
    }

    /**
     * Delegate allow you passing the DelegatesTo annotation the class type where you will give the control of
     * your closure.
     * Once that you delegate your closure for that class, whatever invocation inside the closure it will executed
     * for the class instance that you assign.
     */
    @Test
    def void delegatePerson() {
        println "Owner:"
        personDelegateOwnerFirst {
            foo()
            onlyOnPerson()
        }
        println "Delegate 1:"
        personDelegateDelegateFirst {
            foo()
        }
        println "Delegate 2:"
        person1DelegateDelegateFirst {
            foo()
            onlyOnMain()
        }
    }

    /**
     * DelegateTo owner_first means that if the method exist in the owner class, then is the one that it will be invoked.
     * In case that the method in the closure does not exist in the owner class then it will be executed in the delegate class
     *
     * @param cl
     * @return
     */
    def personDelegateOwnerFirst(@DelegatesTo(value = Person, strategy = Closure.OWNER_FIRST) Closure cl) {
        cl.delegate = new Person()
        cl.call()
    }

    /**
     * DelegateTo delegate_first means that the method in the closure will be executed in the delegate class, and in case
     * that does not exist it will be executed in the owner class
     * @param cl
     * @return
     */
    def person1DelegateDelegateFirst(@DelegatesTo(value = Person1, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        cl.delegate = new Person1()
        cl.call()
    }

    def personDelegateDelegateFirst(@DelegatesTo(value = Person, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        cl.delegate = new Person()
        cl.call()
    }

    @Test
    def void delegateNumber() {
        println "Owner:"
        println numbersOwner {
            foo()
            onlyOnMain()
            numbers.sum()
        }
        println "Owner only:"
        println numbersOwnerOnly {
            foo()
            onlyOnMain()
//            numbers.sum()---> If the element is not part of the main class then it wont compile
        }
        println "Delegate:"
        println numbersDelegate {
            foo()
            onlyOnMain()
            numbers.sum()
        }
    }

    def numbersOwner(@DelegatesTo(value = Numbers, strategy = Closure.OWNER_FIRST) Closure cl) {
        cl.delegate = new Numbers()
        cl.call()
    }

    def numbersDelegate(@DelegatesTo(value = Numbers, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        cl.delegate = new Numbers()
        cl.call()
    }

    /**
     * DelegateTo owner_only means that the method in the closure will be executed only in the owner class, and if
     * does not exist in the owner class then it wont compile
     * @param cl
     * @return
     */
    def numbersOwnerOnly(@DelegatesTo(value = Numbers, strategy = Closure.OWNER_ONLY) Closure cl) {
        cl.delegate = new Numbers()
        cl.call()
    }

    class Numbers {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5)

        def foo() {
            println "delegate"
        }
    }


}
