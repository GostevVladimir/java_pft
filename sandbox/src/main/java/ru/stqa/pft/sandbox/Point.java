package ru.stqa.pft.sandbox;

public class Point {
  double x;
  double y;

  public  Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public  double distance(Point p2){
    double p1p2 = Math.sqrt((this.x-p2.x)*(this.x-p2.x) + (this.y-p2.y)*(this.y-p2.y));
    return p1p2;
  }
}
