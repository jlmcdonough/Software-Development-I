
//Patrick McNamara and Jospeh McDonough

//Sphere.java



import java.text.*;

public class Sphere extends Shape
{
	protected double radius;
	protected static DecimalFormat form = new DecimalFormat("0.##");


//  Sets up the radius
	public Sphere(double rad) 
	{
		radius = rad;
	}

//  Returns the value of radius 

	public double getRadius() 
	{
		return radius;
	}

//  Returns the calculated area

	public double computeArea() 
	{
		return 4 * (radius * radius) * Math.PI;
	}


//  Returns the calculated circumference

	public double computePerimeter() 
	{
		return 2 * radius * Math.PI;
	}


//  Returns the information about the Sphere

	public String toString() 
	{
		return "Sphere: radius is " + form.format(radius) + "\ncircumference is " + form.format(computePerimeter())
				+ ", area is " + form.format(computeArea());
	}
}
