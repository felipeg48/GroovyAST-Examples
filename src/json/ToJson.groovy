package json


import org.codehaus.groovy.transform.GroovyASTTransformationClass
import java.lang.annotation.*
import json.ToJsonTransformation

@Retention (RetentionPolicy.SOURCE)
@Target ([ElementType.TYPE])
@GroovyASTTransformationClass (["json.ToJsonTransformation"])
public @interface ToJson { }