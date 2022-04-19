package ch.uzh.ifi.ce.cabne.domains.LLG;

import ch.uzh.ifi.ce.cabne.domains.Mechanism;

public class ShapleyWith implements Mechanism<Double, Double> {

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
        	double paymenti = 0;
        	double paymentj = 0;
        	int j = (i + 1) % 2;
            if (bids[2] > bids[0]) {
            	if (bids[2] > bids[1]) {
            		//wl
            		paymenti = bids[2]/4-bids[j]/4+bids[i]*7.0/12;
            		paymentj = bids[2]/4-bids[i]/4+bids[j]*7.0/12;
            	}
            	else  {
            		//sl2
            		paymenti = bids[i]*7.0/12;
            		paymentj = bids[2]/3-bids[i]/4+bids[j]/2;
            	}
            } else {
            	if (bids[2] > bids[1]) {
            		//sl1
            		paymenti = bids[2]/3-bids[j]/4+bids[i]/2;
            		paymentj = bids[j]*7.0/12;
            	}
            	else {
            		paymenti = bids[i]/2+bids[2]/12;
            		paymentj =  bids[j]/2+bids[2]/12;
            	}
            }
            double payment = paymenti + (bids[2] - paymenti - paymentj) / 2;
            if (payment > bids[i]) {
            	payment = bids[i];
            } else if (bids[2] - payment > bids[j]) {
            	payment = bids[2] - bids[j];
            }
            
            return v - payment;
        }
    }
}
