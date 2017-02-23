package util.rng.summon;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import Library.summon.UnitSpecific;

/**
 * Class to build the image used to display units, as it is now used in places other than the summon simulation command
 * @author Allen
 *
 */
public class SummonImageBuilder {
	//values in are defaults
	private int h=200;
	private int w=160;
	private int col=5;
	private int row;
	private ArrayList<UnitSpecific> units=new ArrayList<UnitSpecific>();
	private boolean dynamicRows=false;
	/**
	 * Creates a new summon image builder with parameters specified
	 * @param h height of the unit
	 * @param w width of the unit
	 */
	public SummonImageBuilder(int h, int w){
		this.h=h;
		this.w=w;
	}
	/**
	 * Specifies the number of columns for the units, if not specified defaults to 5, if built dynamically 
	 * then this parameter is ignored
	 * @param col number of columns
	 * @return
	 */
	public SummonImageBuilder setColumnSize(int col){
		this.col=col;
		return this;
	}
	/**
	 * Specifies the max number of rows for the units, if not specified it will dynamically determine the number of rows needed
	 * based on the column value provided
	 * @param row max rows
	 * @return
	 */
	public SummonImageBuilder setRowSize(int row){
		this.row=row;
		return this;
	}
	/**
	 * Builds the number of columns dynamically, if set to true it will try to keep the image such that it's shape will be a square
	 * ensures that the col num is floor of squareroot(#units)   
	 * @return
	 */
	public SummonImageBuilder buildColumnsDynamically(){
		dynamicRows=true;
		return this;
	}
	/**
	 * Adds the unit to the list of units to build the image for
	 * @return
	 */
	public SummonImageBuilder addUnit(UnitSpecific unit){
		units.add(unit);
		return this;
	}
	/**
	 * Adds the list of units to the list of units to build the image for
	 * @param units
	 * @return
	 */
	public SummonImageBuilder addUnit(Collection<UnitSpecific> units){
		units.addAll(units);
		return this;
	}
	/**
	 * Build the image with the settings specified, returns the image
	 * @return
	 */
	public BufferedImage build(){
		
		return null;
	}
}
