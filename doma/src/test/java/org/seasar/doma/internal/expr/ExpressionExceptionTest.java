/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.internal.expr;

import org.seasar.doma.internal.expr.ExpressionEvaluator;
import org.seasar.doma.internal.expr.ExpressionException;
import org.seasar.doma.internal.expr.ExpressionParser;
import org.seasar.doma.internal.expr.node.ExpressionNode;
import org.seasar.doma.message.DomaMessageCode;

import junit.framework.TestCase;

/**
 * @author taedium
 * 
 */
public class ExpressionExceptionTest extends TestCase {

    public void testMethodInvocationFailed() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new java.util.ArrayList().get(0)");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3001, e.getMessageCode());
        }
    }

    public void testMethodNotFound() throws Exception {
        ExpressionParser parser = new ExpressionParser("\"aaa\".bbb");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3002, e.getMessageCode());
        }
    }

    public void testVariableUnresolvable() throws Exception {
        ExpressionParser parser = new ExpressionParser("aaa.eq(100)");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3003, e.getMessageCode());
        }
    }

    public void testDoubleQuotationNotClosed() throws Exception {
        ExpressionParser parser = new ExpressionParser("\"bbb\" == \"bbb");
        try {
            parser.parse();
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3004, e.getMessageCode());
        }
    }

    public void testClassNotFound() throws Exception {
        ExpressionParser parser = new ExpressionParser("new MyString()");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3005, e.getMessageCode());
        }
    }

    public void testConstructorNotFound() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new java.lang.String(10B)");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3006, e.getMessageCode());
        }
    }

    public void testConstructorInvocationFailed() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new java.util.ArrayList(-1)");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3007, e.getMessageCode());
        }
    }

    public void testComparisonFailed_incomparable() throws Exception {
        ExpressionParser parser = new ExpressionParser("1 > true");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3008, e.getMessageCode());
        }
    }

    public void testComparisonFailed_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("1 > null");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3009, e.getMessageCode());
        }
    }

    public void testOperandNotFound() throws Exception {
        ExpressionParser parser = new ExpressionParser("true &&");
        try {
            parser.parse();
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3010, e.getMessageCode());
        }
    }

    public void testUnsupportedTokenFound() throws Exception {
        ExpressionParser parser = new ExpressionParser("5 & 5");
        try {
            parser.parse();
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3011, e.getMessageCode());
        }
    }

    public void testDOMA3012() throws Exception {
        ExpressionParser parser = new ExpressionParser("5aaa");
        try {
            parser.parse();
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3012, e.getMessageCode());
        }
    }

    public void testOperandNotNumber() throws Exception {
        ExpressionParser parser = new ExpressionParser("5 + \"10\"");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3013, e.getMessageCode());
        }
    }

    public void testArithmeticOperationFailed() throws Exception {
        ExpressionParser parser = new ExpressionParser("5 / 0");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3014, e.getMessageCode());
        }
    }

    public void testArithmeticOperationFailed_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("5 / null");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(node);
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3015, e.getMessageCode());
        }
    }

    public void testQuotationNotClosed() throws Exception {
        ExpressionParser parser = new ExpressionParser(" 'aaa");
        try {
            parser.parse();
            fail();
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
            assertEquals(DomaMessageCode.DOMA3016, e.getMessageCode());
        }
    }
}
