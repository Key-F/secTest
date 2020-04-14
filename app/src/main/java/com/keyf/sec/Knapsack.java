package com.keyf.sec;

public class Knapsack {

    // A utility function that returns maximum of two integers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack(int size[], int val[], int n, int maxSize) {
        int i, w;
        int K[][] = new int[n + 1][maxSize + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= maxSize; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (size[i - 1] <= w)
                    K[i][w] = max(val[i - 1] + K[i - 1][w - size[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][maxSize];
    }
}