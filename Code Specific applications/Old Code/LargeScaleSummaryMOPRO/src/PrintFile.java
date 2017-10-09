import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

public class PrintFile {
	
	public void printer(String FileName, String PrintString) throws IOException 
	{
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( FileName));
		    writer.write( PrintString );

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}

}
