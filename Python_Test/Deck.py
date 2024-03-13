import random
from Game import playerHand,dealerHand,dealerReveal

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