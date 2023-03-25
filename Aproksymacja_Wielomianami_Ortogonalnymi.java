package Aproksymacja_Wielomianami_Ortogonalnymi;

import java.util.ArrayList;
import java.util.Scanner;

public class Aproksymacja_Wielomianami_Ortogonalnymi {

    public static double funkcja(double x) {
       //  return (double) Math.pow(Math.E, x);
        return (double) (Math.pow(x,3) + 2);
    }

    public static double fCi(double x, int n) { // p(x) * fi(x) * f(x)
        double wynik = 0;
            wynik = wagaLegendrea() * wielomianLegendrea(x, n) * funkcja(x);
        return wynik;
    }

    public static double calkafCi(double a, double b, int n) { //Metodą Trapezów
        int liczba = 20;
        double h;
        double wynik = 0;
        ArrayList<Double> wezly_x = new ArrayList();

        h = (b - a) / liczba;

        for (int i = 0; i < liczba + 1; i++) {
            double wezel;
            wezel = a + i * (h);
            wezly_x.add(wezel);
        }

        for (int i = 0; i < liczba + 1; i++) {
            if (i == 0 || i == liczba) {
                wynik += fCi(wezly_x.get(i), n) / 2;

            } else {
                wynik += fCi(wezly_x.get(i), n);
            }
        }

        wynik = h * wynik;

        return wynik;
    }

    public static double fLambda(double x, int n) {  //p(x) * (fi(x))**2
        double wynik = 0;
        wynik = wagaLegendrea() * Math.pow(wielomianLegendrea(x, n), 2);
        return wynik;
    }

    public static double calkafLambda(double a, double b, int n) { //Metodą Trapezów
        int liczba = 20;
        double h;
        double wynik = 0;
        ArrayList<Double> wezly_x = new ArrayList();


        h = (b - a) / liczba;

        for (int i = 0; i < liczba + 1; i++) {
            double wezel;
            wezel = a + i * (h);
            wezly_x.add(wezel);
        }
        for (int i = 0; i < liczba + 1; i++) {
            if (i == 0 || i == liczba) {
                wynik += fLambda(wezly_x.get(i), n) / 2;
            } else {
                wynik += fLambda(wezly_x.get(i), n);

            }
        }

        wynik = h * wynik;

        return wynik;
    }

    public static double wielomianLegendrea(double x, int n) {
        double wynik = 0;

        switch (n) {
            case 0: {
                wynik = 1;
                break;
            }
            case 1: {
                wynik = x;
                break;
            }
            case 2: {
                wynik = (3 * Math.pow(x, 2) - 1) / 2;
                break;
            }
            case 3: {
                wynik = (5 * Math.pow(x, 3) - 3 * x) / 2;
                break;
            }
            case 4: {
                wynik = (35 * Math.pow(x, 4) - 30 * Math.pow(x, 2) + 3) / 8;
                break;
            }
            case 5: {
                wynik = (63 * Math.pow(x, 5) - 70 * Math.pow(x, 3) + 15 * x) / 8;
                break;
            }
            case 6:{
                wynik = (231 * Math.pow(x, 6) - 315 * Math.pow(x, 4) + 105 * Math.pow(x, 2) - 5) / 16;
            }
            case 7:{
                wynik = (429 * Math.pow(x, 7) - 693 * Math.pow(x, 5) + 315 * Math.pow(x, 3) - 35 * x) / 16;
            }
            case 8:{
                wynik = (6435 * Math.pow(x, 8) - 12012 * Math.pow(x, 6) + 6930 * Math.pow(x, 4) - 1260 * Math.pow(x, 2) + 35) / 128;
            }
            case 9:{
                wynik = (12155 * Math.pow(x, 9) - 25740 * Math.pow(x, 7) + 18018 * Math.pow(x, 5) - 4620 * Math.pow(x, 3) + 315 * x) / 128;
            }
            case 10:{
                wynik = (46189 * Math.pow(x, 10) - 109395 * Math.pow(x, 8) + 90090 * Math.pow(x, 6) - 30030 * Math.pow(x, 4) + 3465 * Math.pow(x, 2) - 63) / 256;
            }
            case 11:{
                wynik = (88179 * Math.pow(x, 11) - 230945 * Math.pow(x, 9) + 218790 * Math.pow(x, 7) - 90090 * Math.pow(x, 5) + 15015 * Math.pow(x, 3) - 693 * x) / 256;
            }
//            default:{
//                System.out.println("Nieznany stopień Wielomianu Legendre'a!");
//            }
        }
        return wynik;
    }

    public static double wagaLegendrea() {
        return 1;
    } //waga p(x) = 1

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double wynik = 0;
        int n; //stopień
        double a, b; //granice całkowania
        double x;
        double[] lambda_tab, Ci_tab;

        System.out.print("Podaj dolny przedzial calkowania (a): ");
        a = sc.nextDouble();
        System.out.print("Podaj górny przedzial calkowania (b): ");
        b = sc.nextDouble();
        System.out.print("Podaj stopień wielomianu aproksymujacego (dostępne: 0 - 11): ");
        n = sc.nextInt();
        if(n > 11 || n < 0){
            System.out.print("Niepoprawny stopień! Podaj stopień wielomianu aproksymujacego jeszcze raz (dostępne: 0 - 11): ");
            n = sc.nextInt();
        }
        System.out.print("Podaj x: ");
        x = sc.nextDouble(); // x, w którym poszukujemy rozwiązania

        lambda_tab = new double[n + 1];
        Ci_tab = new double[n + 1]; //stałe współczynniki (Ci), które trzeba wyznaczyć

        for (int i = 0; i <= n; i++) { //wyznaczam Lambdy, korzystając z kwadratury Legendre'a
            lambda_tab[i] = calkafLambda(a, b, i);
        }

        for (int i = 0; i <= n; i++) { //wyznaczam Ci, korzystając z kwadratury Legendre'a
            Ci_tab[i] = (1 / (lambda_tab[i])) * calkafCi(a, b, i);
        }

        for (int i = 0; i <= n; i++) {
            wynik += Ci_tab[i] * wielomianLegendrea(x, i); //sumuję Ci * funkcja bazowa(fi)
        }
        System.out.println("WYNIK bezpośrednio z funkcji(bez aproksymacji): " + funkcja(x));
        System.out.println("WYNIK funkcji aproksymującej: " + wynik);
    }
}
