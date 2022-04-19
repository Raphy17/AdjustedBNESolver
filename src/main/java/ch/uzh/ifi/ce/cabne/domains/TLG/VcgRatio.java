package ch.uzh.ifi.ce.cabne.domains.TLG;


import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class VcgRatio implements Mechanism<Double, Double> {

	@Override
	public double computeUtility(int i, Double v, Double[] bids) {
		// In this mechanism, we assume that all players bid only on their bundle of interest.
		// bids is therefore an array of doubles of length 3.
		
		if (i==3) {
			// utility of global player
			if (bids[3] > bids[0] + bids[1] + bids[2]) {
				return v - bids[2] - bids[1] - bids[0];
			}
			return 0.0;
		} else if (bids[3] > bids[0] + bids[1] + bids[2]) {
        	// global player wins
            return 0.0;
        } else {
        	Double[] referencePoint = { Math.max(bids[3] - bids[2] - bids[1], 0), Math.max(bids[3] - bids[2] - bids[0], 0), Math.max(bids[3] - bids[1] - bids[0], 0) };  
        	Double[] vcgPayoff = {bids[0]-referencePoint[0], bids[1]-referencePoint[1], bids[2]-referencePoint[2]};
        	double totalUtility = bids[0]+ bids[1] + bids[2] - bids[3];
            double payoffFromBid;
            double totalPayoff = vcgPayoff[0]+vcgPayoff[1]+vcgPayoff[2];
            if (totalPayoff > 0) {
            	payoffFromBid = (totalUtility * vcgPayoff[i])/totalPayoff;
            } else {
            	payoffFromBid = totalUtility/3; 
            }
            return (v-bids[i]) + payoffFromBid;
        }
    }
}
