package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args)	{
		hello("world");
		hello("User");
		hello("Vladimir");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point p1 = new Point(3,3);
		Point p2 = new Point(7,4);
		System.out.println("Растояние между точками p1 и p2 = " + distance(p1,p2));

		System.out.println("Растояние между точками p1 и p2 = " + p1.distance(p2));

	}

	public static void hello(String somebody){

		System.out.println("Hello, " + somebody + "!");
	}


	public static double distance(Point p1, Point p2){
		double p1p2 = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
		return p1p2;
	}

}