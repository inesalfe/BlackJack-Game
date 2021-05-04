package blackjack;

public class Dealer {
	
	private Shoe shoe;
	private Hand hand;
	
	private boolean isStanding;
	private boolean isBlackjack;
	
	public Dealer() {
		shoe = new Shoe();
		shoe = new Hand();
	}

	public Dealer(String shoeFile_in) {
		shoe = new Shoe(shoeFile_in);
		shoe = new Hand();
		
	}
	
	public void hit() {
		hand.addCard(dealCards());
	}
	
	public void stand() {
		isStanding = true;
	}
	
	public Card dealCards() {
		return shoe.getCard();
	}
	
	public int result(PlayerHand hand_in) {
		int playerScore = hand_in.getValue();
		int dealerScore = hand.getValue();
		if (playerScore < dealerScore)
			return -1;
		else if (playerScore > dealerScore)
			return 0;
		else
			return 1;
	}
	
}
