/*
Given a time in 12-hour AM/PM format, convert it to military (24-hour) time.

Note: Midnight is 12:00:00AM on a 12-hour clock, and  00:00:00 on a 24-hour clock. Noon is 12:00:00PM on a 12-hour clock, and 12:00:00 on a 24-hour clock.

Input Format

A single string containing a time in 12-hour clock format (i.e.: hh:mm:ssAM or hh:mm:ssPM), where 01<=hh<=12 and 00<=mm,ss<=59

Output Format

Convert and print the given time in 24-hour format, where 00<=hh<=23

Sample Input
07:05:45PM

Sample Output
19:05:45

*/

#include <bits/stdc++.h>

using namespace std;

string timeConversion(string s) {

    string am_pm = s.substr(8,2);
    
    string hh = s.substr(0,2);

    if ( hh == "12" )
       hh = "00";
  
    if ( am_pm == "PM" )
        hh = to_string(stoi(hh) + 12);
        
    return hh + s.substr(2,6);
}

int main() {
    string s;
    cin >> s;
    string result = timeConversion(s);
    cout << result << endl;
    return 0;
}
