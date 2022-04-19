package ch.uzh.ifi.ce.cabne.domains.TLG;

import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class BidNearest implements Mechanism<Double, Double> {

	@Override
	public double computeUtility(int i, Double v, Double[] bids) {
		// In this mechanism, we assume that all players bid only on their bundle of interest.
		// bids is therefore an array of length 3.
		
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
        	double delta = (bids[0]+bids[1]+bids[2]-bids[3])/3;
        	double payment;
            if (delta <= Math.min(bids[0], Math.min(bids[1], bids[2]))) {
            	//everybody pays 1/3 of the global bid
                payment = bids[i]-delta;
            } else if (bids[0] < delta) {
            	// bidder 0 pays her full bid
            	if (i == 0) return v; 		
            	delta = (bids[1]+bids[2]-bids[3])/2;
            	if (delta <= Math.min(bids[1], bids[2])) {
                    payment = bids[i]-delta;
            	} else if (bids[1] < delta) {
            		if (i == 1) return v; 		
            		payment = bids[3];
            	} else {
            		if (i == 2) return v; 		
            		payment = bids[3];
            	}
            } else if (bids[1] < delta) {
            	// bidder 1 pays her full bid
            	if (i == 1) return v; 		
            	delta = (bids[0]+bids[2]-bids[3])/2;
            	if (delta <= Math.min(bids[0], bids[2])) {
                    payment = bids[i]-delta;
            	} else if (bids[0] < delta) {
            		if (i == 0) return v; 		
            		payment = bids[i]-delta;
            	} else {
            		if (i == 2) return v;		
            		payment = bids[i]-delta;
            	}
            } else {
            	// bidder 2 pays her full bid
            	if (i == 2) return v; 		
            	delta = (bids[0]+bids[1]-bids[3])/2;
            	if (delta <= Math.min(bids[0], bids[1])) {
                    payment = bids[i]-delta;
            	} else if (bids[0] < delta) {
            		if (i == 0) return v; 		
            		payment = bids[i]-delta;
            	} else {
            		if (i == 1) return v; 		
            		payment = bids[i]-delta;
            	}
            }

            return v - payment;
        }
    }
}