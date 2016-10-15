package impl.groovy.delegate

import groovy.transform.CompileStatic
import org.junit.Test

/**
 * Created by pabloperezgarcia on 27/7/16.
 */
@CompileStatic
class Collection {

    /**
     * Delegate allow you passing the DelegatesTo annotation the class type where you will give the control of
     * your closure.
     * Once that you delegate your closure for that class, whatever invocation inside the closure it will executed
     * for the class instance that you assign.
     */
    @Test
    def void delegateList() {
        getList([1, 2, 3, 4, 5])
    }

    def getList(List<Integer> elements) {
        customList(elements) {
            printNumbers(elements)
        }
    }

    def printNumbers(List<Integer> elements) {
        elements.each { number ->
            println number
        }
    }

    /**
     * DelegateTo owner_first means that if the method exist in the owner class, then is the one that it will be invoked.
     * In case that the method in the closure does not exist in the owner class then it will be executed in the delegate class
     *
     * @param cl
     * @return
     */
    def customList(@DelegatesTo(value = ArrayList, strategy = Closure.DELEGATE_FIRST) List list, Closure cl) {
        cl.delegate = list
        cl.call()
    }


}
