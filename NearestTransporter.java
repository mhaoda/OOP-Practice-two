/*
 * This is the nearest transporter, which to transport foods from the closest 
 * farmer to the closest animal.
 */
public class NearestTransporter extends AbstractItem {
	int stock, num;
	int conx = 0, cony = 0;

	public NearestTransporter(Grid grid, int xCoordinate, int yCoordinate, int num) {
		super(grid, xCoordinate, yCoordinate);
		this.num = num;
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	public int getNum() {
		return num;
	}

	public NearestTransporter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getConx() {
		return conx;
	}

	public void setConx(int conx) {
		this.conx = conx;
	}

	public int getCony() {
		return cony;
	}

	public void setCony(int cony) {
		this.cony = cony;
	}

	@Override
	public String toString() {
		return "NT";
	}

	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		// initialise the length and the coordinate.
		int radishL = 999, cornL = 999, rabbitL = 999, beaverL = 999;
		int cornx = 0, corny = 0, radishx = 0, radishy = 0, rabbitx = 0, rabbity = 0, beaverx = 0, beavery = 0;
		
		for (int m = 0; m < grid.grid.length; m++) {
			for (int n = 0; n < grid.grid[m].length; n++) {
				// search the corn farmer in the grid, record the length and the coordinate.
				if (grid.grid[m][n] != null && grid.grid[m][n].getClass().equals(CornFarmer.class)) {
					cornL = Math.abs(m - xCoordinate) + Math.abs(n - yCoordinate);
					cornx = m;
					corny = n;
				}
				// search the radish farmer in the grid, record the length and the coordinate.
				if (grid.grid[m][n] != null && grid.grid[m][n].getClass().equals(RadishFarmer.class)) {
					radishL = Math.abs(m - xCoordinate) + Math.abs(n - yCoordinate);
					radishx = m;
					radishy = n;
				}
			}
		}

		for (int m = 0; m < grid.grid.length; m++) {
			for (int n = 0; n < grid.grid[m].length; n++) {
				// search the rabbit in the grid, record the length and the coordinate.
				if (grid.grid[m][n] != null && grid.grid[m][n].getClass().equals(Rabbit.class)) {
					rabbitL = Math.abs(m - xCoordinate) + Math.abs(n - yCoordinate);
					rabbitx = m;
					rabbity = n;
				}
				// search the corn beaver in the grid, record the length and the coordinate.
				if (grid.grid[m][n] != null && grid.grid[m][n].getClass().equals(Beaver.class)) {
					beaverL = Math.abs(m - xCoordinate) + Math.abs(n - yCoordinate);
					beaverx = m;
					beavery = n;
				}
			}
		}
		
		if (cornL != radishL && rabbitL != beaverL) {
			// find the closest animal.
			if (cornL > radishL) {
				if (grid.grid[radishx][radishy].getStock() >= getNum()) {
					addToStock(getNum());
					grid.grid[radishx][radishy].reduceStock(getNum());
				} else {
					addToStock(grid.grid[radishx][radishy].getStock());
					grid.grid[radishx][radishy].reduceStock(grid.grid[radishx][radishy].getStock());
				}
			}
			// find the closest farmer.
			if (cornL < radishL) {
				if (grid.grid[cornx][corny].getStock() >= getNum()) {
					addToStock(getNum());
					grid.grid[cornx][corny].reduceStock(getNum());
				} else {
					addToStock(grid.grid[cornx][corny].getStock());
					grid.grid[cornx][corny].reduceStock(grid.grid[cornx][corny].getStock());
				}
			}
			// get the closest animal's coordinate.
			if (rabbitL > beaverL) {
				setConx(beaverx);
				setCony(beavery);
			}
			// get the closest farmer's coordinate.
			if (rabbitL < beaverL) {
				setConx(rabbitx);
				setCony(rabbity);
			}
		}
	}

	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return stock;
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		stock = stock + nutrition;
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		stock = stock - nutrition;
	}

}
