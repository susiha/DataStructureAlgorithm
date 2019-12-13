package com.susiha.concept;

import com.susiha.Utils.BaseUtils;
import com.susiha.datastructure.base.LinkedList;
import com.susiha.datastructure.stack.Stack;
import com.susiha.datastructure.stack.StackByArray;

/**
 * 算术表达式
 * 数据和运算符的存储有三种方式
 * 1 中序
 * 2 前序
 * 2 后续
 */
public class Expression {


    public static final char add = '+';
    public static final char reduce = '-';
    public static final char mult = '*';
    public static final char divede = '/';
    public static final char remainder = '%';
    public static final char left_bracket = '(';
    public static final char right_bracket = ')';

    /**
     * 中序表达式
     * 这是最习惯的表示方式
     * <操作数1><运算符><操作数2>
     * 比如3+5，5*6..
     */
    public static void infix(String expression) {
//        String expression = "3+5*6+7*8+4*(1+2)";
//        String expression = "3+5%6+7*8+4*(1+2*(3-2))";

        //运算符栈
        StackByArray<Character> operators = new StackByArray<>();
        //操作数栈
        StackByArray<Integer> operands = new StackByArray<>();
        //表达式不合法
        if (expression == null || expression.equals("")) {
            throw new IllegalArgumentException("表达式不合法");
        }
        StringBuffer sb = new StringBuffer();


        for (int i = 0; i < expression.length(); i++) {
            //取出字符
            char c = expression.charAt(i);
            if (isOperator(c)) { //如果是运算符
                //首先判断符号是不是()
                if(c==left_bracket){ //左括号
                    //如果遇到左括号放入栈中
                    operators.push(c);
                }else if(c == right_bracket){ //右括号
                    //如果是右括号的话直接把括号里面的值全部计算出来

                    while(true){
                        //找到对应的左括号
                        if(operators.peek()==left_bracket){
                            operators.pop();
                            break;
                        }
                        if(sb.length()==0){
                            int lastOperant =operands.pop();
                            operands.push(operation(operands.pop(),operators.pop(),lastOperant));
                        }else{
                            operands.push(operation(operands.pop(),operators.pop(),Integer.parseInt(sb.toString())));
                            sb.setLength(0);
                        }

                    }
                }else {
                    if(!operators.isEmpty()){
                        //如果栈顶元素是左括号 直接把运算符放入栈中
                        if(operators.peek()==left_bracket){
                            operators.push(c);
                            int operand = Integer.parseInt(sb.toString());
                            operands.push(operand);
                        }else{
                            /**
                             * 判断栈顶元素的优先级
                             * 如果栈顶的元素优先级比较高先计算然后再把结果压入操作数栈中
                             */
                            if(isHighPriority(operators.peek())>=isHighPriority(c)){
                                //计算结果压入栈中
                                if(sb.length()==0){
                                    int lastOperant =operands.pop();
                                    operands.push(operation(operands.pop(),operators.pop(),lastOperant));
                                }else{
                                    operands.push(operation(operands.pop(),operators.pop(),Integer.parseInt(sb.toString())));
                                    sb.setLength(0);
                                }
                                operators.push(c);

                            }else{ //否则的话 直接把运算符入栈
                                operators.push(c);
                                if(sb.length()!=0){
                                    int operand = Integer.parseInt(sb.toString());
                                    operands.push(operand);
                                }

                            }
                        }
                    }else{
                        operators.push(c);
                        int operand = Integer.parseInt(sb.toString());
                        operands.push(operand);
                    }
                }

                BaseUtils.println("操作数栈 :"+operands.toString());
                BaseUtils.println("运算符栈 :"+operators.toString());
                //清空
                sb.setLength(0);
            } else {
                sb.append(c);
            }

        }

        BaseUtils.println("---------------分割线-------------------");
        while(!operators.isEmpty()){

            //如果是左右括号 直接出栈
            if(operators.peek() == left_bracket||operators.peek()==right_bracket){
                operators.pop();
            }else{
                int lastOperand = operands.pop();
                int firstOperand = operands.pop();
                //把计算结果再重新放入栈中
                operands.push(operation(firstOperand,operators.pop(),lastOperand));
            }

            BaseUtils.println("操作数栈 :"+operands.toString());
            BaseUtils.println("运算符栈 :"+operators.toString());
        }


    }


    /**
     * 前序表达式
     * <运算符><操作数1><操作数2>
     * 比如2+3 在前序表达式中就表示为+23
     * 2+3*5+4
     * 在前序表示中就表示为
     * ++2*354
     *
     */
    public static void prefix(String expression){
//        String expression = "3+5%6+7*8+4*(1+2*(3-2))";
        LinkedList<String> result = infixToPrefix(expression);
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<result.getSize();i++){
            sb.append(result.get(i));
        }
        BaseUtils.println("中序表达式为："+expression);
        BaseUtils.println("转换为前序表达式为："+sb.toString());


        //用前序表达式的计算对应的值
        StackByArray<Integer> resultValue = new StackByArray<>();
        while(!result.isEmpty()){

           String value = result.removeLast();
           char c;
           if(value.length()==1&&isOperator(c =value.charAt(0))){ //是操作符
               int first = resultValue.pop();
               resultValue.push(operation(first,c,resultValue.pop()));

           }else{
               resultValue.push(Integer.parseInt(value));
           }

            BaseUtils.println("源 :"+result.toString());
            BaseUtils.println("运算结果 :"+resultValue.toString());
        }
    }

    /**
     * 后序表达式
     * <操作数1><操作数2><运算符>
     * 如中序表达式是 2+3
     * 后序表达式就是 23+
     */
    public static void postfix(String expression){


        LinkedList<String> result = infixToPostfix(expression);
        StringBuffer sb = new StringBuffer();
        for(int i =0;i<result.getSize();i++){
            sb.append(result.get(i));
        }

        BaseUtils.println("中序表达式为 ："+expression);
        BaseUtils.println("转换为后序表达式为 ："+sb.toString());



        StackByArray<Integer> stack = new StackByArray<>();
        while(!result.isEmpty()){
            String value = result.removeFirst();
            char c;
            if(value.length()==1&&isOperator(c = value.charAt(0))){
               int lastOperand = stack.pop();
                stack.push(operation(stack.pop(),c,lastOperand));

            }else{
                stack.push(Integer.parseInt(value));
            }

            BaseUtils.println("栈的结果 ："+stack.toString());
        }
    }


    /**
     * 中序表达式转后续表达式
     *
     * @param expression
     * @return
     */
    private static LinkedList<String> infixToPostfix(String expression){
        //同前序表达式一样 也需要借助LinkedList
        LinkedList<String> storedExpression = stroedList(expression);
        LinkedList<String> result = new LinkedList<>();
        StackByArray<Character> operators = new StackByArray();
        while(!storedExpression.isEmpty()){

            String value = storedExpression.removeFirst();
            char c;
            if(value.length()==1&&isOperator(c = value.charAt(0))){
                if(c == left_bracket){
                    operators.push(c);
                }else if(c==right_bracket){

                    while(!operators.isEmpty()){

                        if(operators.peek() == left_bracket){
                            operators.pop();
                            break;
                        }
                        result.addLast(String.valueOf(operators.pop()));
                    }
                }else{
                    while(true){
                        //如果栈为空，则把该运算符入栈，并跳出while循环
                        if(operators.isEmpty()){
                            operators.push(c);
                            break;
                            //判断如果栈顶元素是右括号 跳出while循环
                        }else if(operators.peek() == left_bracket){
                            operators.push(c);
                            break;
                            //如果运算符的优先级高于栈顶元素的优先级 就入栈结束循环
                        }else if(isHighPriority(operators.peek())<isHighPriority(c)){
                            operators.push(c);
                            break;
                        }
                        //一直出栈 直到遇到上面三种情况
                        result.addLast(operators.pop().toString());
                    }
                }
            }else{
                //如果是
                result.addLast(value);
            }
        }

        //把剩余的运算符添加到结果中
        while(!operators.isEmpty()){
            result.addLast(String.valueOf(operators.pop()));
        }
        return result;
    }





    /**
     *
     * 中序表达式转前序表达式 借助LinkedList
     * 这是因为如果如果是操作数23只用字符串反转的话变成了32 出现了错误
     * 这里使用LinkedList的removeLast 相当于先把表达式反转
     * 在接受的时候使用另一个LinkedList的addFirst相当于把结果又反转了一次
     * 这样实现了中序转前序的两次反转效果
     * 步骤：
     * 1 将中序表达式反转，依次取出对应的字符
     * 2 如果是是运算符
     *   2.1 如果是"(" 直接放入运算符操作数栈
     *   2.2 如果是 "）"把运算符栈依次出栈并把出栈的运算符添加到结果中直到找到"（"为止，并把"（"出栈并遗弃掉
     *   2.3 如果不是"（"也不是"）"则把运算符栈依次出栈并添加到结果中，遇到以下三种情况终止出栈：
     *      2.3.1 如果栈为空 把当前运算符入栈 并跳出循环
     *      2.3.2 如果栈顶元素是"("把当前运算符入栈 并跳出循环
     *      2.3.3 如果当前运算符的优先级大于或等于栈顶元素的优先级 把当前运算符入栈 并跳出循环
     * 3 如果不是运算符 直接把放入结果中
     */
    private static LinkedList<String> infixToPrefix(String infix){
        //存储过的表达式 作为源 所有操作都在这上进行操作
        LinkedList<String> source = stroedList(infix);
        //存储最终结果
        LinkedList<String> result = new LinkedList<>();
        //存储运算符栈
        StackByArray<Character> operators = new StackByArray();

        while (!source.isEmpty()){
            String i = source.removeLast();
            char c;
            //表示是操作符
            if(i.length()==1&&isOperator( c = i.charAt(0))){
                //如果是右括号直接先入栈
                if(c == right_bracket){
                    operators.push(c);
                }else{
                  if(c!=left_bracket){ //如果不是左括号
                      while(true){
                          //如果栈为空，则把该运算符入栈，并跳出while循环
                          if(operators.isEmpty()){
                              operators.push(c);
                              break;
                              //判断如果栈顶元素是右括号 跳出while循环
                          }else if(operators.peek() == right_bracket){
                              operators.push(c);
                              break;
                              //如果运算符的优先级高于栈顶元素的优先级 就入栈结束循环
                          }else if(isHighPriority(operators.peek())<isHighPriority(c)){
                              operators.push(c);
                              break;
                          }
                          //一直出栈 直到遇到上面三种情况
                          result.addFirst(operators.pop().toString());
                      }
                    }else{ //如果是左括号

                      while(!operators.isEmpty()){
                          if(operators.peek()==right_bracket){
                              //把右括号出栈遗弃掉
                              operators.pop();
                              break;
                          }else{
                              //一直出栈直到遇到右括号为止并把运算符添加到结果中
                              result.addFirst(operators.pop().toString());
                          }
                      }
                    }
                }
            }else{
                //因为addFirst的话就不用后续反转了
                result.addFirst(i);
            }
        }
        //最后把运算符中的栈全部都添加到结果中
        while(!operators.isEmpty()){
            result.addFirst(operators.pop().toString());
        }
        return result;
    }


    /**
     * 把字符串存储为LinkedList
     * @param infix
     * @return
     */
    private static LinkedList<String> stroedList(String infix){
        LinkedList<String> list = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<infix.length();i++){
            char c = infix.charAt(i);
                if(isOperator(c)){
                    //把数据添加进来
                    if(sb.length()!=0){
                        list.addLast(sb.toString());
                    }
                    //把运算符添加新来
                    list.addLast(String.valueOf(c));
                    //数据清空
                    sb.setLength(0);
                }else{
                    if(i==infix.length()-1){ //最后一个操作数
                        sb.append(c);
                        list.addLast(sb.toString());
                    }else{
                        sb.append(c);
                    }
                }
            }

        return list;
    }


    private static boolean isOperator(char c) {
        if (c == add || c == reduce || c == mult || c == divede ||
                c == remainder || c == left_bracket || c == right_bracket) {
            return true;
        }
        return false;
    }


    /**
     * 新的操作符是否比旧的操作符的有更高的优先级
     *
     * @param oldOperator
     * @return
     */
    private static int isHighPriority(char oldOperator) {
        if(oldOperator == left_bracket){
            return 1;
        }
        if (oldOperator == add || oldOperator == reduce) {
            return 2;
        }

        if (oldOperator == mult || oldOperator == divede || oldOperator == remainder) {
            return 3;
        }
        return 1;
    }

    /**
     * 具体实际的运算
     * @param operand1
     * @param operator
     * @param operand2
     * @return
     */
    private static int operation(int operand1, char operator, int operand2) {
        switch (operator) {
            case add:
                return operand1 + operand2;
            case reduce:
                return operand1 - operand2;
            case mult:
                return operand1 * operand2;
            case divede:
                return operand1 / operand2;
            case remainder:
                return operand1 % operand2;
            default:
                throw new IllegalArgumentException("操作数错误");
        }
    }
}
