package impl.groovy.annotation

import org.junit.Test

/**
 * Allow us use the same operator of Closure for different types
 * In this example we have different class types treated as the same type using
 * the same operator in the same collection
 */
class DynamicTyping {

    private static final List<?> INVENTORY = [
            new Car(), new Car(),
            new TV(), new TV(), new TV()
    ]

    private static class Car {
        public final int cost = 100
    }

    private static class TV {
        public final int cost = 50
    }


    public static int sumDynamic() {
        return INVENTORY.sum { it.cost } as int
    }

    @Test
    def void compileDynamicTest() {
        println sumDynamic()
    }


}
