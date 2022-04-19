package ch.uzh.ifi.ce.cabne.domains.LLG;

import ch.uzh.ifi.ce.cabne.domains.Mechanism;

public class ShapleyDistr implements Mechanism<Double, Double> {

	@Override
	public double computeUtility(int i, Double v, Double[] bids) {
		// In this mechanism, we assume that all players bid only on their bundle of interest.
		// bids is therefore an array of length 3.
		
		if (i==2) {
			// utility of global player
			if (bids[2] > bids[0] + bids[1]) {
				return v - bids[1]*7/6.0 - bids[0]*7/6.0;
			} else {
				double util_g = 0;
	        	double a = bids[0];
	        	double b = bids[1];
	        	double g = bids[2];
				if (bids[2] > bids[0]) {
	            	if (bids[2] > bids[1]) {
	            		//wl
	            		util_g = 1/4.0*g-1/12.0*(a+b);

	            	}
	            	else  {
	            		//sl2
	            		util_g = 1/6.0*g-1/6.0*a;
	            	}
	            } else {
	            	if (bids[2] > bids[1]) {
	            		//sl1
	            		util_g =1/6.0*g-1/6.0*b;
	            	}
	            	else {
	            		util_g = 1/12.0*g;
	            	}
	            }
				return util_g;
			}
        } else {
        	double paymenti = 0;
        	double paymentj = 0;
        	double util_g = 0;
        	double a = bids[0];
        	double b = bids[1];
        	double g = bids[2];
        	int j = (i + 1) % 2;
            if (bids[2] > bids[0]) {
            	if (bids[2] > bids[1]) {
            		//wl
            		paymenti = bids[2]/4-bids[j]/4+bids[i]*7.0/12;
            		paymentj = bids[2]/4-bids[i]/4+bids[j]*7.0/12;
            		util_g = 1/4.0*g-1/12.0*(a+b);

            	}
            	else  {
            		//sl2
            		paymenti = bids[i]*7.0/12;
            		paymentj = bids[2]/3-bids[i]/4+bids[j]/2;
            		util_g = 1/6.0*g-1/6.0*a;
            	}
            } else {
            	if (bids[2] > bids[1]) {
            		//sl1
            		paymenti = bids[2]/3-bids[j]/4+bids[i]/2;
            		paymentj = bids[j]*7.0/12;
            		util_g =1/6.0*g-1/6.0*b;
            	}
            	else {
            		paymenti = bids[i]/2+bids[2]/12;
            		paymentj =  bids[j]/2+bids[2]/12;
            		util_g = 1/12.0*g;
            	}
            }
            if (bids[2] > bids[0] + bids[1]) {
            	return bids[i]/6;
            }
            double payment = paymenti + (bids[2] - util_g - paymenti - paymentj) / 2;
            if (payment > bids[i]) {
            	payment = bids[i];
            } else if (bids[2] - util_g - payment > bids[j]) {
            	payment = bids[2] - util_g - bids[j];
            }
            
            return v - payment;
        }
    }
}
