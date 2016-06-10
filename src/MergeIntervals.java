import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/*
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */

public class MergeIntervals {
	
	public static List<Interval> mergeIntervals(
			final List<Interval> intervals) {
		
		Collections.sort(intervals, new IntervalComparator());
		
		List<Interval> result = new ArrayList<Interval>();
		for(int startIndex = 0; startIndex < intervals.size(); startIndex++) {
			Interval interval = intervals.get(startIndex);
			for(int endIndex = startIndex + 1; endIndex < intervals.size(); endIndex++) {
				final Interval addition = intervals.get(endIndex);
				if(addition.start > interval.end) {
					break;
				}
				
				interval = new Interval(
						interval.start,
						Math.max(interval.end, addition.end));
			}
			
			result.add(interval);
			
		}
		
		return result;
	}
	
	public static class Interval {
		
		private final int start;
		private final int end;
		
		public Interval(final int start, final int end) {
			this.start = start;
			this.end = end;
		}
		
		public int getStart() {
			return this.start;
		}
		
		public int getEnd() {
			return this.end;
		}
	}
	
	public static class IntervalComparator implements Comparator<Interval> {

		@Override
		public int compare(final Interval o1, final Interval o2) {
			return ((Integer)o1.getStart()).compareTo(o2.start);
		}
	}
}
