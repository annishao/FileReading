package dna;

import java.io.*;
import java.util.*;


public class FileConverter 
{
	private File fastq;
	private File fasta;
	
	//
	// Initializes the fastq and fasta instance variables.
	//
	public FileConverter(File fastq, File fasta)
	{
		this.fastq = fastq;
		this.fasta = fasta;
	}
	
	//
	// Writes a fasta file consisting of conversion of all records from the fastq with
	// sufficient quality and unique defline.
	//
	public void convert() throws IOException
	{
		// Build chain of readers.
		FileReader fr = new FileReader(fastq);
		BufferedReader br = new BufferedReader(fr);
		FastqReader fqr = new FastqReader(br);

		// Build chain of writers.
		FileWriter fw = new FileWriter(fasta);
		PrintWriter pw = new PrintWriter(fw);
		FastaWriter faw = new FastaWriter(pw);
		
		// Ensures that convert() will stop running when the end of the fastq file is reached.
		boolean endReached = false;
		while(!endReached)
		{
			try
			{
				FastqRecord fastqRecord = fqr.readRecord();
				if(fastqRecord != null)
				{
					// Ensures that quality of FastqRecord is good enough to be written as a FastaRecord.
					if(fastqRecord.qualityIsLow() == false)
					{
						FastaRecord far= new FastaRecord(fastqRecord);
						faw.writeRecord(far);
					}
				}
				else
				{
					endReached = true;
				}
			}
			catch(RecordFormatException rfe)
			{
				System.out.println(rfe.getMessage());
			}
		}
		// Close PrintWriter, FileWriter, BufferedReader, and FileWriter.
		pw.close();
		fw.close();
		br.close();
		fr.close();
	}
	
		
	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW4.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW4.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
