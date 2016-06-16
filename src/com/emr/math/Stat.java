package com.emr.math;

import java.util.Random;

public class Stat {
	/*
	 * get random number between min and max
	 */
	public static double rand(double min, double max) {
		return Math.random() * (max - min) + min;
	}
	
	/*
	 * p(x) = N(x | mu, precision), where 
	 * precision is the inverse of covariance
	 */
	public static double gaussianProb(double[] x,
			double[] mu, double[][] precision) {
		int p = mu.length;
		double det = Matrix.det(precision);
		double scale = Math.sqrt(det) / 
				Math.pow(2 * Math.PI, p / 2.0);
		
		double[] delta = new double[p];		// x - mu
		for (int i = 0; i < p; i++) {
			delta[i] = x[i] - mu[i];
		}
		double[] c = new double[p];
		Matrix.multiply(precision, delta, c);
		double[] dist = new double[1];
		Matrix.multiply(delta, c, dist);
		return scale * Math.exp(-0.5 * dist[0]);
	}
	
	/*
	 * generate random variables from gaussian distribution
	 */
	public static double gaussianRandom(double mu, double sigma) {
		Random r = new Random();
		return mu + r.nextGaussian() * sigma;
	}
	
	public static double[] gaussianRandom(double[] mu, double[][] sigma) {
		int n = mu.length;
		double[] rand = new double[n];
		double[][] cd = Matrix.choleskyDecompose(sigma);
		
		double[] z = new double[n];
		for (int i = 0; i < n; i++) {
			z[i] = gaussianRandom(0, 1);
		}
		
		double[] cov = new double[n];
		Matrix.multiply(cd, z, cov);
		
		for (int i = 0; i < n; i++) {
			rand[i] = mu[i] + cov[i];
		}
		
		return rand;
	}

}
