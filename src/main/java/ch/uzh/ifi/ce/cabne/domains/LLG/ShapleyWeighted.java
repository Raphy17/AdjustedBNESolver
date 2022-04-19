package ch.uzh.ifi.ce.cabne.domains.LLG;


import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class ShapleyWeighted implements Mechanism<Double, Double> {

	@Override
	public double computeUtility(int i, Double v, Double[] bids) {
		// In this mechanism, we assume that all players bid only on their bundle of interest.
		// bids is therefore an array of length 3.
		
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
        	Double shapleyPayoffs[] = new Double[3];
        	double a = bids[0];
        	double b = bids[1];
        	double g = bids[2];
            if (bids[2] > bids[0]) {
            	if (bids[2] > bids[1]) {
            		//wl
            		shapleyPayoffs[0] = 5.0/6*a+b/3-g/3;
            		shapleyPayoffs[1] = 5.0/6*b+a/3-g/3;
            	}
            	else  {
            		//sl2
            		shapleyPayoffs[0] = 5.0/6*a;
            		shapleyPayoffs[1] = b+a/3-g/2;
            	}
            } else {
            	if (bids[2] > bids[1]) {
            		//sl1
            		shapleyPayoffs[0] = a+b/3-g/2;
            		shapleyPayoffs[1] = 5.0/6*b;
            	}
            	else {
            		//sls
            		shapleyPayoffs[0] = a-g/6;
            		shapleyPayoffs[1] = b-g/6;
            	}
            }
            double totalUtility = a + b - g;
            double payoffFromBid;
            if ((shapleyPayoffs[0]+shapleyPayoffs[1]) > 0) {
            	payoffFromBid = (totalUtility * shapleyPayoffs[i])/(shapleyPayoffs[0]+shapleyPayoffs[1]);
            } else {
            	payoffFromBid = totalUtility /2; 
            }
            
            return (v-bids[i]) + payoffFromBid;
        }
    }
}
