package ch.uzh.ifi.ce.cabne.domains.LLG;


import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class ShapleyVcg implements Mechanism<Double, Double> {

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
        	double shapleyVcgPayment[] = new double[2];
            if (bids[2] > bids[0]) {
            	if (bids[2] > bids[1]) {
            		//wl
            		shapleyVcgPayment[0] = bids[2]/3.0-bids[1]/3.0;
            		shapleyVcgPayment[1] = bids[2]/3.0-bids[0]/3.0;
            	}
            	else  {
            		//sl1
            		shapleyVcgPayment[0] = 0.0;
            		shapleyVcgPayment[1] = bids[2]/2.0-bids[0]/3.0;;
            	}
            } else {
            	if (bids[2] > bids[1]) {
            		//sl0
            		shapleyVcgPayment[0] = bids[2]/2.0-bids[1]/3.0;
            		shapleyVcgPayment[1] = 0.0;
            	}
            	else {
            		//sls
            		shapleyVcgPayment[0] = bids[2]/6;
            		shapleyVcgPayment[1] = bids[2]/6;
            	}
            }
            double delta = (bids[2] - shapleyVcgPayment[0] - shapleyVcgPayment[1]) / 2;
            double[] payment = {shapleyVcgPayment[0]+delta, shapleyVcgPayment[1]+delta};
            if (payment[0] > bids[0]) {
            	payment[0] = bids[0];
            	payment[1] = bids[2]-bids[0];
            } else if (payment[1] > bids[1]) {
            	payment[1] = bids[1];
            	payment[0] = bids[2]-bids[1];
            }
            if (payment[i] > bids[i] + 0.00001) {
            	System.out.println("ss");
            	System.out.println(bids[0]);
            	System.out.println(bids[1]);
            	System.out.println(v);
            	System.out.println(payment);
            	System.out.println(v - payment[i]);
            }
            return v - payment[i];
        }
    }
}
