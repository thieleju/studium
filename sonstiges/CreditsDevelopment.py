import matplotlib.pyplot as plt
import numpy as np
import sys

# set recursion limit to 10k
print(sys.getrecursionlimit())
sys.setrecursionlimit(10000)

# simulate development of daily increase in credits over time
# when perfect-buying daily increase

credits = 0
creditsDaily = 2500
creditsDailyIncrease = 1000
creditsDailyCost = 50000
creditsDailyAdded = 0
buys = 0
buysPerDay = 0
days = 0

xDays = np.array([])
yCredits = np.array([])
yCreditsDaily = np.array([])
yBuysPerDay = np.array([])


def plotFigure(title, *dicts):
    """
    General function to plot graphs from dictionaries

    Arguments:
        `title` (string): Title of the figure
        `dicts` (dictionaries): Variable length argument list of dictionaries
    ```
    dict = {"xLabel": "x-Axis",
            "yLabel": "y-Axis",
            "x": np.array([1,2,3]),
            "y": np.array([1,2,3])}
    ```
    """
    # create main plot
    fig = plt.figure(1)
    fig.suptitle(title)

    # create n-subplots
    for dict in dicts:
        # add subplot
        ax = fig.add_subplot(len(dicts), 1, dicts.index(dict) + 1)

        # plot current dicitonary
        ax.plot(dict["x"], dict["y"])
        ax.ticklabel_format(style='plain')
        ax.set_xlabel(dict["xLabel"])
        ax.set_ylabel(dict["yLabel"])

    # show
    plt.show()


def simulateBuysOverTimeSteps():
    global credits
    global creditsDaily
    global creditsDailyIncrease
    global creditsDailyCost
    global creditsDailyAdded
    global buys
    global buysPerDay
    global days
    global xDays
    global yCredits
    global yCreditsDaily
    global yBuysPerDay

    # earn daily credits
    credits += creditsDaily
    days += 1
    buysPerDay = 0

    # simulate buying as much as you can with current credits (in one day)
    while credits >= creditsDailyCost:
        credits -= creditsDailyCost
        creditsDaily += creditsDailyIncrease
        buys += 1
        buysPerDay += 1

    # add date for graphs
    xDays = np.append(xDays, days)
    yCredits = np.append(yCredits, credits)
    yCreditsDaily = np.append(yCreditsDaily, creditsDaily)
    yBuysPerDay = np.append(yBuysPerDay, buysPerDay)


# ======= START =======
if __name__ == "__main__":

    # get valid userinput
    valid = False
    while not valid:
        # Get daily credit increase from userinput
        userinput = input(
            "How many credits do you get per day? Example: 2376500 (q=quit)\n")

        # handle string to int conversion error
        try:
            creditsDailyAdded = int(userinput)
        except:
            if userinput == "q":
                sys.exit()
            else:
                print("Please enter a valid Integer!")
            continue

        # handle minimum input
        if creditsDailyAdded <= 2500:
            print(
                "You cannot have less or equal to 2500 daily credits added!")
        # handle invalid input
        elif creditsDailyAdded % 1000 != 500:
            print(
                "You cannot have this amount of credits added per day!")
        else:
            valid = True

    # run simulation until value is met
    while creditsDaily < creditsDailyAdded:
        simulateBuysOverTimeSteps()

    # prepare dictionaries (overcomplicated, but for testing purposes)
    dict1 = {"xLabel": "Days",
             "yLabel": "Credits",
             "x": xDays,
             "y": yCredits}

    dict2 = {"xLabel": "Days",
             "yLabel": "Credits Daily Increase",
             "x": xDays,
             "y": yCreditsDaily}

    dict3 = {"xLabel": "Days",
             "yLabel": "Buys Per Day",
             "x": xDays,
             "y": yBuysPerDay}

    # plot graphs
    plotFigure('Credits development over time', dict1, dict2, dict3)

    # show additional stats
    print("Days: ", days)
    print("Credits: ", credits)
    print("Credits added daily: ", creditsDaily)
    print("Buys: ", buys)
