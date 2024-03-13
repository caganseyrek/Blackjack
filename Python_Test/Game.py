from Chips import *
from Deck import *
from Main import getInputFromList

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