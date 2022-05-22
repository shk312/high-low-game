import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//カード情報
class Card {
	int suit; //マーク(0:スペード/1:ハート/2:ダイヤ/3:クラブ)
	int no; //数字 (1:エース/11:ジャック/12:クイーン/13:キング)
}

//山札クラス
public class Deck{
	//定数定義
	final int TotalCard = 52; //カード枚数
	
	/** メンバ変数定義
	 * カードリスト　0～52の要素をいれ、マークと数字を割り当てる
	 * 0-12 spade, 13-25 heart, 26-38: diamond, 39-51: club
	 */
	private List<Integer> cardList;
	
	private int cardIndex = 0;
	
	//コンストラクタ（初期化処理）
	public Deck() {
		//次に取り出すカードの番号初期化
		cardIndex = 0; //1枚目
		
		//山札初期化
		cardList = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51));
		
		//山札シャッフル
		Collections.shuffle(cardList);
	}
	
	//メソッド定義
	//山札からカードを1枚取り出し、そのカードのマークと数字をリターンする
	public Card GetCard() {
		Card cardInfo = new Card(); //カード情報格納用
		int cardNo = 0; //カード番号
		
		//カード1枚取り出して、カード番号取得
		cardNo = (int) cardList.get(cardIndex);
		
		//1枚取り出したので、カード番号1加算
		cardIndex++;
		
		//山札をすべて引いた場合,山札初期化
		if( TotalCard <= cardIndex ) {
			cardIndex = 0;
			Collections.shuffle(cardList);
		}
		
		cardInfo.suit = cardNo / 13; // マーク＝カード番号を13で割った商
		cardInfo.no = (cardNo % 13) + 1; //数字＝カード番号を13で割ったあまり+1
		
		return cardInfo;
	}
}