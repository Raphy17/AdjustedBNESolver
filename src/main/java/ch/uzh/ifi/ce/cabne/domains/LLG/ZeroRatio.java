package ch.uzh.ifi.ce.cabne.domains.LLG;


import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class ZeroRatio implements Mechanism<Double, Double> {

	@Override
	public double computeUtility(int i, Double v, Double[] bids) {
		// In this mechanism, we assume that all players bid only on their bundle of interest.
		// bids is therefore an array of doubles of length 3.
		
		if (i==2) {
			// utility of global player
			if (bids[2] > bids[0] + bids[1]) {
				return v - bids[1] - bids[0];
			}
			return 0.0;
		} else if (bids[2] > bids[0] + bids[1]) {
        	// global player wins
            return 0.0;
        } else {

        	double totalUtility = bids[0]+ bids[1] - bids[2];
            double payoffFromBid;
            double totalPayoff = bids[0]+bids[1];
            if (totalPayoff > 0) {
            	payoffFromBid = (totalUtility * bids[i])/totalPayoff;
            } else {
            	payoffFromBid = totalUtility/2; 
            }
            return (v-bids[i]) + payoffFromBid;
        }
        
    }
}
