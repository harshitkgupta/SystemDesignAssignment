import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

public class ConcurrentHistogram implements Histogram{

	private Map<Entry<Double,Double>,Integer> intervalHistogram = null;
	
	private List<Double> sampleList = null;
	private List<Double> outlierList = null;
	
	private Double mean = 0.0;
	private Double sum =0.0;
	private Double squared_sum =0.0;
	private Double variance =0.0;
	
	public ConcurrentHistogram(List<Entry<Double, Double>> intervalList){
		super();
		intervalHistogram = new ConcurrentHashMap<Entry<Double,Double>,Integer>();
		sampleList = new ArrayList<Double>();
		outlierList = new ArrayList<Double>();
		try {
			validateIntervalList(intervalList);
		} catch (Exception e) {
			System.out.println("Overlapping Intervals Found. Please enter correct intervals");
			e.printStackTrace();
		}
		intervalList.forEach(interval -> this.intervalHistogram.put(interval, 0));
	}
	
	private void validateIntervalList(List<Entry<Double, Double>> intervalList) throws Exception {
		Collections.sort(intervalList, (u,v)-> u.getKey().compareTo(v.getKey()));
		for(int i=0; i<intervalList.size()-1;i++)
		{
			for(int j=i+1; j<intervalList.size() ;j++)
			{
				
				//Condition to check for overlapping interval
				if((intervalList.get(i).getKey()<=intervalList.get(j).getKey() && intervalList.get(j).getKey()<intervalList.get(i).getValue()))
				{			
					throw new Exception("Overlapping Intervals Found");
				}
			}		
				
		}	
	}

	@Override
	public Double getMean() {
		return mean;
	}

	@Override
	public Double getVariance() {
		return variance;
	}

	@Override
	public List<Double> getOutLiers() {
		return outlierList;
	}

	@Override
	public List<Entry<Double, Double>> getIntervals() {
		return new ArrayList<Entry<Double, Double>>(intervalHistogram.keySet());
	}

	@Override
	public List<Double> getSamples() {
		return sampleList;
	}

	@Override
	public void putSampleInBins(List<Double> inputSampleList) {
		inputSampleList.stream().forEach(sample->{
			Boolean isOutlier =true;
			for(Entry<Double,Double> entry: intervalHistogram.keySet())
			{
				Double first = entry.getKey();
				Double second = entry.getValue();
				if(first <= sample && sample < second)
				{
					synchronized(entry){
						Integer count = intervalHistogram.get(entry)+1;
						intervalHistogram.put(entry, count);
					}
					synchronized (sampleList) {
						sampleList.add(sample);
					}
					updateMeanAndVariance(first, second);
					isOutlier = false;
					break;
				}	
			}
			if(isOutlier)
			{	
				synchronized (outlierList) {
					outlierList.add(sample);
				}	
			}	
				
		});
	}


	private synchronized void updateMeanAndVariance(Double first, Double second) {
		Double value = (first+second)/2.0;
		sum += value;
		mean  = sum/sampleList.size();
		squared_sum += value*value;
		variance = (squared_sum - (sum * sum)/sampleList.size())/sampleList.size();
	}


	public void printSummary(){
		System.out.println("------Histogram Summary------");
		System.out.println("Histogram Bins count : "+getIntervals().size());
		System.out.println("-------------------------------");
		System.out.println("Start - End : Frequency");
		
		Map<Double,Double> intervalTreeMap = new TreeMap<Double,Double>();
		for(Entry<Double,Double> entry: intervalHistogram.keySet())
		{	
			intervalTreeMap.put(entry.getKey(), entry.getValue());
		}
		for(Entry<Double,Double> entry: intervalTreeMap.entrySet())
		{	
			System.out.println(entry.getKey()+" - "+ entry.getValue()+" : "+intervalHistogram.get(entry));
		}
		
		System.out.println("-------------------------------");
		System.out.println("Histogram Outliers : ");
		outlierList.forEach(u->System.out.print(u+" "));
		System.out.println("\n-------------------------------");
		
		System.out.println("Histogram Mean : "+mean);
		System.out.println("Histogram Variance : "+variance);
		System.out.println("-------------------------------");
	}

}
