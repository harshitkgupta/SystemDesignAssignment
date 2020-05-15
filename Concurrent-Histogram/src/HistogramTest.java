
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class HistogramTest extends TestCase{
	
	Histogram histogram = null;
	List<Entry<Double,Double>> intervalList =null;
	List<Double> sampleList = null;
	List<Double> sampleListInBins = null;
	List<Double> outliers = null;
	
	@Before
	public void setUp() throws Exception
	{
		intervalList = new ArrayList<Entry<Double,Double>>();
		intervalList.add(new AbstractMap.SimpleEntry<Double, Double>(3.0, 4.1));
		intervalList.add(new AbstractMap.SimpleEntry<Double, Double>(8.5, 8.7));
		intervalList.add(new AbstractMap.SimpleEntry<Double, Double>(0.0, 1.1));
		intervalList.add(new AbstractMap.SimpleEntry<Double, Double>(31.5, 41.27));
		
		histogram = new ConcurrentHistogram(intervalList);
		Double sampleArry []= {40.1, 8.1, 8.2, 30.0, 31.51, 1.0, 41.27};
		sampleList = new ArrayList<Double>(Arrays.asList(sampleArry));
		
		Double sampleInBinsArry []= {40.1, 31.51, 1.0};
		sampleListInBins = new ArrayList<Double>(Arrays.asList(sampleInBinsArry));
		
		Double outliersArry []= {8.1, 8.2, 30.0, 41.27};
		outliers = new ArrayList<Double>(Arrays.asList(outliersArry));
	}
	
	@Test(expected=Exception.class)
	public void testHistogram_overlappingIntervals()
	{
		intervalList.add(new AbstractMap.SimpleEntry<Double, Double>(29.7, 35.4));
		histogram = new ConcurrentHistogram(intervalList);
	}
	
	@Test
	public void testHistogram_getIntervalsList()
	{	
		assertTrue(intervalList.containsAll(histogram.getIntervals()));
		assertTrue(histogram.getIntervals().containsAll(intervalList));
	}
	
	@Test
	public void testHistogram_getSamples()
	{	
		histogram.putSampleInBins(sampleList);
		sampleList.removeAll(outliers);
		assertTrue(sampleList.containsAll(histogram.getSamples()));
		assertTrue(histogram.getSamples().containsAll(sampleList));
	}
	
	@Test
	public void testHistogram_getOutliers()
	{
		histogram.putSampleInBins(sampleList);
		assertTrue(outliers.containsAll(histogram.getOutLiers()));
		assertTrue(histogram.getOutLiers().containsAll(outliers));
	}
	
	@Test
	public void testHistogram_getMean()
	{
		histogram.putSampleInBins(sampleList);
		assertEquals(24.44, histogram.getMean());
	}
	
	@Test
	public void testHistogram_getVariance()
	{
		histogram.putSampleInBins(sampleList);
		assertEquals(285.3660500000001, histogram.getVariance());
	}
}
