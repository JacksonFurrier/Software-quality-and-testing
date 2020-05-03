public class MathApplication {
  private CalculatorService mCalcService;

  public void setCalculatorService(CalculatorService aCalcService) {
    this.mCalcService = aCalcService;
  }

  public double add(double aLeft, double aRight) {
    return mCalcService.add(aLeft, aRight);
  }

  public double subtract(double aLeft, double aRight) {
    return mCalcService.subtract(aLeft, aRight);
  }

  public double multiply(double aLeft, double aRight) {
    return mCalcService.multiply(aLeft, aRight);
  }

  public double divide(double aLeft, double aRight) {
    return mCalcService.divide(aLeft, aRight);
  }
}