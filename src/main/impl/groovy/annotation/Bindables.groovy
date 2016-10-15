package impl.groovy.annotation

import groovy.beans.Bindable
import org.junit.Test

/**
 * Created by pabloperezgarcia on 15/10/2016.
 */
class Bindables {


    @Bindable
    class User {
        String name, email
    }


    @Test
    def void testBindables() {
        def u = new User(name: 'mrhaki', email: 'mrhaki@mrhaki.com')
        // Since Groovy 2.2 we don't have to use the as keyword like
        // { ... } as PropertyChangeListener,
        // but we can rely on implicit coercion.
        u.addPropertyChangeListener { event ->
            println "Changed property $event.propertyName from $event.oldValue to $event.newValue"
        }

        u.name = 'Hubert A. Klein Ikkink'
        // Output: Changed property name from mrhaki to Hubert A. Klein Ikkink

        u.email = 'hubert@mrhaki.com'
        // Output: Changed property email from mrhaki@mrhaki.com to hubert@mrhaki.com

    }

}
