package xml

import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.*
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.ast.builder.AstBuilder


@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class ToXmlTransformation implements ASTTransformation {

    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {

        if (!astNodes ) return
        if (!astNodes[0] || !astNodes[1]) return
        if (!(astNodes[0] instanceof AnnotationNode)) return
        if (astNodes[0].classNode?.name != ToXml.class.name) return

        def toXmlMethods = makeToXmlMethod(astNodes[1])
        astNodes[1].addMethod(toXmlMethods.find { it.name == 'toString' })
        astNodes[1].addMethod(toXmlMethods.find { it.name == 'createInstanceFrom' })


    }

    def makeToXmlMethod(ClassNode source) {
        def phase = CompilePhase.INSTRUCTION_SELECTION

        def ast = new AstBuilder().buildFromString(phase, false, """
                package ${source.packageName}

                import com.thoughtworks.xstream.XStream
                import com.thoughtworks.xstream.annotations.XStreamAlias
                import com.thoughtworks.xstream.annotations.XStreamAsAttribute
                import com.thoughtworks.xstream.annotations.XStreamImplicit
                import com.thoughtworks.xstream.io.xml.DomDriver

                class ${source.nameWithoutPackage} {
                    String toString(){
                        XStream xstream = new XStream()
                        xstream.alias("${source.nameWithoutPackage.toLowerCase()}", ${source.nameWithoutPackage}.class)
		                xstream.processAnnotations([${source.nameWithoutPackage}.class] as Class[])
		                xstream.autodetectAnnotations(true)
		                xstream.toXML(this)
                    }

                    static def createInstanceFrom(xml){
                        XStream xstream = new XStream(new DomDriver())
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
