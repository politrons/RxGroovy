package impl.groovy

import org.junit.Test

/**
 * Created by pabloperezgarcia on 28/7/16.
 */
class Memorize {

    /**
     * Apply memorize operator over a Closure catch the execution of this one with the argument passed
     * In this example the first two invokation since we are passing the same argument the second call it´s cached
     * The third call since the argument is new the execution take the normal time since it´s not cached
     * shall print
     *
     *      exec_1:588
     *      exec_2:1
     *      exec_3:503
     *      exec_4:0
     */
    @Test
    def void memoize() {
        def start = System.currentTimeMillis();
        memoizeTest(1)
        println "exec_1:${System.currentTimeMillis() - start}"
        start = System.currentTimeMillis();
        memoizeTest(1)
        println "exec_2:${System.currentTimeMillis() - start}"
        start = System.currentTimeMillis();
        memoizeTest(2)
        println "exec_3:${System.currentTimeMillis() - start}"
        start = System.currentTimeMillis();
        memoizeTest(1)
        println "exec_4:${System.currentTimeMillis() - start}"
    }

    def memoizeTest = { n -> Thread.sleep(500) }.memoize()

    @Test
    def void testTrampoline() {
        tramp = tramp.trampoline()
        long freeMemory = Runtime.getRuntime().freeMemory()
        println tramp(1, freeMemory)
        println noTramp(1, freeMemory)
    }

    def tramp = { number, freeMemory ->
        if (number.intValue() == 100) {
            println "Memory Consumed:" + (Runtime.getRuntime().freeMemory())
            return number.toString()
        }
        number += 1
        tramp.trampoline(number, freeMemory)
    }

    def noTramp = { number, freeMemory ->
        if (number.intValue() == 100) {
            println "No Trampoline Memory Consumed:" + (Runtime.getRuntime().freeMemory())
            return number.toString()
        }
        number += 1
        noTramp(number, freeMemory)
    }


}
