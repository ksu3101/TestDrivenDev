package kr.swkang.testdrivendev;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import kr.swkang.testdrivendev.utils.Calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author KangSung-Woo
 * @since 2016-09-22
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TestCalculator {
  private Calculator calculator;

  @Before
  public void initTest() {
    this.calculator = new Calculator();
  }

  @Test
  public void testAddNumbers() {
    int result = calculator.add(10, 20);
    assertThat(result, is(30));
  }

  @Test
  public void testMinusNumbers() {
    int result = calculator.minus(25, 10);
    assertThat(result, is(15));
  }

}
