package xml

import org.codehaus.groovy.transform.GroovyASTTransformationClass
import java.lang.annotation.*
import xml.ToXmlTransformation


@Retention (RetentionPolicy.SOURCE)
@Target ([ElementType.TYPE])
@GroovyASTTransformationClass (["xml.ToXmlTransformation"])
public @interface ToXml { }