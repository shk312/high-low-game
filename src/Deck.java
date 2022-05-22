import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//�J�[�h���
class Card {
	int suit; //�}�[�N(0:�X�y�[�h/1:�n�[�g/2:�_�C��/3:�N���u)
	int no; //���� (1:�G�[�X/11:�W���b�N/12:�N�C�[��/13:�L���O)
}

//�R�D�N���X
public class Deck{
	//�萔��`
	final int TotalCard = 52; //�J�[�h����
	
	/** �����o�ϐ���`
	 * �J�[�h���X�g�@0�`52�̗v�f������A�}�[�N�Ɛ��������蓖�Ă�
	 * 0-12 spade, 13-25 heart, 26-38: diamond, 39-51: club
	 */
	private List<Integer> cardList;
	
	private int cardIndex = 0;
	
	//�R���X�g���N�^�i�����������j
	public Deck() {
		//���Ɏ��o���J�[�h�̔ԍ�������
		cardIndex = 0; //1����
		
		//�R�D������
		cardList = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51));
		
		//�R�D�V���b�t��
		Collections.shuffle(cardList);
	}
	
	//���\�b�h��`
	//�R�D����J�[�h��1�����o���A���̃J�[�h�̃}�[�N�Ɛ��������^�[������
	public Card GetCard() {
		Card cardInfo = new Card(); //�J�[�h���i�[�p
		int cardNo = 0; //�J�[�h�ԍ�
		
		//�J�[�h1�����o���āA�J�[�h�ԍ��擾
		cardNo = (int) cardList.get(cardIndex);
		
		//1�����o�����̂ŁA�J�[�h�ԍ�1���Z
		cardIndex++;
		
		//�R�D�����ׂĈ������ꍇ,�R�D������
		if( TotalCard <= cardIndex ) {
			cardIndex = 0;
			Collections.shuffle(cardList);
		}
		
		cardInfo.suit = cardNo / 13; // �}�[�N���J�[�h�ԍ���13�Ŋ�������
		cardInfo.no = (cardNo % 13) + 1; //�������J�[�h�ԍ���13�Ŋ��������܂�+1
		
		return cardInfo;
	}
}