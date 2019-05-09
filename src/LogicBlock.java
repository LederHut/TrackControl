
import java.io.*;

public class LogicBlock
{
	public int[][] blockMetadata;
	public int blockID = 0;
	public boolean isUsed = false;
	
	LogicBlock (int size)
	{
		blockMetadata = new int[size][2];
	}
}