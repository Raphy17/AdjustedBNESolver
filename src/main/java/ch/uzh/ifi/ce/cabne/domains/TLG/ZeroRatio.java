
package ch.uzh.ifi.ce.cabne.domains.TLG;


import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class ZeroRatio implements Mechanism<Double, Double> {

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
        	double totalUtility = bids[0]+ bids[1] + bids[2] - bids[3];
            double payoffFromBid;
            double totalPayoff = bids[0]+ bids[1] + bids[2];
            if (totalPayoff > 0) {
            	payoffFromBid = (totalUtility * bids[i])/totalPayoff;
            } else {
            	payoffFromBid = totalUtility/3; 
            }
            return (v-bids[i]) + payoffFromBid;
        }
    }
}
