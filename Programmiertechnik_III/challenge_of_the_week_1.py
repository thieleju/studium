# Warten auf wäremeres Wetter - Challenge of the Week 1

import numpy as np

input = np.array([15, 17, 18, 15, 14, 14, 15, 20, 18, 19])
# input = np.random.randint(10, 28, 20) # array of 20 random temperatures from 10 to 28 degrees


def howManyDaysUntilWarmer(current_temp):
    global input
    # iterate over all temperatures after the current one
    # if the next temperature is higher than the current one,
    # add 1 to the counter
    c = 0
    while current_temp >= input[i + c]:
        # if the next day to check is outside the array,
        # break the loop and add 0 to the output
        if (c + i >= len(input) - 1):
            c = 0
            break
        # if the next day is colder, add 1 to the counter and continue
        c += 1
    # return the number of days until the next warmer day
    return c


if __name__ == "__main__":
    # create empty output array
    output = np.array([])

    # iterate over all temperatures to find the next warmer day
    # add the number of days to the output array
    for i, temp in enumerate(input):
        output = np.append(output, howManyDaysUntilWarmer(temp))

    # print the output array in a nice format
    for i, temp in enumerate(input):
        print(
            f"Day: {i + 1} with {temp}°C\tDays until warmer: {output[i] if output[i] != 0 else '0 (Unknown)'}")
