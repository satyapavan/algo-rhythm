/*

Colleen is turning n years old! Therefore, she has n candles of various heights on her cake, and candle  has height . Because the taller candles tower over the shorter ones, Colleen can only blow out the tallest candles.

Given the height for each individual candle, find and print the number of candles she can successfully blow out.

Input Format
The first line contains a single integer, n, denoting the number of candles on the cake. 
The second line contains n space-separated integers, where each integer i describes the height of candle i .

Output Format
Print the number of candles Colleen blows out on a new line.

Sample Input 0

4
3 2 1 3

Sample Output 0

2

*/



#include <bits/stdc++.h>

using namespace std;

int birthdayCakeCandles(int n, vector <int> ar) {
    set<int> setHeights(ar.begin(), ar.end());
    n = 0;
    
    int tallestCandleHeight = * setHeights.end();
    
    for(vector<int>::iterator itr = ar.begin(); itr != ar.end() ; itr++){
        if ( * itr == tallestCandleHeight )
            n++;
    }
    
    return n;
}

int main() {
    int n;
    cin >> n;
    
    int current_height = 0;
    int tallest = 0;
    int tallest_count = 0;
    
    for(int itr = 0; itr < n; itr++){
       cin >>  current_height;
        
        if ( current_height > tallest ) {
            tallest = current_height;
            tallest_count = 1;
        } else if ( current_height == tallest ) {
            tallest_count++;
        }
    }
        
    cout << tallest_count << endl;
    return 0;
}
