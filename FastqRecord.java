package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//
public class FastqRecord implements DNARecord
{
	private String defline;
	private String sequence;
	private String quality;
	
	//
	// Initializes the 3 instance variables defline, sequence, and quality.
	// Add a precondition check: throw RecordFormatException if the 1st char of the defline is 
	// not '@'. 
	//
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		if(defline.charAt(0) != '@')
		{
			throw new RecordFormatException("Bad 1st char in defline in fastq record: saw " + defline.charAt(0) + " expected: @");
		}
		this.defline = defline;
		this.sequence = sequence;
		this.quality = quality;
	}
	
	//
	// Returns the defline of FastqRecord.
	//
	public String getDefline()
	{
		return defline;
	}
	
	//
	// Returns the sequence of FastqRecord.
	//
	public String getSequence()
	{
		return sequence;
	}

	//
	// Checks for deep equality of all 3 instance variables. 
	//
	public boolean equals(Object x)
	{
		FastqRecord that = (FastqRecord) x;
		return this.defline.equals(that.defline) && this.quality.equals(that.quality) && this.sequence.equals(that.sequence);
	}
	
	//
	// Returns true if the quality contains at least one dollar sign and at least one hash sign.
	//
	public boolean qualityIsLow()
	{
		return quality.contains("$") && quality.contains("#");
	}
	
	
	//
	// Returns the sum of the hash codes of defline, sequence, and quality.
	//
	public int hashCode()
	{
		return defline.hashCode() + sequence.hashCode() + quality.hashCode();
	}
}
