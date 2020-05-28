package dna;

import java.io.*;


//
// Reads lines from a BufferedReader and builds a FastqRecord.
//
public class FastqReader
{
	private BufferedReader theBufferedReader;
	
	//
	// Constructor initializes theBufferedReader instance variable.
	//
	
	public FastqReader(BufferedReader in)
	{
		theBufferedReader = in;
	}
	
	//
	// Returns next record in the file, or null if EOF (end-of-file).
	//
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		// Read the defline from the BufferedReader. Return null if you read null, 
		// indicating end of file.
		String defline = theBufferedReader.readLine();
		if(defline != null)
		{
			// Read the next 3 lines from the buffered reader. Construct and return
			// a FastqRecord.
			String sequence = theBufferedReader.readLine();
			String line = theBufferedReader.readLine();
			String quality = theBufferedReader.readLine();
			FastqRecord fastqRecord = new FastqRecord(defline, sequence, quality);
			return fastqRecord;
		}
		return null;
	}
}
