package uk.ac.manchester.cs.m84556jh.particle;

import uk.ac.manchester.cs.m84556jh.colour.Col;

public class Particle {
	private int radius;
	private double xPos, yPos;
	private double xVel, yVel;
	private int lt = 500;
	private Col colour;
	
	public Particle(int r, int initX, int initY, int emitAngleDeg, Col col, int velMag) {
		radius = r;
		xPos = initX;
		yPos = initY;
		xVel = velMag * Math.sin(Math.toRadians(emitAngleDeg));
		yVel = velMag * Math.cos(Math.toRadians(emitAngleDeg));
		colour = col;
	}
	
	public void move() {
		xPos+=xVel;
		yPos+=yVel;
		lt--;
	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	public int getR() {
		return radius;
	}
	
	public int getLifetime() {
		return lt;
	}
	
	public Col getColour() {
		return colour;
	}
}
