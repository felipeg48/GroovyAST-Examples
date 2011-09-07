package async


import org.codehaus.groovy.transform.GroovyASTTransformationClass
import java.lang.annotation.*
import async.AsyncTransformation

/**
 * Created by IntelliJ IDEA.
 * User: felipeg
 * Date: 9/6/11
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
@Retention (RetentionPolicy.SOURCE)
@Target ([ElementType.METHOD])
@GroovyASTTransformationClass (["async.AsyncTransformation"])
public @interface Async { }
