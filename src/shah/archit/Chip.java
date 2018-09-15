package shah.archit;

import java.awt.Color;

public class Chip {

	private Color chipColor;
	private int chipDiameter;

	public Chip(Color color, int chipDiameter) {
		this.chipColor = color;
		this.chipDiameter = chipDiameter;

	}

	public void setColor(Color color) {
		this.chipColor = color;
	}

	public Color getColor() {
		return chipColor;
	}

	public void setSize(int size) {

		this.chipDiameter = size;

	}

	public int getDiameter() {
		return chipDiameter;
	}

}