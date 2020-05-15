import java.util.List;
import java.util.Map.Entry;

public interface Histogram {
	
	public Double getMean();
	
	public Double getVariance();
	
	public List<Double> getOutLiers();
	
	public List<Entry<Double,Double>> getIntervals();
	
	public List<Double> getSamples();
	
	public void putSampleInBins(List<Double> sampleList);
	
	public void printSummary();

}
