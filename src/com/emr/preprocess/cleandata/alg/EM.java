package com.emr.preprocess.cleandata.alg;

import com.emr.entity.Data;
import com.emr.math.Matrix;
import com.emr.math.Stat;

public class EM {
	private static int K = 3;	// # mixture Gaussian
	private static int N;		// # data set
	private static int P;		// # attributes per data
	private static final double eps = 0.0001;
	
	private static double[][] gamma = new double[N][K];
	private static double[][] oldGamma = new double[N][K];
	
	public static void train(Data[] x) {
		N = x.length;
		P = x[0].data.length;
		
		double[] pi = new double[K];
		double[][] mu = new double[K][P];
		double[][][] sigma = new double[K][P][P];
		init(x, pi, mu, sigma);
		fillMissingData(x, pi, mu, sigma);
		
		double[] gammaSum = new double[K];
		
		while (true) {
			estep(x, pi, mu, sigma, gammaSum);
			if (converge())
				break;
			
			mstep(x, pi, mu, sigma, gammaSum);
			fillMissingData(x, pi, mu, sigma);
		}
	}
	
	private static void init(Data[] x, double[] pi, 
			double[][] mu, double[][][] sigma) {
		double[][] range = new double[P][2];
		Matrix.getColRange(x, range);
		
		for (int k = 0; k < K; K++) {
			pi[k] = 1.0 / P;
			
			Matrix.setIdentity(sigma[k]);
			
			for (int p = 0; p < P; p++) {
				mu[k][p] = Stat.rand(range[p][1], range[p][0]);
			}
		}
	}
	
	/*
	 * estimate normalized 
	 * gamma[n][k] = pi[k] * N(x[n] | mu[k], sigma[k]), 
	 * with norm \sum_j pi[j] * N(x[n] | mu[j], sigma[j])
	 */
	private static void estep(Data[] x, double[] pi, 
			double[][] mu, double[][][] sigma, double[] gammaSum) {
		oldGamma = gamma;
		for (int n = 0; n < N; n++) {
			double norm = 0.0;
			for (int k = 0; k < K; k++) {
				double prob = Stat.gaussianProb(x[n].data, mu[k], sigma[k]);
				gamma[n][k] = prob * pi[k];
				norm += gamma[n][k];
			}
			
			for (int k = 0; k < K; k++) {
				gamma[n][k] /= norm;
				gammaSum[k] += gamma[n][k];
			}
		}
	}
	
	/*
	 * estimate pi, mu, sigma with new gamma
	 */
	private static void mstep(Data[] x, double[] pi, double[][] mu, 
			double[][][] sigma, double[] gammaSum) {
		// update pi
		for (int k = 0; k < K; k++) {
			pi[k] = gammaSum[k] / N;
		}

		// update mu
		for (int k = 0; k < K; k++) {
			for (int p = 0; p < P; p++) {
				mu[k][p] = 0.0;
			}
			
			for (int n = 0; n < N; n++) {
				for (int p = 0; p < P; p++) {
					mu[k][p] += gamma[n][k] * x[n].data[p] / gammaSum[k];
				}
			}
		}

		// update sigma
		for (int k = 0; k < K; k++) {
			for (int i = 0; i < P; i++) {
				for (int j = 0; j < P;j++) {
					sigma[k][i][j] = 0.0;
				}
			}
			
			for (int n = 0; n < N; n++) {
				for (int i = 0; i < P; i++) {
					for (int j = 0; j < P;j++) {
						double di = x[n].data[i] - mu[k][i];
						double dj = x[n].data[j] - mu[k][j];
						sigma[k][i][j] = 
								(gamma[n][k] * di * dj) / gammaSum[k];
					}
				}
			}
			
			// calculate precision
			sigma[k] = Matrix.inverse(sigma[k]);
		}
	}
	
	private static boolean converge() {
		for (int n = 0; n < N; n++) {
			for (int k = 0; k < K; k++) {
				if (Math.abs(gamma[n][k] - oldGamma[n][k]) > eps)
					return false;
			}
		}
		return true;
	}
	
	private static void fillMissingData(Data[] x, double[] pi, 
			double[][] mu, double[][][] sigma) {
		for (int n = 0; n < N; n++) {
			if (!x[n].isComplete()) {
				double[][] generated = new double[K][P];
				for (int k = 0; k < K; k++) {
					generated[k] = Stat.gaussianRandom(mu[k], sigma[k]);
				}
				
				int[] index = x[n].findMissingIndex();
				for (int i = 0; i < index.length; i++) {
					double estimate = 0.0;
					int p = index[i];
					for (int k = 0; k < K; k++) {
						estimate += pi[k] * generated[k][p];
					}
					x[n].data[p] = estimate;
				}
			}
		}
	}

}
