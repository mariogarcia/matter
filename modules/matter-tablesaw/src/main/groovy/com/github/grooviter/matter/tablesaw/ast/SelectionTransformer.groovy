package com.github.grooviter.matter.tablesaw.ast

import groovy.transform.TupleConstructor
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.BinaryExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.macro.matcher.ASTMatcher
import org.codehaus.groovy.syntax.Types
import tech.tablesaw.api.NumberColumn

import java.time.LocalDateTime

import static org.codehaus.groovy.ast.tools.GeneralUtils.args
import static org.codehaus.groovy.ast.tools.GeneralUtils.callX


@TupleConstructor
class SelectionTransformer extends ClassCodeExpressionTransformer {
    SourceUnit sourceUnit

    @Override
    Expression transform(Expression expr) {
        if (expr == null) {
            return null
        }

        if (isExpression(expr)) {
            BinaryExpression binaryExpression = expr as BinaryExpression
            // leftExpression => table[column]
            BinaryExpression getAtX = binaryExpression.leftExpression as BinaryExpression
            VariableExpression varX = getAtX.leftExpression as VariableExpression
            ConstantExpression colX = getAtX.rightExpression as ConstantExpression

            def column = callX(varX, "column", args(colX))

            // rightExpression => value
            //ConstantExpression valueX = binaryExpression.rightExpression as ConstantExpression


            //return callX(column, resolveTokenMethod(binaryExpression.operation.type), args(valueX))
            println "===========================>${binaryExpression.rightExpression}"
            ClassNode rightExpressionType = resolveArgumentToCompareToClassNode(binaryExpression.rightExpression)
            return callX(column, resolveToken(binaryExpression.operation.type), args(binaryExpression.rightExpression))
        }

        return expr.transformExpression(this)
    }

    private static ClassNode resolveArgumentToCompareToClassNode(Expression expression) {
        if (expression instanceof MethodCallExpression) {
            return expression.objectExpression.type
        }
        return expression.type
    }

    private static String resolveToken(int token){
        switch(token) {
            case Types.COMPARE_LESS_THAN_EQUAL: return "isLessThanOrEqualTo"
            case Types.COMPARE_LESS_THAN: return "isLessThan"
            case Types.COMPARE_GREATER_THAN: return "isGreaterThan"
            case Types.COMPARE_GREATER_THAN_EQUAL: return "isGreaterThanOrEqualTo"
            case Types.COMPARE_EQUAL: return "isEqualTo"
            case Types.COMPARE_NOT_EQUAL: return "isNotEqualTo"
            case Types.KEYWORD_IN: return "inList"
        }
    }

    private static isExpression(ASTNode node){
        ASTNode pattern = ASTMatcher.withConstraints(macro { table[column] > value }){
            placeholder("table", "column", "value")
            token {
                type in [
                    Types.COMPARE_GREATER_THAN,
                    Types.COMPARE_GREATER_THAN_EQUAL,
                    Types.COMPARE_LESS_THAN,
                    Types.COMPARE_LESS_THAN_EQUAL,
                    Types.COMPARE_EQUAL,
                    Types.COMPARE_NOT_EQUAL,
                    Types.KEYWORD_IN
                ]
            }
        }

        return ASTMatcher.matches(node, pattern)
    }
}
