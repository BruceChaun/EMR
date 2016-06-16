package com.emr.math;

import com.emr.entity.Data;

public class Matrix {
	/*
	 * get max and min for each attribute
	 */
	public static void getColRange(Data[] x, double[][] range) {
		int n = x.length;
		int p = x[0].data.length;
		for (int i = 0; i < p; i++) {
			range[i][0] = Double.NEGATIVE_INFINITY;
			range[i][1] = Double.POSITIVE_INFINITY;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				double value = x[n].data[j];
				if (value > range[j][0])
					range[j][0] = value;
				if (value < range[j][1])
					range[j][1] = value;
			}
		}
	}
	
	/*
	 * set covariance as identity matrix
	 */
	public static void setIdentity(double[][] m) {
		int R = m.length;
		int C = m[0].length;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (r == c)
					m[r][c] = 1;
				else 
					m[r][c] = 0;
			}
		}
	}

	/*
	 * calculate the determinant of matrix
	 */
	public static double det(double[][] mat) {
		int n = mat.length;
		double[][] m = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				m[i][j] = mat[i][j];
			}
		}
		
		int sign = 0;
		
		for (int j = 0; j < n; j++) {
			double max = Math.abs(m[j][j]);
			int row = j;
			for (int i = j+1; i < n; i++) {
				double v = Math.abs(m[i][j]);
				if (v > max) {
					max = v;
					row = i;
				}
			}
			if (row != j) {
				swapRow(m, j, row);
				sign++;
			}
			
			for (int i = j+1; i < n; i++) {
				double scale = m[i][j] / m[j][j];
				for (int k = 0; k < n; k++) {
					m[i][k] -= m[j][k] * scale;
				}
			}
		}
 
		double sum = 1.0;
		if (sign % 2 != 0)
			sum = -sum;
		for (int i = 0; i < n; i++) {
			sum *= m[i][i];
		}
		return sum;
	}
	
	
	private static void swapRow(double[][] m, int i, int j) {
		double[] tmp = m[i];
		m[i] = m[j];
		m[j] = tmp;
	}
	
	/*
	 * calculate the inverse of matrix
	 * refer to: http://www.sanfoundry.com/java-program-find-inverse-matrix/
	 */
	public static double[][] inverse(double m[][]) 
    {
        int n = m.length;
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = m[i][j];
			}
		}
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
        // Transform the matrix into an upper triangle
        gaussian(a, index);
 
        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
        // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
 
	/*
	 *  Method to carry out the partial-pivoting Gaussian
	 *  elimination.  Here index[] stores pivoting order.
	 */
    private static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
        // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
    
    /*
     * calculate transpose of matrix
     */
    public static double[][] transpose(double[][] m) {
    	int r = m.length;
    	int c = m[0].length;
    	double[][] m_t = new double[c][r];
    	for (int i = 0; i < r; i++) {
    		for (int j = 0; j < c; j++) {
    			m_t[j][i] = m[i][j];
    		}
    	}
    	return m_t;
    }
    
    /*
     * multiplication of two matrices
     */
    public static boolean multiply(double[][] a, double[][] b, double[][] c) {
    	int rowA = a.length;
    	if (rowA == 0)
    		return false;
    	
    	int colA = a[0].length;
    	int rowB = b.length;
    	if (colA != rowB)
    		return false;
    	
    	int colB = b[0].length;
    	
    	for (int i = 0; i < rowA; i++) {
    		for (int j = 0; j < colB; j++) {
    			c[i][j] = 0.0;
    			for (int k = 0; k < colA; k++) {
    				c[i][j] += a[i][k] * b[k][j];
    			}
    		}
    	}
    	return true;
    }
    
    public static boolean multiply(double[][] a, double[] b, double[] c) {
    	int rowA = a.length;
    	if (rowA == 0)
    		return false;
    	
    	int colA = a[0].length;
    	int rowB = b.length;
    	if (colA != rowB)
    		return false;
    	
    	for (int i = 0; i < rowA; i++) {
    		double[] res = new double[1];
    		multiply(a[i], b, res);
    		c[i] = res[0];
    	}
    	return false;
    }
    
    public static boolean multiply(double[] a, double[] b, double[] c) {
    	int lenA = a.length;
    	int lenB = b.length;
    	if (lenA != lenB)
    		return false;
    	
    	c[0] = 0.0;
    	for (int i = 0; i < lenA; i++) {
    		c[0] += a[i] * b[i];
    	}
    	return true;
    }
    
    /*
     * Cholesky decomposistion
     * return L s.t. m = L * L^T, 
     * where L is a lower triangle matrix
     * refer to: https://www.youtube.com/watch?v=NppyUqgQqd0
     */
    public static double[][] choleskyDecompose(double[][] m) {
    	int n = m.length;
    	double[][] L = new double[n][n];
    	
    	for (int k = 0; k < n; k++) {
    		for (int i = 0; i <= k; i++) {
    			double sum = 0.0;
    			for (int j = 0; j < i; j++) {
    				sum += L[i][j] * L[k][j];
    			}
    			double l = m[k][i] - sum;
    			if (k == i)
    				l = Math.sqrt(l);
    			else 
    				l /= L[i][i];
    			L[k][i] = l;
    		}
    	}
    	
    	return L;
    }

}
