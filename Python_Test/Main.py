import random

playerChips = 0
playerBet = 0

gameEnded = True

playerHand = []
dealerHand = []

gameEnded = False
playerBust = False
dealerReveal = False

def startGame():
    newBet()
    gameEnded = False
    playerBust = False
    dealerReveal = False

    playDeck = generateDeck()
    playerHand.append(drawCard(playDeck))
    dealerHand.append(drawCard(playDeck)) # hidden card
    playerHand.append(drawCard(playDeck))
    dealerHand.append(drawCard(playDeck))

    while (True):
        if (getHandValue(playerHand) > 21):
            playerBust = True
            dealerReveal = True
            getStatus()
            print("Your hand went over 21 before starting. You lost.")
            setChips(playerBet, "-")
            gameEnded = True
            break
        elif (getHandValue(playerHand) == 21):
            dealerReveal = True
            getStatus()
            print("Blackjack! You win.")
            setChips(int(playerBet * 1.5), "+")
            gameEnded = True
            break

        getStatus()
        choice = getInputFromList("1 - Hit | 2 - Stay > ", [1, 2])
        if (choice == 1):
            playerHand.append(drawCard(playDeck))
        elif (choice == 2):
            dealerReveal = True
            break
    
    dealerReveal = True
    if(playerBust == False and getHandValue(dealerHand) < 17 and gameEnded == False):
        while (True):
            if (getHandValue(dealerHand) >= 17):
                break
            dealerHand.append(drawCard(playDeck))

    getStatus()
    print("\n")

    if(playerBust == False and gameEnded == False):
        if (getHandValue(dealerHand) > 21):
            print("Dealer's hand went over 21. You win.")
            setChips(playerBet, "+")
        elif (getHandValue(playerHand) > getHandValue(dealerHand)):
            print("You win.")
            setChips(playerBet, "+")
        elif (getHandValue(playerHand) == getHandValue(dealerHand)):
            print("It's a tie.")
        else:
            print("Dealer wins.")
            setChips(playerBet, "-")
    
    print("Game Over.")
    playerBust = False
    dealerReveal = False
    gameEnded = True

    while (True):
        replay = getInputFromList("1 - Return to main menu | 2 - Play again > ", [1, 2])
        if (replay == 2):
            print("\nThank you for playing!")
            exit(0)
        elif (replay == 1):
            return

def generateDeck():
    ranks = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"]
    suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
    newDeck = []
    for rank in ranks:
        for suit in suits:
            newDeck.append(rank + " of " + suit)
    random.shuffle(newDeck)
    return newDeck

def drawCard(deck: list):
    return deck.pop(0)

def getCardValue(card: str):
    value = 0
    rank = card.split(" ")[0]
    if ("JackQueenKing".__contains__(rank)):
        value = 10
    elif (rank.__eq__("Ace")):
        value = 11
    else:
        value = int(rank)
    return value

def getHandValue(deck: list):
    handValue = 0
    aceAmount = 0
    for card in deck:
        handValue += getCardValue(card)
        if (card.__contains__("Ace")):
            aceAmount += 1
    if (handValue > 21):
        while (aceAmount > 0):
            handValue -= 10
            if (handValue == 21):
                break
            aceAmount -= 1
    return handValue

def getStatus():
    print("\n")
    dealer = dealerHand
    hiddencard = dealer.pop(0)

    if (dealerReveal == False):
        dealer.remove(hiddencard)
        print("Dealer's Hand > [Hidden] ", dealer, " (", getHandValue(dealerHand), "pts)")
        print("Dealer's Hand > ", playerHand, " (", getHandValue(playerHand), "pts)")
        dealer.insert(0, hiddencard)
    elif (dealerReveal == True):
        print("Dealer's Hand > ", dealer, " (", getHandValue(dealerHand), "pts)")
        print("Dealer's Hand > ", playerHand, " (", getHandValue(playerHand), "pts)")

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

def setChips(amount: int, operation: str):
    if(operation == "+"):
        playerChips += amount
        return

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

if __name__ == "__main__":
    Main()