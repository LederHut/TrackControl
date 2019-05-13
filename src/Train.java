import java.util.ArrayList;

public class Train
{
	Grid gridref;
	
	public int[][] trainLocation = new int[3][2];
	public int[][] trainPath;
	public int trainID = 0;
	public int trainStartCol,
			   trainStartRow;
	public int trainEndCol,
			   trainEndRow;
	public int trainSteps = 0,
			   maxTrainSteps = 0;
	public int lastx = 0,
			   lasty = 0;
	public boolean isTurned = false;
	
	public Train(int trainid, Grid grid)
	{
		gridref = grid;
		trainID = trainid;
	}
	
	//A* algorithm
	public void pathfinder(int startx, int starty, int destx, int desty)
	{
		ArrayList<Vector2i> positions = new ArrayList<Vector2i>();
		ArrayList<Vector2i> finalpath = new ArrayList<Vector2i>();
		
		ArrayList<Tile> openlist = new ArrayList<Tile>();
		ArrayList<Tile> closedlist = new ArrayList<Tile>();
		
		Tile startTile = new Tile(new Vector2i(startx, starty), null, 0, 0);
		
		Vector2i end = new Vector2i(destx, desty);
		
		int[][][] gmd = gridref.getgridMetadata();
		
		int hz = 10;
		int md = ManhattanDist(startx, starty, destx, desty);
		
		//finds all the passable terrain
		for(int col = 0; col < Grid.maxCols; col++){
			for(int row = 0; row < Grid.maxRows; row++){
				if(gmd[col][row][0] > 2 && gmd[col][row][0] < 8)
				{
					positions.add(new Vector2i(col,row));
				}
			}
		}
		
		//finds all neighbors around the start point
		for(int i = 0; i < positions.size(); i++)
		{
			if(positions.get(i).y == trainStartRow -1 && positions.get(i).x == trainStartCol)
			{
				openlist.add(new Tile(positions.get(i), startTile, hz, md));
				positions.remove(i);
				i--;
			}
			else if(positions.get(i).y == trainStartRow +1 && positions.get(i).x == trainStartCol)
			{
				openlist.add(new Tile(positions.get(i), startTile, hz, md));
				positions.remove(i);
				i--;
			}
			else if(positions.get(i).x == trainStartCol -1 && positions.get(i).y == trainStartRow)
			{
				openlist.add(new Tile(positions.get(i), startTile, hz, md));
				positions.remove(i);
				i--;
			}
			else if(positions.get(i).x == trainStartCol +1 && positions.get(i).y == trainStartRow)
			{
				openlist.add(new Tile(positions.get(i), startTile, hz, md));
				positions.remove(i);
				i--;
			}
		}
		
		closedlist.add(startTile);
		
		//actual pathfinding algorithm
		while(closedlist.get(closedlist.size()-1).pos !=  end && !openlist.isEmpty())
		{
			Tile thistile = new Tile(null, null, 10000, 10000);
			
			for(Tile t : openlist)
			{
				if(t.F < thistile.F)
				{
					thistile = t;
				}
			}
			
			int thisx = thistile.pos.x,
				thisy = thistile.pos.y;
			
			int h = ManhattanDist(thisx, thisy, destx, desty);
			
			for(int i = 0; i < positions.size(); i++)
			{
				if(positions.get(i).y == thisy -1 && positions.get(i).x == thisx)
				{
					Tile t = new Tile(positions.get(i), thistile, hz + thistile.G, h);
					if(!openlist.contains(t))
					{
						openlist.add(t);
						positions.remove(i);
						i--;
					}
				}
				else if(positions.get(i).y == thisy +1 && positions.get(i).x == thisx)
				{
					Tile t = new Tile(positions.get(i), thistile, hz + thistile.G, h);
					if(!openlist.contains(t))
					{
						openlist.add(t);
						positions.remove(i);
						i--;
					}
				}
				else if(positions.get(i).x == thisx -1 && positions.get(i).y == thisy)
				{
					Tile t = new Tile(positions.get(i), thistile, hz + thistile.G, h);
					if(!openlist.contains(t))
					{
						openlist.add(t);
						positions.remove(i);
						i--;
					}
				}
				else if(positions.get(i).x == thisx +1 && positions.get(i).y == thisy)
				{
					Tile t = new Tile(positions.get(i), thistile, hz + thistile.G, h);
					if(!openlist.contains(t))
					{
						openlist.add(t);
						positions.remove(i);
						i--;
					}
				}
			}
			closedlist.add(thistile);
			openlist.remove(thistile);
		}
		
		Tile endTile = closedlist.get(closedlist.size()-1);
		
		while(endTile.parent != null)
		{
			finalpath.add(endTile.pos);
			endTile = endTile.parent;
		}
		
		trainPath = new int[finalpath.size()][2];
		
		for(int i = 0; i < finalpath.size(); i++)
		{
			trainPath[i][0] = finalpath.get(i).x;
			trainPath[i][1] = finalpath.get(i).y;
		}
		
		maxTrainSteps = finalpath.size();
	}
	private int ManhattanDist(int a, int b, int c, int d)
	{
		return (a-c) + (b-d);
	}
}
class Tile
{
	public Vector2i pos = new Vector2i(-1,-1);
	public Tile parent = null;
	int G = 0,
		H = 0,
		F = 0;
	
	public Tile()
	{
		
	}
	public Tile(Vector2i pos)
	{
		this.pos = pos;
	}
	public Tile(Vector2i pos, Tile parent, int g, int h)
	{
		this.pos = pos; 
		this.parent = parent;
		G = g;
		H = h;
		F = G + H;
	}
}
class Vector2i
{
	public int x = 0,
			   y = 0;
	public Vector2i()
	{
		
	}
	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
