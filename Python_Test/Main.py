from Game import *

def getInput(prompt: str):
    while (True):
        try:
            output = int(input(prompt))
            if output.is_integer is False:
                raise ValueError()
            else:
                return output
        except ValueError:
            print("Invalid input. Please enter a valid number")

def getInputFromList(prompt: str, choices: list):
    while (True):
        try:
            output = int(input(prompt))
            if output not in choices:
                raise ValueError()
            else:
                return output
        except ValueError:
            print("Invalid input. Please enter a valid number")

def Main():
    while (True):
        print("\nChips: ")
        print("1. Start a new blackjack game")
        print("2. Add chips to balance")
        print("3. Quit the game\n")
        choice = getInputFromList("Your choice: ", [1, 2, 3])
        if (choice == 1):
            startGame()
            break
        elif (choice == 2):
            addChips()
            break
        elif (choice == 3):
            exit(0)
        else:
            print("Invalid choice. Please try again.")