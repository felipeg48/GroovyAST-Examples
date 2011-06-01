package json

import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.*
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.ast.builder.AstBuilder
import xml.ToXml


@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class ToJsonTransformation implements ASTTransformation {

    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {

        if (!astNodes ) return
        if (!astNodes[0] || !astNodes[1]) return
        if (!(astNodes[0] instanceof AnnotationNode)) return
        if (astNodes[0].classNode?.name != ToJson.class.name) return

        def toJsonMethods = makeToJsonMethod(astNodes[1])

        astNodes[1].addMethod(toJsonMethods.find { it.name == 'toString' })
        astNodes[1].addMethod(toJsonMethods.find { it.name == 'createInstanceFrom' })


    }

    def makeToJsonMethod(ClassNode source) {
        def phase = CompilePhase.INSTRUCTION_SELECTION

        def ast = new AstBuilder().buildFromString(phase, false, """
                package ${source.packageName}

                import com.thoughtworks.xstream.XStream
                import com.thoughtworks.xstream.annotations.XStreamAlias
                import com.thoughtworks.xstream.annotations.XStreamImplicit
                import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver
                import com.thoughtworks.xstream.io.xml.DomDriver

                class ${source.nameWithoutPackage} {
                    String toString(){
                        XStream xstream = new XStream(new JettisonMappedXmlDriver());
                        xstream.setMode(XStream.NO_REFERENCES);
                        xstream.alias("${source.nameWithoutPackage.toLowerCase()}", ${source.nameWithoutPackage}.class)
		                xstream.processAnnotations([${source.nameWithoutPackage}.class] as Class[])
		                xstream.autodetectAnnotations(true)
		                xstream.toXML(this)
                    }

                    static def createInstanceFrom(xml){
                        XStream xstream = new XStream(new JettisonMappedXmlDriver())
                        xstream.alias("${source.nameWithoutPackage.toLowerCase()}", ${source.nameWithoutPackage}.class)
		                xstream.processAnnotations([${source.nameWithoutPackage}.class] as Class[])
		                xstream.autodetectAnnotations(true)
		                xstream.fromXML(xml)
                    }
                }
                """)

       ast[1].methods
    }
}
