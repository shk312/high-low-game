public class Player {
	//�v���C���[�������Ă�����
	private Card cardInfo;
	
	//�R���X�g���N�^�i�������j
	public Player() {
		cardInfo = new Card();
		
		cardInfo.no = 0;
		cardInfo.suit = 0;
	}
	
	//���\�b�h 1���h���[
	public void Draw(Deck deck) {
		cardInfo = deck.GetCard();
		return;
	}
	
	public int GetNo() {
		return cardInfo.no;
	}
	
	public int GetSuit() {
		return cardInfo.suit;
	}
}