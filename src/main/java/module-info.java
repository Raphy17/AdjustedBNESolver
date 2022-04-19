module CA_BNE_Solver {
	exports ch.uzh.ifi.ce.cabne.verification;
	exports ch.uzh.ifi.ce.cabne.domains;
	exports ch.uzh.ifi.ce.cabne.domains.LLG;
	exports ch.uzh.ifi.ce.cabne.BR;
	exports ch.uzh.ifi.ce.cabne.pointwiseBR.updateRule;
	exports ch.uzh.ifi.ce.cabne.domains.FirstPriceLLG;
	exports ch.uzh.ifi.ce.cabne.examples;
	exports ch.uzh.ifi.ce.cabne.strategy;
	exports ch.uzh.ifi.ce.cabne.domains.LLLLGG;
	exports ch.uzh.ifi.ce.cabne.statisticaltests;
	exports ch.uzh.ifi.ce.cabne.algorithm;
	exports ch.uzh.ifi.ce.cabne.helpers.distributions;
	exports ch.uzh.ifi.ce.cabne.pointwiseBR;
	exports ch.uzh.ifi.ce.cabne.randomsampling;
	exports ch.uzh.ifi.ce.cabne.helpers;
	exports ch.uzh.ifi.ce.cabne.integration;

	requires commons.math3;
	requires jopt;
	requires opencsv;
}