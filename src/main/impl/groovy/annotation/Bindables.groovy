package impl.groovy.annotation

import groovy.beans.Bindable
import org.junit.Test

/**
 * Bindable annotation allow us provide a listener into class or attribute level that allow us for instance
 * detect when the class/attributes are modified, invoking the callback that we define in addPropertyChangeListener
 */
class Bindables {

    @Bindable
    class User {
        String name, email
    }

    @Test
    def void testBindables() {
        def u = new User(name: 'Pablo', email: 'pablo@gmail.com')

        u.addPropertyChangeListener { event ->
            println "Changed property $event.propertyName from $event.oldValue to $event.newValue"
        }

        u.name = 'Mr Nobody'
        // Output: Changed property name from Pablo to Mr Nobody

        u.email = 'nobody@gmail.com.com'
        // Output: Changed property email from pablo@gmail.com to nobody@gmail.com.com

    }

}
