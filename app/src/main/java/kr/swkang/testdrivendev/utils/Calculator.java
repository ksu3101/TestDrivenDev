package kr.swkang.testdrivendev.utils;

/**
 * @author KangSung-Woo
 * @since 2016-09-22
 */
public class Calculator {
  private int a;
  private int b;

  public Calculator() {
    this.a = 0;
    this.b = 0;
  }

  public int add(int a, int b) {
    this.a = a;
    this.b = b;
    return a + b;
  }

  public int minus(int a, int b) {
    this.a = a;
    this.b = b;
    return a - b;
  }

  public boolean isEqualNumber() {
    return a == b;
  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }
}
