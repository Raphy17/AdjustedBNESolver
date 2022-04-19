package ch.uzh.ifi.ce.cabne.domains.TLG;

import ch.uzh.ifi.ce.cabne.domains.Mechanism;


public class ShapleyWithout implements Mechanism<Double, Double> {

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
//        					System.out.println("XYZ");
        					shapleyPayoff[0] = (3*a+b+c-g)/4;
        					shapleyPayoff[1] = (a+3*b+c-g)/4;
        					shapleyPayoff[2] = (a+b+3*c-g)/4;
        				} else {
        					//XY
//        					System.out.println("XY");
        					shapleyPayoff[0] = 3.0/4*a;
        					shapleyPayoff[1] = a/4+5.0/6*b+c/3-g/3;
        					shapleyPayoff[2] = a/4+b/3+5.0/6*c-g/3;
        				}        				
        			} else {
        				//X
        				if (g > b + c) {
        					//XZ
//        					System.out.println("XZ");
        					shapleyPayoff[0] = 5.0/6*a+b/4+c/3-g/3;
        					shapleyPayoff[1] = 3.0/4*b;
        					shapleyPayoff[2] = a/3+b/4+5.0/6*c-g/3;
        				} else {
        					//X
//        					System.out.println("X");
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
//    						System.out.println("YZ");
    						shapleyPayoff[0] = 5.0/6*a+b/3+c/4-g/3;
        					shapleyPayoff[1] = a/3+5.0/6*b+c/4-g/3;
        					shapleyPayoff[2] = 3.0/4*c;
    					} else {
    						//Y
//    						System.out.println("Y");
    						shapleyPayoff[0] = 5.0/6*a+b/12-g/12;
    						shapleyPayoff[1] = a/3+11.0/12*b+c/3-5.0/12*g;
        					shapleyPayoff[2] = 5.0/6*c+b/12-g/12;
    					}
    				} else {
    					//
    					if (g > b + c) {
    						//Z
//    						System.out.println("Z");
    						shapleyPayoff[0] = 11.0/12*a+b/3+c/3-5.0/12*g;
        					shapleyPayoff[1] = 5.0/6*b+a/12-g/12;
        					shapleyPayoff[2] = 5.0/6*c+a/12-g/12;
    					} else {
    						//
//    						System.out.println("-");
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
//        					System.out.println("xyq");
        					shapleyPayoff[0] = 5.0/6*a;
        					shapleyPayoff[1] = 5.0/6*b;
        					shapleyPayoff[2] = 5.0/6*c;
        				} else {
        					//XY
//        					System.out.println("xy");
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
//        						System.out.println("xzq");
        						shapleyPayoff[0] = 5.0/6*a;
            					shapleyPayoff[1] = a/3+b+c/3-g/2;
            					shapleyPayoff[2] = 5.0/6*c;
            				} else {
            					//XZ
//            					System.out.println("xz");
            					shapleyPayoff[0] = 11.0/12*a+c/12-g/12;
            					shapleyPayoff[1] = a/12+b+c/12-g/4;
            					shapleyPayoff[2] = 11.0/12*c+a/12-g/12;
            				}
        				} else {
        					//X
//        					System.out.println("x");
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
//        						System.out.println("yzq");
        						shapleyPayoff[0] = a+b/3+c/3-g/2;
            					shapleyPayoff[1] = 5.0/6*b;
            					shapleyPayoff[2] = 5.0/6*c;
            				} else {
            					//YZ
//            					System.out.println("yz");
            					shapleyPayoff[0] = a+b/12+c/12-g/4;
            					shapleyPayoff[1] = 11.0/12*b+c/12-g/12;
            					shapleyPayoff[2] = 11.0/12*c+b/12-g/12;

            				}
        				} else {
        					//Y
//        					System.out.println("y");
        					shapleyPayoff[0] = a+b/12-g/6;
        					shapleyPayoff[1] = 11.0/12*b;
        					shapleyPayoff[2] = b/12+c-g/6;
        				}
        			} else {
        				//
        				if (g > c) {
        					//Z
//        					System.out.println("z");
        					shapleyPayoff[0] = a+c/12-g/6;
        					shapleyPayoff[1] = b+c/12-g/6;
        					shapleyPayoff[2] = 11.0/12*c;
        				} else {
        					//
//        					System.out.println("--");
        					shapleyPayoff[0] = a-g/12;
        					shapleyPayoff[1] = b-g/12;
        					shapleyPayoff[2] = c-g/12;
        				}
        			}
        		}
        	}
        	Double shapleyPaymentReferencePoint[] = new Double[3];
        	shapleyPaymentReferencePoint[0] = bids[0] - shapleyPayoff[0];
        	shapleyPaymentReferencePoint[1] = bids[1] - shapleyPayoff[1];
        	shapleyPaymentReferencePoint[2] = bids[2] - shapleyPayoff[2];
        	if (shapleyPayoff[0] < 0 | shapleyPayoff[1] < 0 | shapleyPayoff[2] < 0) {
        		System.out.println("xxxx");
        	}
        	if (shapleyPaymentReferencePoint[0] < 0 | shapleyPaymentReferencePoint[1] < 0 | shapleyPaymentReferencePoint[2] < 0) {
        		System.out.println("qqqq");
        	}

        	double delta = (bids[3] - shapleyPaymentReferencePoint[0] - shapleyPaymentReferencePoint[1] - shapleyPaymentReferencePoint[2])/3;
        	Double shapleyPayment[] = new Double[3];
        	shapleyPayment[0] = shapleyPaymentReferencePoint[0] + delta;
        	shapleyPayment[1] = shapleyPaymentReferencePoint[1] + delta;
        	shapleyPayment[2] = shapleyPaymentReferencePoint[2] + delta;
        	Double finalPayment[] = new Double[3];
//        	System.out.println(i + "|"+ v);
//        	System.out.println(bids[0]+ "|" + bids[1] +"|"+ bids[2] + "|" + bids[3]);
//        	System.out.println(shapleyPayoff[0]+ "|" + shapleyPayoff[1] +"|"+ shapleyPayoff[2]);
//        	System.out.println(shapleyPaymentReferencePoint[0]+ "|" + shapleyPaymentReferencePoint[1] +"|"+ shapleyPaymentReferencePoint[2]);
//        	System.out.println(shapleyPayment[0]+ "|" + shapleyPayment[1] +"|"+ shapleyPayment[2]);
//        	System.out.println(delta);
//        	System.out.println("----------");
        	//Checking irrationality constraints
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
               
            
         
//            if (finalPayment[i] > bids[i]) {
//            	
//            }
//        	System.out.println(i);
//        	System.out.println(bids[0]+ "|" + bids[1] +"|"+ bids[2] + "|" + bids[3]);
//        	System.out.println(finalPayment[0]+ "|" + finalPayment[1] +"|"+ finalPayment[2]);
//            System.out.println("----------");
            

            return v - finalPayment[i];
        }
    }
}


