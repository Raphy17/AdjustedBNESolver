package ch.uzh.ifi.ce.cabne.domains.TLG;

import java.util.Iterator;
import java.util.List;

import ch.uzh.ifi.ce.cabne.algorithm.BNESolverContext;
import ch.uzh.ifi.ce.cabne.domains.BidSampler;
import ch.uzh.ifi.ce.cabne.strategy.Strategy;

public class TLGSampler extends BidSampler<Double, Double> {

	
	public TLGSampler(BNESolverContext<Double, Double> context) {
		super(context);
	}

	public Iterator<Sample> conditionalBidIterator(int i, Double v, Double b, List<Strategy<Double, Double>> s) {
		// get an TLG sample, assuming that local bidders are all independently distributed u.a.r. in [0,1]
		// and global bidder is u.a.r. in [0,2]
		
		// NOTE: this assumes that global bidder plays truthful, which is only the case with a core-selecting rule.
		// It's not enough to add a separate code path for i == 2 to make it work for a strategic global bidder.
		if (i == 3) {
			throw new RuntimeException("This sampler assumes that the global player is truthful.");
		}
		
		final int localopponent1 = (i + 1) % 3;
		final int localopponent2 = (i + 2) % 3;
		Iterator<double[]> rngiter = context.getRng(3).nextVectorIterator();
		Strategy<Double, Double> slocal1 = s.get(localopponent1); 
		Strategy<Double, Double> slocal2 = s.get(localopponent2); 
		Strategy<Double, Double> sglobal = s.get(3);
		double local1Max = slocal1.getMaxValue();
		double local2Max = slocal2.getMaxValue();
		double local1Min = 0.0;
		double local2Min = 0.0;
		double globalMax = sglobal.getMaxValue();
		double globalMin = 0.0;
		Iterator<Sample> it = new Iterator<Sample>() {
			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public Sample next() {
				double[] r = rngiter.next();
				Double result[] = new Double[4];
				
				// bids of local players
				result[i] = b;
				result[localopponent1] = slocal1.getBid(local1Min + r[0] * (local1Max-local1Min)); 
				result[localopponent2] = slocal2.getBid(local2Min + r[1] * (local2Max-local2Min)); 
				
				// bid of global player, ignoring the area where the global player wins, and adjusting the density
				// accordingly (basically importance sampling)
				
				double globalbound = Math.min(b + result[localopponent1] + result[localopponent2] , globalMax);
				double density = (globalbound-globalMin) / (globalMax-globalMin) / (globalMax-globalMin); // would be 1 / globalMax without importance sampling
				
//				double density = 1 / globalMax; // wihout importance sampling

				result[3] = globalMin + (globalbound-globalMin) * r[2];
//				result[3] = globalMax * r[2];	//without importance sampling

				
				return new Sample(density, result);
			}
		};
		return it;
	}
}
