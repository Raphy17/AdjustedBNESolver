package ch.uzh.ifi.ce.cabne.domains.TLG;


import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class Quadratic implements Mechanism<Double, Double> {

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
        	Double[] referencePoint = { Math.max(bids[3] - bids[2] - bids[1], 0)/2.0, Math.max(bids[3] - bids[2] - bids[0], 0)/2.0, Math.max(bids[3] - bids[1] - bids[0], 0)/2.0 };  
        	Double quadraticPayment[] = new Double[3];
        	double delta = (bids[3] - referencePoint[0] - referencePoint[1] - referencePoint[2])/3;
        	quadraticPayment[0] = referencePoint[0] + delta;
        	quadraticPayment[1] = referencePoint[1] + delta;
        	quadraticPayment[2] = referencePoint[2] + delta;
        	Double finalPayment[] = new Double[3];
            if (quadraticPayment[0] > bids[0]) {
            	finalPayment[0] = bids[0];
            	delta = (bids[3] - bids[0] - referencePoint[1] - referencePoint[2])/2;
            	quadraticPayment[1] = referencePoint[1] + delta;
            	quadraticPayment[2] = referencePoint[2] + delta;
            	if (quadraticPayment[1] > bids[1]) {
            		finalPayment[1] = bids[1];
            		finalPayment[2] = bids[3] - bids[0] - bids[1];	
            	} else if (quadraticPayment[2] > bids[2]) {
            		finalPayment[2] = bids[2];
            		finalPayment[1] = bids[3] - bids[0] - bids[2];	
            	} else {
            		finalPayment[1] = quadraticPayment[1];
            		finalPayment[2] = quadraticPayment[2];
            	}
            } else if (quadraticPayment[1] > bids[1]) {
            	finalPayment[1] = bids[1];
            	delta = (bids[3] - bids[1] - referencePoint[0] - referencePoint[2])/2;
            	quadraticPayment[0] = referencePoint[0] + delta;
            	quadraticPayment[2] = referencePoint[2] + delta;
            	if (quadraticPayment[0] > bids[0]) {
            		finalPayment[0] = bids[0];
            		finalPayment[2] = bids[3] - bids[0] - bids[1];	
            	} else if (quadraticPayment[2] > bids[2]) {
            		finalPayment[2] = bids[2];
            		finalPayment[0] = bids[3] - bids[1] - bids[2];	
            	} else {
            		finalPayment[0] = quadraticPayment[0];
            		finalPayment[2] = quadraticPayment[2];
            	}
            } else if (quadraticPayment[2] > bids[2]) {
            	finalPayment[2] = bids[2];
            	delta = (bids[3] - bids[2] - referencePoint[1] - referencePoint[0])/2;
            	quadraticPayment[1] = referencePoint[1] + delta;
            	quadraticPayment[0] = referencePoint[0] + delta;
            	if (quadraticPayment[1] > bids[1]) {
            		finalPayment[1] = bids[1];
            		finalPayment[0] = bids[3] - bids[2] - bids[1];	
            	} else if (quadraticPayment[0] > bids[0]) {
            		finalPayment[0] = bids[0];
            		finalPayment[1] = bids[3] - bids[0] - bids[2];	
            	} else {
            		finalPayment[1] = quadraticPayment[1];
            		finalPayment[0] = quadraticPayment[0];
            	}
            } else {
        		finalPayment[0] = quadraticPayment[0];
            	finalPayment[1] = quadraticPayment[1];
        		finalPayment[2] = quadraticPayment[2];
            }
           
            
            return v - finalPayment[i];
        }
    }
}
