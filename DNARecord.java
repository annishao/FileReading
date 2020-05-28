package dna;

//
// FastqRecord and FastaRecord should implement this.
public interface DNARecord 
{
	String		getDefline();
	String		getSequence();
}