import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

public class HistogramClient {
	
	public static void main(String args[]) throws Exception
	{
		if(args[0] == null  || args[0].trim().length()==0)
		{
			System.out.println("No input file was specified");
			throw new Exception("No input file was specified");
		}	
		try(Scanner sc= new Scanner(new File(args[0])))
		{
			int intervals = sc.nextInt();
			List<Entry<Double,Double>> intervalList = new ArrayList<Entry<Double,Double>>();
			for(int i=0;i<intervals; i++)
			{	
				double first = sc.nextDouble();
				double second = sc.nextDouble();
			
				intervalList.add(new AbstractMap.SimpleEntry<Double, Double>(first, second));
			}
			Histogram histogram = new ConcurrentHistogram(intervalList);
			int samples = sc.nextInt();
			List<Double> sampleList = new ArrayList<Double>();
			for(int i=0;i<samples; i++)
			{	
				double sample = sc.nextDouble();			
				sampleList.add(sample);
			}
			histogram.putSampleInBins(sampleList);
			histogram.printSummary();
		
		} catch (FileNotFoundException e) {
			System.out.println("Path is not valid or Input File does not exist");
			e.printStackTrace();
		}
		
	}
}
