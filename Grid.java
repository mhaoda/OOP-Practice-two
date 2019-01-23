/*
 * This is the Grid class, extends the AbstractGrid class 
 */
public class Grid extends AbstractGrid {
	int width, height;
	int production;
	int consumption;

	public Grid(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		super.grid = new AbstractItem[width][height];

		stock = new int[width][height];
	}

	public int getProduction() {
		return production;
	}

	public void setProduction(int production) {
		this.production = production;
	}

	public int getConsumption() {
		return consumption;
	}

	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
		// TODO Auto-generated method stub
		/**
		 * Return the item at row i and column j Returns null if there isn't an item at
		 * the specified location Also returns null for out-of-bounds queries
		 *
		 * @param xCoordinate the row number
		 * @param yCoordinate the column number
		 * @return The item that is stored at the specified grid cell, null otherwise
		 */
		grid[xCoordinate][yCoordinate] = item;

	}

	@Override
	public AbstractItem getItem(int xCoordinate, int yCoordinate) {
		/**
		 * Return the item at row i and column j Returns null if there isn't an item at
		 * the specified location Also returns null for out-of-bounds queries
		 *
		 * @param xCoordinate the row number
		 * @param yCoordinate the column number
		 * @return The item that is stored at the specified grid cell, null otherwise
		 */
		// TODO Auto-generated method stub
		try {
			if (grid[xCoordinate][yCoordinate] == null) {
				return null;
			} else {
				return grid[xCoordinate][yCoordinate];
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public int getStockAt(int xCoordinate, int yCoordinate) {
		/**
		 * The stock (in terms of units of nutrition at the specified location.
		 *
		 * @param xCoordinate the row number
		 * @param yCoordinate the column number
		 * @return The stock at the specified location
		 */
		// TODO Auto-generated method stub
		return stock[xCoordinate][yCoordinate];
	}

	@Override
	public void emptyStockAt(int xCoordinate, int yCoordinate) {
		/**
		 * Clear the stock at the specified location. i.e. set it to 0. This is
		 * equivalent to setStockAt(x,y,0)
		 *
		 * @param xCoordinate the row number
		 * @param yCoordinate the column number
		 */
		// TODO Auto-generated method stub
		setStockAt(xCoordinate, yCoordinate, 0);
	}

	@Override
	public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		/**
		 * Add the specified amount to the stock value stored at the specified location
		 *
		 * @param xCoordinate the row number
		 * @param yCoordinate the column number
		 * @param nutrition   the amount
		 */
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] = stock[xCoordinate][yCoordinate] + nutrition;

	}

	@Override
	public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		/**
		 * Reduce the stock at the specified location by the given amount
		 *
		 * @param xCoordinate the row number
		 * @param yCoordinate the column number
		 * @param nutrition   the amount
		 */
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] = stock[xCoordinate][yCoordinate] - nutrition;
	}

	@Override
	public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		/**
		 * Update the stock value at the specified location. Methods like emptyStockAt,
		 * addToStockAt and reduceStockAt may be easier to use for specific use cases
		 *
		 * @param xCoordinate the row number
		 * @param yCoordinate the column number
		 * @param nutrition   the amount
		 */
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] = nutrition;
	}

	@Override
	public void processItems(TimeStep timeStep) {
		/**
		 * Process all items. First all farmers, then all transporters, then all
		 * consumers.
		 *
		 * @param timeStep The time step we are at. This value may be used to determine
		 *                 production frequency of farmers.
		 */
		// TODO Auto-generated method stub
		// process farmers.
		for (int m = 0; m < grid.length; m++) {
			for (int n = 0; n < grid[m].length; n++) {
				if (grid[m][n] != null) {
					if (grid[m][n].getClass().equals(CornFarmer.class)
							|| grid[m][n].getClass().equals(RadishFarmer.class)) {
						grid[m][n].process(timeStep);
					}
				}
			}
		}
		// process transporters.
		for (int q = 0; q < grid.length; q++) {
			if (grid[q] != null) {
				for (int n = 0; n < grid[0].length; n++) {
					if (grid[q][n] != null) {
						if (grid[q][n].getClass().equals(HorizontalTransporter.class)
								|| grid[q][n].getClass().equals(VerticalTransporter.class)
								|| grid[q][n].getClass().equals(NearestTransporter.class)) {
							grid[q][n].process(timeStep);
						}
					}
				}
			}
		}
		// process animals.
		for (int e = 0; e < grid.length; e++) {
			for (int n = 0; n < grid[0].length; n++) {
				if (grid[e][n] != null) {
					if (grid[e][n].getClass().equals(Beaver.class) || grid[e][n].getClass().equals(Rabbit.class)) {
						grid[e][n].process(timeStep);
					}
				}
			}
		}

	}

	@Override
	public void recordProduction(int nutrition) {
		/**
		 * After every production by every farmer we record the amount of nutrition that
		 * is produced, so we can maintain statistics.
		 *
		 * @param nutrition the amount
		 */
		// TODO Auto-generated method stub
		production = production + nutrition;
	}

	@Override
	public int getTotalProduction() {
		// TODO Auto-generated method stub
		return production;
	}

	@Override
	public void recordConsumption(int nutrition) {
		// TODO Auto-generated method stub
		consumption = consumption + nutrition;

	}

	@Override
	public int getTotalConsumption() {
		// TODO Auto-generated method stub
		return consumption;
	}

}
