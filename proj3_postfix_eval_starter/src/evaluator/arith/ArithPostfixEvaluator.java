package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/** An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions. */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stack;

  /** Constructs an {@link ArithPostfixEvaluator} */
  public ArithPostfixEvaluator() {
    // TODO Initialize to your LinkedStack
    stack = new LinkedStack<Operand<Integer>>();
    
  }

  int operatorCount = 0;

  /** {@inheritDoc} */
  @Override
  public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
    ArithPostfixParser parser = new ArithPostfixParser(expr);
    for (Token<Integer> token : parser) {
      Type type = token.getType();
      switch (type) {
        case OPERAND:
          stack.push(token.getOperand());
          // TODO What do we do when we see an operand?
          break;
        case OPERATOR:
          if(token.getOperator().getNumberOfArguments() == 2){
            token.getOperator().setOperand(1, stack.pop());
            token.getOperator().setOperand(0, stack.pop());
            stack.push(token.getOperator().performOperation());
            operatorCount++;
          }
          else{
            token.getOperator().setOperand(0, stack.pop());
            stack.push(token.getOperator().performOperation());
            operatorCount++;
          }

          // TODO What do we do when we see an operator?
          break;
        default:
          throw new IllegalStateException("Parser returned an invalid Type: " + type);
      }
    }
    int stackSize = stack.size();
    if(operatorCount == 0 && stackSize > 1){
      throw new IllegalPostfixExpressionException();
    }
    // TODO What do we return?
    return stack.top().getValue();
  }
}
