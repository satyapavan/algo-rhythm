#!/bin/python3

import sys

arr = list(map(int, input().strip().split(' ')))
max=-sys.maxsize-1
min=sys.maxsize
sum=0
for x in arr:
    sum+=x
    if x>max:
        max=x
    if x<min:
        min=x
print (sum-max,sum-min)

## Or just sort them and then calculate the SUM(1:4) for MIN and SUM(2:5) for MAX values, awesome!!

# Problem Statement: Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.
# Input Format: A single line of five space-separated integers.
# https://www.hackerrank.com/challenges/mini-max-sum/problem
