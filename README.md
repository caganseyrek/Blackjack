# Blackjack Game

A simple terminal-based blackjack game made with Java.

Card images' source can be found [here](https://commons.wikimedia.org/wiki/File:English_pattern_playing_cards_deck.svg)

>Please note that I made this project for learning and practising purposes. Codes may be inefficient, written incorrectly or entirely may not work.

>Note: Chips and betting in this game are entirely imaginary and only added to make the game close to a real blackjack game.

***

## Installation and Setup

You need to install `Main.java`, `Game.java` and `Deck.java` in order to play.

After you install the files, navigate to their location in terminal then compile the java files:

```bash
javac Main.java
```

After that, you can run the game:

```bash
java Main
```

## How to Play Blackjack

 * In a given round, player's goal is to get a hand higher than the dealer's without going over 21. (That is called "busting")
   
 * If you reach exactly 21 points (that means you have a blackjack) without dealer also reaching 21 points, you instantly win. (If dealer also have a blackjack the round is a draw)
  
 * In blackjack, every player competes against the dealer, not themselves. (Game can be played with multiple players but this version only allows one player)

 * Cards 2 to 10 have their face value. (For example 2 of hearts have 2 point value), Jack-Queen-King have 10 point value, and Ace have 11 point value. But player can decide to choose if their ace worth 11 or 1 points. (For example if player have Jack, Queen and an Ace, they can decide to count the Ace as 1 to reach 21)

 * On start, dealer gives the players one card face-up and give themselves one card face-down. After that dealer again gives everyone (including themselves) one card face-up. It doesn't matter if players can see each others hand. It is important that players don't see dealer's first (face-down) card.

 * There are 2 options that players can do (at least in this version of the game): hitting or staying.
   
   * When you hit, the dealer gives you another card from the deck. But you must be careful when you decide to hit, because if you go over 21 (or bust), you instantly lose that round.
   * When you stay, your turn in round is over and dealer reveals their face-down card. If dealer's hand is lower than 17, they draw until they reach or go over 17 points. If dealer is went over 21, you win the round.
   
 * After that whoever's hand is greater wins (between player and dealer). If both player's and dealer's hands are equal in points, it's a tie.
