package impl.groovy.annotation
import groovy.transform.CompileDynamic
import org.junit.Test

/**
 * Created by pabloperezgarcia on 15/10/2016.
 */
class DynamicTyping {

    private static final List<?> INVENTORY = [
            new Car(), new Car(),
            new TV(), new TV(), new TV()
    ]

    private static class Car {
        public final int cost = 10000
    }

    private static class TV {
        public final int cost = 1000
    }

    @Test
    def void compileDynamicTest(){
        println sumDynamic()
    }

    @CompileDynamic
    public static int sumDynamic() {
        return INVENTORY.sum { it.cost } as int
    }


}
