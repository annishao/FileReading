package dna;

//
// FastaRecord contains the defline and sequence string from
// a record in the fasta file.
//
public class FastaRecord implements DNARecord 
{	
	private String defline;
	private String sequence;
	
	//
	// Initializes the defline and sequence instance variables.
	// Has a precondition check: throw RecordGFormatException if 
	// the 1st char of the defline is not '>'. 
	//
	public FastaRecord(String defline, String sequence) throws RecordFormatException
	{
		if(defline.charAt(0) != '>')
		{
			throw new RecordFormatException("Bad 1st char in defline in fasta record: saw " + defline.charAt(0) + " expected: >");
		}
		this.defline = defline;
		this.sequence = sequence;
	}
	
	//
	// Initializes defline and sequence from the input record. The defline should be the
	// defline of the fastq record, but with a '>' in the first position rather than a '@'.
	//
	public FastaRecord(FastqRecord fastqRec)
	{
		this.defline = ">" + fastqRec.getDefline().substring(1);
		this.sequence = fastqRec.getSequence();
	}
	
	//
	// Returns the defline of the FastaRecord.
	//
	public String getDefline()
	{
		return defline;
	}
	
	//
	// Returns the sequence of the FastaRecord.
	//
	public String getSequence()
	{
		return sequence;
	}
	
	//  
	// Checks for deep equality for both instance variables.
	//
	public boolean equals(Object x)
	{
		FastaRecord that = (FastaRecord) x;
		return this.defline.equals(that.defline) && this.sequence.equals(that.sequence);
	}
	
	//
	// Returns the sum of the hashcodes of defline and sequence.
	//
	public int hashCode()
	{
		return defline.hashCode() + sequence.hashCode();
	}
}
