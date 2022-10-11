# Warten auf wärmeres Wetter - Challenge of the Week 1

import numpy as np

input = np.array([15, 17, 18, 15, 14, 14, 15, 20, 18, 19])

# array of 20 random temperatures from 10 to 28 degrees
# input = np.random.randint(10, 28, 20)


def getDaysUntilWarmer(i, input):
    '''
    Iterate over all temperatures after the current one.
    If the next temperature is higher than the current one,
    add 1 to the counter
    @param i: current index
    @param input: input array containing temperatures
    @returns Amount of days until the next warmer day
    '''
    counter = 0
    while input[i] >= input[i + counter]:
        # if the next day to check is outside the array,
        # break the loop and add 0 to the output
        if counter + i >= len(input) - 1:
            counter = 0
            break
        # since next day is colder, add 1 to the counter and continue the loop
        counter += 1
    # return the number of days until the next warmer day
    return counter


if __name__ == "__main__":
    # create empty output array
    output = np.array([])

    # iterate over all temperatures to find the next warmer day
    for i in range(len(input)):
        # add the number of days to the output array
        output = np.append(output, getDaysUntilWarmer(i, input))

    # print the output array in a nice format
    for i, temp in enumerate(input):
        solution = output[i] if output[i] != 0 else f"Unknown, but >= {len(input) - i}"
        print(f"Day: {i + 1} with {temp}°C\tDays until warmer: {solution}")
