package impl.groovy.annotation

import groovy.transform.builder.Builder
import org.junit.Test

/**
 * Created by pabloperezgarcia on 23/10/2016.
 */
class BuilderPatter {

    @Builder(builderMethodName = 'initiator', buildMethodName = 'create')
    class Message {
        String from, to, subject, body
    }

    @Test
    def void testBuilder(){
        def message = Message.initiator()
                .from('mrhaki@mrhaki.com')
                .body('Groovy rocks!')
                .create()

        assert message.body == 'Groovy rocks!'
        assert message.from == 'mrhaki@mrhaki.com'
    }

}
