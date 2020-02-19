package uk.ac.manchester.cs.m84556jh.particle;

import uk.ac.manchester.cs.m84556jh.colour.Col;

public class Particle {
	private int radius;
	private double xPos, yPos;
	private double xVel, yVel;
	private int lt;
	private Col colour;
	
	public Particle(int r, int initX, int initY, int emitAngleDeg, Col col, int velMag, int life) {
		radius = r;
		xPos = initX;
		yPos = initY;
		xVel = velMag * Math.sin(Math.toRadians(emitAngleDeg));
		yVel = velMag * Math.cos(Math.toRadians(emitAngleDeg));
		colour = col;
		lt = life;
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
