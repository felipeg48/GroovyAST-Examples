package async

import org.codehaus.groovy.transform.GroovyASTTransformationClass
import java.lang.annotation.*
import xml.ToXmlTransformation


@Retention (RetentionPolicy.SOURCE)
@Target ([ElementType.TYPE])
@GroovyASTTransformationClass (["async.AsyncTransformation"])
public @interface Asynchronous {

}