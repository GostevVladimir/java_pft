package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPointOne(){
    Point p1 = new Point(3, 3);
    Point p2 = new Point(7, 4);

    Assert.assertEquals(p1.distance(p2), 4.123105625617661);

  }
  @Test
  public void testPointTwo(){
    Point p1 = new Point(2, 3);
    Point p2 = new Point(8, 4);

    Assert.assertEquals(p1.distance(p2), 6.082762530298219);
  }
}
