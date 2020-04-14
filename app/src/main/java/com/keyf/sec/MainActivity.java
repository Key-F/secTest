package com.keyf.sec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Threat t1 = new Threat("Заражение", 10, 0.08);
        Threat t2 = new Threat("Кража данных", 20, 0.1);
        Threat t3 = new Threat("Потеря данных", 25, 0.3);
        Threat t4 = new Threat("Нежелательное списание со счета", 10, 0.4);

        Threat[] threats = {t1,t2,t3,t4};

        Application p1 = new Application("a1", 300, 150, Arrays.asList(new Double[]{0.4, 0.8, 0.9, 0.7}));
        Application p2 = new Application("a2", 70, 45, Arrays.asList(new Double[]{0.1, 0.5, 0.1, 0.0}));
        Application p3 = new Application("a3", 80, 66, Arrays.asList(new Double[]{0.1, 0.1, 0.0, 0.8}));

        Application[] applications = {p1,p2,p3};

        int maxSize = 250;



        int[] vect = {0,1,1};
        double hh = damagePrevention(applications,threats,vect);
    //    String h = Double.toString(hh);
        double cc = costCount(applications,vect);



        //double total = hh * 0.8 + cc * 0.1;
        int[] vect2 = {1,1,1};
        double total2 = damagePrevention(applications,threats,vect2) * 0.8 + costCount(applications,vect2) * 0.1;
        Log.d("jj", Double.toString(total2));
       // Log.d("jj",h);
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(5));
        tryAll(0.7, 0.1, 3, applications, threats, maxSize);
        int[] best = tryMaxDMG(3, applications, threats, maxSize);

        int KnapCoeff = forKnapSack(applications, maxSize);

        System.out.println(Arrays.toString(tryMinCost(3, applications, threats, 0.2 * damagePrevention(applications,threats,best))));
        System.out.println(tryMinCost(3, applications, threats, 0.1 * damagePrevention(applications,threats,best)));


    }

    private int forKnapSack(Application[] applications, int maxWeight){
        int n = applications.length;
        int[] cost = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = applications[i].getCost();
            size[i] = applications[i].getSize();
        }
        return Knapsack.knapSack(size, cost, n, maxWeight);
    }

    private int[] tryAll(double damageWeight, double costWeight, int prgrmCount, Application[] applications, Threat[] threats, int maxSize){
        int[] matrix = new int[prgrmCount];
        int[] bestMatrix = new int[prgrmCount];
        double total;
        double maxCoeff = 0;
        for (int i = 0; i < Math.pow(2,prgrmCount); i++) {
            int binary = Integer.parseInt(Integer.toBinaryString(i));
            for (int j = 0; j < prgrmCount; j++){
                matrix[prgrmCount - 1 - j] = binary % 10 ;
                binary /= 10;
            }
            System.out.println("Vector : " + Arrays.toString(matrix));
            total = damagePrevention(applications,threats,matrix) * damageWeight - costCount(applications,matrix) * costWeight;
            System.out.println("total:: " + total);
            if ((total > maxCoeff) && (weightCount(applications, matrix) < maxSize)) {
                maxCoeff = total;
                bestMatrix = matrix.clone();
            }
        }
        System.out.println("Линейная светка, полный перебор bestMatrix: " + Arrays.toString(bestMatrix) + " maxCoeff: " + maxCoeff);
        return bestMatrix;

    }

    private int[] tryMaxDMG(int prgrmCount, Application[] applications, Threat[] threats, int maxSize) {
        int[] matrix = new int[prgrmCount];
        int[] bestMatrix = new int[prgrmCount];
        double total;
        double maxCoeff = 0;
        for (int i = 0; i < Math.pow(2,prgrmCount); i++) {
            int binary = Integer.parseInt(Integer.toBinaryString(i));
            for (int j = 0; j < prgrmCount; j++){
                matrix[prgrmCount - 1 - j] = binary % 10 ;
                binary /= 10;
            }
            System.out.println("Vector : " + Arrays.toString(matrix));
            total = damagePrevention(applications,threats,matrix);
            System.out.println("total:: " + total);
            if ((total > maxCoeff) && (weightCount(applications, matrix) < maxSize)) {
                maxCoeff = total;
                bestMatrix = matrix.clone();
            }
        }
        System.out.println("Уступки, первая итерация, полный перебор bestMatrix: " + Arrays.toString(bestMatrix) + " maxCoeff: " + maxCoeff);
        return bestMatrix;
    }

    private int[] tryMinCost(int prgrmCount, Application[] applications, Threat[] threats, double delta) {
        int[] matrix = new int[prgrmCount];
        int[] bestMatrix = new int[prgrmCount];
        double total;
        double dmg = 0;
        double minCost = 1;
        for (int i = 0; i < Math.pow(2,prgrmCount); i++) {
            int binary = Integer.parseInt(Integer.toBinaryString(i));
            for (int j = 0; j < prgrmCount; j++){
                matrix[prgrmCount - 1 - j] = binary % 10 ;
                binary /= 10;
            }
            System.out.println("Vector : " + Arrays.toString(matrix));
            total = costCount(applications, matrix);
            System.out.println("total:: " + total);
            dmg = damagePrevention(applications,threats,matrix);
            System.out.println("minCost:: " + minCost + " dmg: " + dmg + " delta: " + delta);
            if ((total < minCost) && (dmg > delta)) {
                minCost = total;
                //System.out.println("minCost:: " + minCost + "dmg: " + dmg + " delta: " + delta);
//                for (int gg = 0; gg < 3; gg++)
//                    bestMatrix[gg]=matrix[gg];
//                   // System.out.println(bestMatrix[gg]+gg);
                bestMatrix = matrix.clone();
                System.out.println(Arrays.toString(bestMatrix));
            }
            System.out.println(" bestMatrix: " + Arrays.toString(bestMatrix));
        }
        double maxCoeff = dmg - minCost;
        System.out.println("Уступки, полный перебор bestMatrix: " + Arrays.toString(bestMatrix) + " maxCoeff: " + maxCoeff);
        return bestMatrix;
    }




        private double damagePrevention(Application[] applications, Threat[] threats, int[] installedVector) {
        double damagePrevented = 0;
        double maxDamage = 0; // Для нормирования
        for (int j = 0; j < threats.length; j++) {
            maxDamage += threats[j].getDamage();
            double maxProbabilityOfProtect = 0;
            for (int i = 0; i < applications.length; i++) {
                double probabilityOfProtect = applications[i].getProbabilityOfThreatProtection(j) * installedVector[i];
                if (probabilityOfProtect >= maxProbabilityOfProtect)
                    maxProbabilityOfProtect = probabilityOfProtect;
            }
            damagePrevented = damagePrevented + threats[j].getProbabilityOfAppearance() * threats[j].getDamage() * maxProbabilityOfProtect;
            //Log.d("myTag", "This is my message");
        }
        return damagePrevented / maxDamage;
    }

    private double costCount(Application[] applications, int[] installedVector){
        double cost = 0;
        double totalCost = 0; // Для нормирования
        for (int i = 0; i < applications.length; i++) {
            cost += applications[i].getCost() * installedVector[i];
            totalCost += applications[i].getCost();
        }
        return cost / totalCost;
    }

    private double weightCount(Application[] applications, int[] installedVector){
        double weight = 0;
        for (int i = 0; i < applications.length; i++)
            weight += applications[i].getSize() * installedVector[i];
        return weight;
    }






    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("object.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
