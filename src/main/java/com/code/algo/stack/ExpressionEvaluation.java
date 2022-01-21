package com.code.algo.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 表达式求值，支持加、减、乘、除，以及括号
 * <p>利用两个栈，一个操作符栈，一个操作数栈：
 * 从左到右遍历表达式字符串，遇到操作符压入操作符栈，遇到数字压入操作数栈。
 * 当前操作符与栈顶操作符比较优先级，当前操作符优先级大于栈顶操作符，直接入栈；
 * 反之，栈顶操作符出栈，取出操作数栈2个数字进行计算，计算结果再压入操作数栈，继续比较。
 * 括号处理：当遇到左括号时，直接入栈；遇到右括号时，触发操作符出栈计算，出栈计算到第一次遇到左括号停止，并将遇到的左括号出栈。
 * 遍历结束后需要清空操作符栈（计算）
 * @author panjb
 */
public class ExpressionEvaluation {

    public static void main(String[] args) {
        String exp = "3+((3 * 8 + 16) - 7 + 7) / 4 - 2";
        int eva = ExpressionEvaluation.eva(exp);
        System.out.println("eva = " + eva);
    }

    public static int eva(String expression) {
        //操作数栈
        Deque<Integer> operandStack = new LinkedList<>();
        //操作符栈
        Deque<Operator> operatorStack = new LinkedList<>();
        expression = expression.replaceAll("\\s", "");
        int i = 0;
        while (i < expression.length()) {
            Operator operator = Operator.getInstance(expression.charAt(i));
            if (operator != null) {
                boolean isRightBracket = Operator.RIGHT_BRACKET.equals(operator);
                boolean isLeftBracket = Operator.LEFT_BRACKET.equals(operator);
                //当前操作符优先级小于等于栈顶操作符或当前操作符是右括号时，触发计算
                if (isTrigger(operatorStack, operator, isRightBracket, isLeftBracket)) {
                    triggerCal(operatorStack, operandStack, operator, isRightBracket);
                }
                //右括号不入栈，只触发计算
                if (!isRightBracket) {
                    operatorStack.push(operator);
                }
                i++;
            } else {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                operandStack.push(num);
            }
        }
        //清空操作符栈
        while (!operatorStack.isEmpty()) {
            doCal(operatorStack, operandStack);
        }
        return operandStack.pop();
    }

    private static boolean isTrigger(Deque<Operator> operatorStack, Operator operator, boolean isRightBracket, boolean isLeftBracket) {
        //当前操作符优先级小于等于栈顶操作符或当前操作符是右括号时，触发计算
        return isRightBracket || (!isLeftBracket && !operatorStack.isEmpty() && operator.priority <= operatorStack.peek().priority);
    }

    private static void triggerCal(Deque<Operator> operatorStack, Deque<Integer> operandStack, Operator operator, boolean isRightBracket) {
        //出栈结束：当前操作符优先级大于栈顶操作符或遇到左括号
        while (!operatorStack.isEmpty() && operatorStack.peek().priority >= operator.priority) {
            //当遇到左括号时，停止出栈，如果此时是右括号，需要将左括号出栈
            if (Operator.LEFT_BRACKET.equals(operatorStack.peek())) {
                if (isRightBracket) {
                    operatorStack.pop();
                }
                break;
            }
            doCal(operatorStack, operandStack);
        }
    }

    private static void doCal(Deque<Operator> operatorStack, Deque<Integer> operandStack) {
        Operator peek = operatorStack.pop();
        Integer b = operandStack.pop();
        Integer a = operandStack.pop();
        operandStack.push(peek.cal(a, b));
    }

    private interface CalculateAble {
        /**
         * 计算
         * @param a 操作数
         * @param b 操作数
         * @return 计算结果
         */
        int cal(int a, int b);
    }

    /**
     * 操作符
     */
    private enum Operator implements CalculateAble {
        /** 加法 */
        ADD(1, '+') {
            @Override
            public int cal(int a, int b) {
                return a + b;
            }
        },
        /** 减法 */
        SUBTRACT(1, '-') {
            @Override
            public int cal(int a, int b) {
                return a - b;
            }
        },
        /** 乘法 */
        MULTIPLY(2, '*') {
            @Override
            public int cal(int a, int b) {
                return a * b;
            }
        },
        /** 除法 */
        DIVIDE(2, '/') {
            @Override
            public int cal(int a, int b) {
                return a / b;
            }
        },

        LEFT_BRACKET(0, '('),

        RIGHT_BRACKET(0, ')');

        private final int priority;
        private final char op;

        Operator(int priority, char op) {
            this.priority = priority;
            this.op = op;
        }

        public static Operator getInstance(char op) {
            for (Operator value : Operator.values()) {
                if (value.op == op) {
                    return value;
                }
            }
            return null;
        }

        public int getPriority() {
            return priority;
        }


        @Override
        public int cal(int a, int b) {
            throw new UnsupportedOperationException();
        }
    }

}
