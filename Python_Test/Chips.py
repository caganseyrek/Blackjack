from Main import getInput

playerChips = 0
playerBet = 0

def addChips():
    while (True):
        try:
            amount = getInput("How many chips to add: ")
            if(amount <= 0):
                raise ValueError()
            else: 
                break
        except ValueError:
            print("Invalid input. Please enter a valid number")

def newBet():
    while(True):
        amount = getInput("How many chips to bet: ")
        if (playerChips < amount):
            print("You don't have enough chips.\n")
            addChips()
        elif (playerChips == 0):
            print("You don't have any chips.\n")
        else:
            playerBet = amount
            break

def getPlayerChips():
    return playerChips

def setChips(amount: int, operation: str):
    if(operation == "+"):
        #playerChips += amount
        return