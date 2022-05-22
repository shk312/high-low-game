public class Player {
	//プレイヤーが持っている情報
	private Card cardInfo;
	
	//コンストラクタ（初期化）
	public Player() {
		cardInfo = new Card();
		
		cardInfo.no = 0;
		cardInfo.suit = 0;
	}
	
	//メソッド 1毎ドロー
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