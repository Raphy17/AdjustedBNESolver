package ch.uzh.ifi.ce.cabne.examples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import ch.uzh.ifi.ce.cabne.BR.AdaptivePWLBRCalculator;
import ch.uzh.ifi.ce.cabne.BR.PWLBRCalculator;
import ch.uzh.ifi.ce.cabne.algorithm.BNEAlgorithm;
import ch.uzh.ifi.ce.cabne.algorithm.BNEAlgorithmCallback;
import ch.uzh.ifi.ce.cabne.algorithm.BNESolverContext;
import ch.uzh.ifi.ce.cabne.domains.TLG.TLGSampler;
import ch.uzh.ifi.ce.cabne.domains.Mechanism;
import ch.uzh.ifi.ce.cabne.domains.TLG.ShapleyWithout;
import ch.uzh.ifi.ce.cabne.integration.MCIntegrator;
import ch.uzh.ifi.ce.cabne.pointwiseBR.PatternSearch;
import ch.uzh.ifi.ce.cabne.pointwiseBR.UnivariatePattern;
import ch.uzh.ifi.ce.cabne.pointwiseBR.updateRule.UnivariateDampenedUpdateRule;
import ch.uzh.ifi.ce.cabne.randomsampling.CommonRandomGenerator;
import ch.uzh.ifi.ce.cabne.strategy.UnivariatePWLStrategy;
import ch.uzh.ifi.ce.cabne.verification.BoundingVerifier1D;
import ch.uzh.ifi.ce.cabne.verification.EstimatingVerifier1D;


public class Test {
	
	public static void main(String[] args) throws InterruptedException, IOException {		

		ShapleyWithout mechanism = new ShapleyWithout();
		
//		Double bidlist[][] = {{0.1, 0.2, 0.2, 1.0}, {0.3, 0.4, 0.5, 1.0},  {0.1, 0.6, 0.5, 1.0},  {0.6, 0.1, 0.5, 1.0},  {0.7, 0.4, 0.2, 1.0},  {0.3, 0.4, 0.9, 1.0},  {0.3, 0.8, 0.5, 1.0},  {0.7, 0.4, 0.5, 1.0},  {0.9, 0.8, 0.6, 1.0}};
//		for (Double bids[] : bidlist) {
//			int i = 0;
//			Double v = bids[i];
//			double res = mechanism.computeUtility(i,  v,  bids);
//			System.out.println(res);
//			System.out.println("----");
//		}
		Double bids[] = {0.1, 0.2, 1.2, 1.0};
		Double shapleyPaymentReferencePoint[] = new Double[3];
    	shapleyPaymentReferencePoint[0] = 0.5;
    	shapleyPaymentReferencePoint[1] = 0.7;
    	shapleyPaymentReferencePoint[2] = 0.9;
    	double delta = (bids[3] - shapleyPaymentReferencePoint[0] - shapleyPaymentReferencePoint[1] - shapleyPaymentReferencePoint[2])/3;
    	Double shapleyPayment[] = new Double[3];
    	shapleyPayment[0] = shapleyPaymentReferencePoint[0] + delta;
    	shapleyPayment[1] = shapleyPaymentReferencePoint[1] + delta;
    	shapleyPayment[2] = shapleyPaymentReferencePoint[2] + delta;
    	Double finalPayment[] = new Double[3];
    	System.out.println(shapleyPayment[0]);
		System.out.println(shapleyPayment[1]);
		System.out.println(shapleyPayment[2]);
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
		System.out.println(finalPayment[0]);
		System.out.println(finalPayment[1]);
		System.out.println(finalPayment[2]);
//		double s0 = (3*a+b+c-g)/4;
//		System.out.println(s0);

	
		
	
	
			
		
		
		
    }
}
