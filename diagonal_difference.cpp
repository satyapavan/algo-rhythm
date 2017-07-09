/*
Problem Statement
Given a square matrix of size NxN, calculate the absolute difference between the sums of its diagonals.

Input Format
The first line contains a single integer, N. The next N lines denote the matrix's rows, with each line containing N space-separated integers describing the columns.

Output Format
Print the absolute difference between the two sums of the matrix's diagonals as a single integer.

source: https://www.hackerrank.com/challenges/diagonal-difference/problem

*/

#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main(){
    int n;
    cin >> n;
    vector< vector<int> > a(n,vector<int>(n));
    
    int s_a=0, s_b=0;
    
    for(int a_i = 0;a_i < n;a_i++){
       for(int a_j = 0;a_j < n;a_j++){
           int currV;
           cin >> currV;
           
           if (a_i == a_j)
               s_a += currV;
               
           // This is a beautiful implementation 
           if (a_i + a_j == (n-1) )
               s_b += currV;
       }
    }
    
    cout << abs(s_a - s_b) << endl;
    
    return 0;
}
