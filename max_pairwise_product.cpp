#include <cstdlib>
#include <iostream>
#include <vector>

using std::vector;
using std::cin;
using std::cout;

/*
    This method makes use of the bruteforce or the n*n time solution.
    This works, but is a YUCK!! solution.
    For the stress testing, this is going to be our alternative solution
*/
long long MaxPairwiseProductBruteForce(const vector<int>& numbers)
{
    long long result = 0;
    int n = numbers.size();
    for (int i = 0; i < n; ++i)
    {
        for (int j = i + 1; j < n; ++j)
        {
            if (((long long)(numbers[i])) * numbers[j] > result)
            {
                result = ((long long)(numbers[i])) * numbers[j];
            }
        }
    }
    return result;
}

/*
This is the submitted solution.
Idea is, max pairwise product is always going to be the product of 2 biggest numbers in the array.
So 1st, we identify the biggest number and then we identify the 2nd biggest number
and behold, you multiple them and you have the answer.
*/
long long MaxPairwiseProductFast(const vector<int>& numbers)
{
    int n = numbers.size();

    int max_index1 = -1;
    for (int i = 0; i < n; ++i)
        if ((max_index1 == -1) || (numbers[i] > numbers[max_index1]))
            max_index1 = i;

    int max_index2 = -1;
    for (int j = 0; j < n; ++j)
        /*
        By now, you might be wondering why are we storing the index values rather than the actual numbers (which is a faster mode of implementation as we are avoiding an additional lookup through the vector).
        The reason to do that is a rookie.
        lets say the inputs are, 2 9 3 1 9
        Brute force says the value is 9*9 = 81
        But when we store the numbers, we will store them as 9 (biggest) and 3 (next biggest) which results in an incorrect solution of 27.
        The reason is, we have duplicate values of 9 and when we are storing the value and not the place holder, we have a bug in our logic.

        9 != 9 is false (when we store actual values)
        1 != 4 is true (when we consider index values)

        By the time i am writing this, i truly loved this idea as i have been making this mistake at all places so far. yay!!
        */
        if ((j != max_index1) && ((max_index2 == -1) || (numbers[j] > numbers[max_index2])))
            max_index2 = j;

    //cout << max_index1 << ' ' << max_index2 << "\n";

    return ((long long)(numbers[max_index1])) * numbers[max_index2];
}

void stress_test()
{
    while (true)
    {
        int n = rand() % 1000 + 2;
        cout << n << "\n";
        vector<int> a;
        for (int i = 0; i < n; ++i)
        {
            a.push_back(rand() % 100000);
        }
        for (int i = 0; i < n; ++i)
        {
            cout << a[i] << ' ';
        }
        cout << "\n";
        long long res1 = MaxPairwiseProductBruteForce(a);
        long long res2 = MaxPairwiseProductFast(a);
        if (res1 != res2)
        {
            cout << "Wrong answer: " << res1 << ' ' << res2 << "\n";
            break;
        }
        else
        {
            cout << "OK\n";
        }
    }
}

/*
sample input is
6
3 4 5 7 3 4

output is supposed to be
35

explanation,
3*4 3*5 3*7 3*3 3*4
    4*5 4*7 4*3 4*4
        5*7 5*3 5*4
            7*3 7*4
                3*4

Of all these combinations, 7*5 is having the highest value of 35.

*/

int main()
{
    stress_test();

    int n;
    cin >> n;
    vector<int> numbers(n);
    for (int i = 0; i < n; ++i)
    {
        cin >> numbers[i];
    }

    long long result = MaxPairwiseProductFast(numbers);
    cout << result << "\n";
    return 0;
}
