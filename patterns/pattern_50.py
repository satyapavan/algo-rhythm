i_input = int(input('Enter a number : '))

for itr in range(1, i_input+1):             ## This is the outer loop to repeat the logic for all the input numbers
    for itr_i in range(itr, i_input+1):     ## This is the first internal loop for printing first half of sequence.
        print(itr_i, end='')                ## For itr = 3, this will print 345 and below loop will print 12, forming 34512

    for itr_j in range(1, itr):             ## This is the second internal loop for printing second  half of sequence.
        print(itr_j, end='')                ## For itr = 3, above loop will print 345 and this loop will print 12, forming 34512

    print("")
