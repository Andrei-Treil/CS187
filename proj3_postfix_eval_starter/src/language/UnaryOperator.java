package language;


public abstract class UnaryOperator<T> implements Operator<T>{
    protected Operand<T> op0;

    @Override
    public final int getNumberOfArguments() {
        return 1;
    }

    @Override
    public void setOperand(int i, Operand<T> operand){
        if (operand == null) throw new NullPointerException("Could not set null operand.");
        if(i > 0){
            throw new IllegalArgumentException("Binary operator only accepts operands 0 but recieved " + i + ".");
        }
        if(i == 0){
            if (op0 != null){
              throw new IllegalStateException("Position " + i + " has been previously set.");
            }
            op0 = operand;
          }
    }
}
