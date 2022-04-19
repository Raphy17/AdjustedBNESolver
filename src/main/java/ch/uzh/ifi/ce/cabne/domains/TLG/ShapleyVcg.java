package ch.uzh.ifi.ce.cabne.domains.TLG;

import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class ShapleyVcg implements Mechanism<Double, Double> {

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
        	Double shapleyVcgPayment[] = new Double[3];
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
        					shapleyVcgPayment[0] = (g-b-c)/4;
        					shapleyVcgPayment[1] = (g-a-c)/4;
        					shapleyVcgPayment[2] = (g-a-b)/4;
        				} else {
        					//XY
        					shapleyVcgPayment[0] = 0.0;
        					shapleyVcgPayment[1] = (g-c)/3-a/4;
        					shapleyVcgPayment[2] = (g-b)/3-a/4;
        				}        				
        			} else {
        				//X
        				if (g > b + c) {
        					//XZ
        					shapleyVcgPayment[0] = (g-c)/3-b/4;
        					shapleyVcgPayment[1] = 0.0;
        					shapleyVcgPayment[2] = (g-a)/3-b/4;
        				} else {
        					//X
        					shapleyVcgPayment[0] = (g-c)/12;
        					shapleyVcgPayment[1] = (g-c)/12;
        					shapleyVcgPayment[2] = 5/12.0*g-(a+b)/3;
        				}
        			}
        		} else {
        			//
        			if (g > a + c) {
    					//Y
    					if (g > b + c) {
    						//YZ
    						shapleyVcgPayment[0] = (g-b)/3-c/4;
        					shapleyVcgPayment[1] = (g-a)/3-c/4;
        					shapleyVcgPayment[2] = 0.0;
    					} else {
    						//Y
    						shapleyVcgPayment[0] = (g-b)/12;
    						shapleyVcgPayment[1] = 5/12.0*g-(a+c)/3;
        					shapleyVcgPayment[2] = (g-b)/12;
    					}
    				} else {
    					//
    					if (g > b + c) {
    						//Z
    						shapleyVcgPayment[0] = 5/12.0*g-(b+c)/3;
        					shapleyVcgPayment[1] = (g-a)/12;
        					shapleyVcgPayment[2] = (g-a)/12;
    					} else {
    						//
    						shapleyVcgPayment[0] = g/6-(b+c)/12;
        					shapleyVcgPayment[1] = g/6-(a+c)/12;
        					shapleyVcgPayment[2] = g/6-(a+b)/12;
    						
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
        					shapleyVcgPayment[0] = 0.0;
        					shapleyVcgPayment[1] = 0.0;
        					shapleyVcgPayment[2] = g/2-(a+b)/3;
        				} else {
        					//XY
        					shapleyVcgPayment[0] = (g-b)/12;
        					shapleyVcgPayment[1] = (g-a)/12;
        					shapleyVcgPayment[2] = g/4-(a+b)/12;
        				}
        			} else {
        				//X
        				if (g > c) {
        					//XZ
        					if (g > a + c) {
            					//XZQ 
        						shapleyVcgPayment[0] = 0.0;
            					shapleyVcgPayment[1] = g/2-(a+c)/3;
            					shapleyVcgPayment[2] = 0.0;
            				} else {
            					//XZ
            					shapleyVcgPayment[0] = (g-c)/12;
            					shapleyVcgPayment[1] = g/4-(a+c)/12;
            					shapleyVcgPayment[2] = (g-a)/12;
            				}
        				} else {
        					//X
        					shapleyVcgPayment[0] = 0.0;
        					shapleyVcgPayment[1] = g/6-a/12;
        					shapleyVcgPayment[2] = g/6-a/12;
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
        						shapleyVcgPayment[0] = g/2-(b+c)/3;
            					shapleyVcgPayment[1] = 0.0;
            					shapleyVcgPayment[2] = 0.0;
            				} else {
            					//YZ
            					shapleyVcgPayment[0] = g/4-(b+c)/12;
            					shapleyVcgPayment[1] = (g-c)/12;
            					shapleyVcgPayment[2] = (g-b)/12;
            				}
        				} else {
        					//Y
        					shapleyVcgPayment[0] = g/6-b/12;
        					shapleyVcgPayment[1] = 0.0;
        					shapleyVcgPayment[2] = g/6-b/12;
        				}
        			} else {
        				//
        				if (g > c) {
        					//Z
        					shapleyVcgPayment[0] = g/6-c/12;
        					shapleyVcgPayment[1] = g/6-c/12;
        					shapleyVcgPayment[2] = 0.0;
        				} else {
        					//
        					shapleyVcgPayment[0] = g/12;
        					shapleyVcgPayment[1] = g/12;
        					shapleyVcgPayment[2] = g/12;
        				}
        			}
        		}
        	}
        	Double shapleyPaymentReferencePoint[] = new Double[3];
        	shapleyPaymentReferencePoint[0] = shapleyVcgPayment[0];
        	shapleyPaymentReferencePoint[1] = shapleyVcgPayment[1];
        	shapleyPaymentReferencePoint[2] = shapleyVcgPayment[2];
        	double delta = (bids[3] - shapleyPaymentReferencePoint[0] - shapleyPaymentReferencePoint[1] - shapleyPaymentReferencePoint[2])/3;
        	Double shapleyPayment[] = new Double[3];
        	shapleyPayment[0] = shapleyPaymentReferencePoint[0] + delta;
        	shapleyPayment[1] = shapleyPaymentReferencePoint[1] + delta;
        	shapleyPayment[2] = shapleyPaymentReferencePoint[2] + delta;
        	Double finalPayment[] = new Double[3];

        	
            if (shapleyPayment[0] > bids[0]) {
            	finalPayment[0] = bids[0];
            	delta = (bids[3] - bids[0] - shapleyPaymentReferencePoint[1] - shapleyPaymentReferencePoint[2])/2;
            	shapleyPayment[1] = shapleyPaymentReferencePoint[1] + delta;
            	shapleyPayment[2] = shapleyPaymentReferencePoint[2] + delta;
            	if (shapleyPayment[1] > bids[1]) {
            		finalPayment[1] = bids[1];
            		finalPayment[2] = bids[3] - bids[0] - bids[1];	
            	} else if (shapleyPayment[2] > bids[2]) {
            		finalPayment[2] = bids[2];
            		finalPayment[1] = bids[3] - bids[0] - bids[2];	
            	} else {
            		finalPayment[1] = shapleyPayment[1];
            		finalPayment[2] = shapleyPayment[2];
            	}
            } else if (shapleyPayment[1] > bids[1]) {
            	finalPayment[1] = bids[1];
            	delta = (bids[3] - bids[1] - shapleyPaymentReferencePoint[0] - shapleyPaymentReferencePoint[2])/2;
            	shapleyPayment[0] = shapleyPaymentReferencePoint[0] + delta;
            	shapleyPayment[2] = shapleyPaymentReferencePoint[2] + delta;
            	if (shapleyPayment[0] > bids[0]) {
            		finalPayment[0] = bids[0];
            		finalPayment[2] = bids[3] - bids[0] - bids[1];	
            	} else if (shapleyPayment[2] > bids[2]) {
            		finalPayment[2] = bids[2];
            		finalPayment[0] = bids[3] - bids[1] - bids[2];	
            	} else {
            		finalPayment[0] = shapleyPayment[0];
            		finalPayment[2] = shapleyPayment[2];
            	}
            } else if (shapleyPayment[2] > bids[2]) {
            	finalPayment[2] = bids[2];
            	delta = (bids[3] - bids[2] - shapleyPaymentReferencePoint[1] - shapleyPaymentReferencePoint[0])/2;
            	shapleyPayment[1] = shapleyPaymentReferencePoint[1] + delta;
            	shapleyPayment[0] = shapleyPaymentReferencePoint[0] + delta;
            	if (shapleyPayment[1] > bids[1]) {
            		finalPayment[1] = bids[1];
            		finalPayment[0] = bids[3] - bids[2] - bids[1];	
            	} else if (shapleyPayment[0] > bids[0]) {
            		finalPayment[0] = bids[0];
            		finalPayment[1] = bids[3] - bids[0] - bids[2];	
            	} else {
            		finalPayment[1] = shapleyPayment[1];
            		finalPayment[0] = shapleyPayment[0];
            	}
            } else {
        		finalPayment[0] = shapleyPayment[0];
            	finalPayment[1] = shapleyPayment[1];
        		finalPayment[2] = shapleyPayment[2];
            }
               

            if (finalPayment[i] > bids[i] + 0.00001) {
            	System.out.println("ss");
            	System.out.println(bids[0]);
            	System.out.println(bids[1]);
            }
            return v - finalPayment[i];
        }
    }
}


