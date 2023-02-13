package com.github.grooviter.matter.tablesaw.ast

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.ModuleNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@CompileStatic
@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class TablesawTransformation implements ASTTransformation {
    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        SelectionTransformer transformer = new SelectionTransformer(source)
        ModuleNode moduleNode = nodes.find() as ModuleNode

        moduleNode.classes.each { transformer.visitClass(it) }
    }
}
