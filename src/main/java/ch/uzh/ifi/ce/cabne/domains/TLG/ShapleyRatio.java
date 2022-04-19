package ch.uzh.ifi.ce.cabne.domains.TLG;

import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class ShapleyRatio implements Mechanism<Double, Double> {

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
        	Double shapleyPayoff[] = new Double[3];
        	double a = bids[0];
        	double b = bids[1];
        	double c = bids[2];
        	double g = bids[3];
        	
        	
        	if (g > Math.max(a, Math.max(b, c))) {
        		//weak locals
        		if (g > a + b ) {
        			//X
        			if (g > a + c ) {
        				//XY
        				if (g > b + c) {
        					//XYZ
        					shapleyPayoff[0] = (3*a+b+c-g)/4;
        					shapleyPayoff[1] = (a+3*b+c-g)/4;
        					shapleyPayoff[2] = (a+b+3*c-g)/4;
        				} else {
        					//XY
        					shapleyPayoff[0] = 3.0/4*a;
        					shapleyPayoff[1] = a/4+5.0/6*b+c/3-g/3;
        					shapleyPayoff[2] = a/4+b/3+5.0/6*c-g/3;
        				}        				
        			} else {
        				//X
        				if (g > b + c) {
        					//XZ
        					shapleyPayoff[0] = 5.0/6*a+b/4+c/3-g/3;
        					shapleyPayoff[1] = 3.0/4*b;
        					shapleyPayoff[2] = a/3+b/4+5.0/6*c-g/3;
        				} else {
        					//X
        					shapleyPayoff[0] = 5.0/6*a+c/12-g/12;
        					shapleyPayoff[1] = 5.0/6*b+c/12-g/12;
        					shapleyPayoff[2] = a/3+b/3+11.0/12*c-5.0/12*g;
        				}
        			}
        		} else {
        			//
        			if (g > a + c) {
    					//Y
    					if (g > b + c) {
    						//YZ
    						shapleyPayoff[0] = 5.0/6*a+b/3+c/4-g/3;
        					shapleyPayoff[1] = a/3+5.0/6*b+c/4-g/3;
        					shapleyPayoff[2] = 3.0/4*c;
    					} else {
    						//Y
    						shapleyPayoff[0] = 5.0/6*a+b/12-g/12;
    						shapleyPayoff[1] = a/3+11.0/12*b+c/3-5.0/12*g;
        					shapleyPayoff[2] = 5.0/6*c+b/12-g/12;
    					}
    				} else {
    					//
    					if (g > b + c) {
    						//Z
    						shapleyPayoff[0] = 11.0/12*a+b/3+c/3-5.0/12*g;
        					shapleyPayoff[1] = 5.0/6*b+a/12-g/12;
        					shapleyPayoff[2] = 5.0/6*c+a/12-g/12;
    					} else {
    						//
    						shapleyPayoff[0] = 11.0/12*a+b/12+c/12-g/6;
        					shapleyPayoff[1] = a/12+11.0/12*b+c/12-g/6;
        					shapleyPayoff[2] = a/12+b/12+11.0/12*c-g/6;
    						
    					}
    				}
        			
        		}
        	} else {
        		//strong locals
        		if (g > a) {
        			//X
        			if (g > b) {
        				//XY
        				if (g > a + b) {
        					//XYQ 
        					shapleyPayoff[0] = 5.0/6*a;
        					shapleyPayoff[1] = 5.0/6*b;
        					shapleyPayoff[2] = 5.0/6*c;
        				} else {
        					//XY
        					shapleyPayoff[0] = 11.0/12*a+b/12-g/12;
        					shapleyPayoff[1] = 11.0/12*b+a/12-g/12;
        					shapleyPayoff[2] = a/12+b/12+c-g/4;
        				}
        			} else {
        				//X
        				if (g > c) {
        					//XZ
        					if (g > a + c) {
            					//XZQ 
        						shapleyPayoff[0] = 5.0/6*a;
            					shapleyPayoff[1] = a/3+b+c/3-g/2;
            					shapleyPayoff[2] = 5.0/6*c;
            				} else {
            					//XZ
            					shapleyPayoff[0] = 11.0/12*a+c/12-g/12;
            					shapleyPayoff[1] = a/12+b+c/12-g/4;
            					shapleyPayoff[2] = 11.0/12*c+a/12-g/12;
            				}
        				} else {
        					//X
        					shapleyPayoff[0] = 11.0/12*a;
        					shapleyPayoff[1] = a/12+b-g/6;
        					shapleyPayoff[2] = a/12+c-g/6;
        				}
        			}
        		} else {
        			//
        			if (g > b) {
        				//Y
        				if (g > c) {
        					//YZ
        					if (g > b + c) {
            					//YZQ 
        						shapleyPayoff[0] = a+b/3+c/3-g/2;
            					shapleyPayoff[1] = 5.0/6*b;
            					shapleyPayoff[2] = 5.0/6*c;
            				} else {
            					//YZ
            					shapleyPayoff[0] = a+b/12+c/12-g/4;
            					shapleyPayoff[1] = 11.0/12*b+c/12-g/12;
            					shapleyPayoff[2] = 11.0/12*c+b/12-g/12;
            				}
        				} else {
        					//Y
        					shapleyPayoff[0] = a+b/12-g/6;
        					shapleyPayoff[1] = 11.0/12*b;
        					shapleyPayoff[2] = b/12+c-g/6;
        				}
        			} else {
        				//
        				if (g > c) {
        					//Z
        					shapleyPayoff[0] = a+c/12-g/6;
        					shapleyPayoff[1] = b+c/12-g/6;
        					shapleyPayoff[2] = 11.0/12*c;
        				} else {
        					//
        					shapleyPayoff[0] = a-g/12;
        					shapleyPayoff[1] = b-g/12;
        					shapleyPayoff[2] = c-g/12;
        				}
        			}
        		}
        	}
        	double totalUtility = a + b + c - g;
            double payoffFromBid;
            double totalPayoff = shapleyPayoff[0]+shapleyPayoff[1]+shapleyPayoff[2];
            if (totalPayoff > 0) {
            	payoffFromBid = (totalUtility * shapleyPayoff[i])/totalPayoff;
            } else {
            	payoffFromBid = totalUtility/3; 
            }
        	
//        	System.out.println(i + "|"+ v);
//        	System.out.println(bids[0]+ "|" + bids[1] +"|"+ bids[2] + "|" + bids[3]);
//        	System.out.println(shapleyPayoff[0]+ "|" + shapleyPayoff[1] +"|"+ shapleyPayoff[2]);
//        	System.out.println(shapleyPaymentReferencePoint[0]+ "|" + shapleyPaymentReferencePoint[1] +"|"+ shapleyPaymentReferencePoint[2]);
//        	System.out.println(delta);
//        	System.out.println("----------");
        	//Checking irrationality constraints

            return (v-bids[i]) + payoffFromBid;

        }
    }
}


