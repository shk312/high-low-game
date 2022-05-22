import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Display implements ActionListener {
	//��ʑS��
	private JFrame disp;
	
	//�p�l���I�u�W�F�N�g(��A���A���j
	private JPanel topPanel, midPanel, bottomPanel;
	
	//���b�Z�[�W�\�����x���I�u�W�F�N�g
	private JLabel msgLbl;
	
	//�e�̃J�[�h��񃉃x���I�u�W�F�N�g
	private JLabel parentLbl, parentSuitLbl, parentNoLbl;
	
	//�q�̃J�[�h��񃉃x���I�u�W�F�N�g
	private JLabel childLbl, childSuitLbl, childNoLbl;
	
	//�{�^���I�u�W�F�N�g
	private JButton btnHigh, btnLow;
	
	//�v���C���[�I�u�W�F�N�g
	private Player parent, child;
	
	public Display(Player prn, Player chl) {
		parent = prn;
		child = chl;
		
		//��ʑS�̂̕\���ݒ�
		disp = new JFrame("High&Low"); //��ʐ���
		disp.setSize(480, 280); //�\���T�C�Y
		disp.setLocationRelativeTo(null); //��ʕ\���ʒu�̒�����ݒ�
		disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�~�{�^���ŉ�ʂ����悤�ݒ�
		disp.setResizable(false); //��ʃT�C�Y��ς����Ȃ��悤�ɐݒ�
		
		//�g�b�v�p�l���̕\���ݒ�
		topPanel = new JPanel(); //�p�l������
		setPanel(topPanel, Color.ORANGE, null, new Dimension(480, 50)); // �p�l���̔w�i�F�A���C�A�E�g�A�T�C�Y��ݒ�
		disp.add(topPanel, BorderLayout.NORTH);//��ʏ㕔�Ƀp�l����ݒ�
		
		//���b�Z�[�W���x����\��
		msgLbl = new JLabel("�ꔭ�����IHigh��Low���𓖂ĂĂ��������B");//���x������
		topPanel.add(msgLbl);//�g�b�v�p�l���ɒǉ�
		setLabelFont(msgLbl, Color.BLACK, 0, 15, 480, 20, 20, false);//���x���̃t�H���g�ݒ�
		
		//�~�h���p�l���̕\���ݒ�
		midPanel = new JPanel();//�p�l������
		setPanel(midPanel, Color.CYAN, null, new Dimension(480, 180)); //�p�l���̔w�i
		disp.add(midPanel, BorderLayout.CENTER); //��ʒ������Ƀp�l����ǉ�
		
		/**�e�J�[�h�̏�� */
		parentLbl = new JLabel("���̃J�[�h");
		parentSuitLbl = new JLabel(getSuitIcon(parent.GetSuit())); //�}�[�N��\��
		parentNoLbl = new JLabel(getNoStr(parent.GetNo()));//������\��
		//�~�h���p�l���ɒǉ�
		midPanel.add(parentLbl);
		midPanel.add(parentSuitLbl);
		midPanel.add(parentNoLbl);
		//���x���̃t�H���g�ݒ�
		setLabelFont(parentLbl, Color.WHITE, 90, 10,100,20,14,false);
		setLabelFont(parentSuitLbl, Color.WHITE, 100,10,80,100,16,false);
		setLabelFont(parentNoLbl, Color.WHITE, 100,35,80,100,16, true);
		
		/** �q�J�[�h�̏���\�� */
		childLbl = new JLabel("���Ȃ��̃J�[�h");
		childSuitLbl = new JLabel("");
		childNoLbl = new JLabel("�H");
		// �~�h���p�l���ɒǉ�
		midPanel.add(childLbl);
		midPanel.add(childSuitLbl);
		midPanel.add(childNoLbl);
		// ���x���̃t�H���g�ݒ�
		setLabelFont(childLbl, Color.WHITE, 265, 10, 150, 20, 14, false );
		setLabelFont(childSuitLbl, Color.LIGHT_GRAY, 300, 10, 80, 100, 16, false );
		setLabelFont(childNoLbl, Color.LIGHT_GRAY, 300, 35, 80, 100, 16, true );
		
		// �{�g���p�l���̕\���ݒ�
		bottomPanel = new JPanel();
		setPanel( bottomPanel, Color.LIGHT_GRAY, new BorderLayout(), new Dimension(480, 50) ); // �p�l���̔w�i�F�A���C�A�E�g�A�T�C�Y��ݒ�
		disp.add( bottomPanel, BorderLayout.SOUTH ); // ��ʉ����Ƀp�l����ǉ�
		
		// HIGH�{�^����\��
		btnHigh = new JButton("HIGH");
		setButton( btnHigh, this, 240, 50, 20 );        // �{�^���̃t�H���g��C�x���g�ݒ�
		bottomPanel.add( btnHigh, BorderLayout.WEST ); // �{�g���p�l�������Ƀ{�^����ǉ�
		
		// LOW�{�^����\��
		btnLow = new JButton("LOW");
		setButton( btnLow, this, 240, 50, 20 );        // �{�^���̃t�H���g��C�x���g�ݒ�
		bottomPanel.add( btnLow, BorderLayout.EAST ); // �{�g���p�l���E���Ƀ{�^����ǉ�
		
		// �Q�[����ʂ�\��
		disp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String cmd     = e.getActionCommand(); // �A�N�V�����R�}���h(�ǂ̃{�^���������ꂽ��)
		
		int parentNo  = parent.GetNo();   // �e�J�[�h�̐���
		int childNo   = child.GetNo();    // �q�J�[�h�̐���
		int childSuit = child.GetSuit();  // �q�J�[�h�̃}�[�N
		 
		// �q�̃J�[�h���I�[�v��
		childNoLbl.setBackground(Color.WHITE);            // �����̔w�i�F
		childNoLbl.setText( getNoStr( child.GetNo() ) );  // �����̕\���f�[�^
		childSuitLbl.setBackground(Color.WHITE);          // �}�[�N�̔w�i�F
		childSuitLbl.setIcon( getSuitIcon( childSuit ) );// �}�[�N�̕\���f�[�^
		
		// �����ꂽ�{�^���ɉ������������s��
		if( cmd.equals("HIGH") ) { 
			// HIGH�{�^���������ꂽ���̏���
			// �{�^���̐F��ς���
			btnHigh.setBackground(Color.GREEN);
			
			// ���ʂ𔻒肵�ă��b�Z�[�W�X�V
			if( parentNo < childNo ) // �q�̕����傫��
			    msgLbl.setText("�吳���A���Ȃ��̏����ł��I");
			else if( childNo < parentNo ) // �e�̕����傫��
			    msgLbl.setText("�s�����A���Ȃ��̕����ł��I");
			else // �e�Ǝq�̐���������
			    msgLbl.setText("����ł��ˁB���������ł��I");
		}
	}
	// �p�l���̃t�H���g�ݒ���s�����\�b�h
	public static void setPanel(JPanel panel, Color color, BorderLayout layout, Dimension dimension ){
	    panel.setBackground(color);        // �w�i�F��ݒ�
	    panel.setLayout(layout);           // ���C�A�E�g��ݒ�
	    panel.setPreferredSize(dimension); // �\���T�C�Y��ݒ�
	 
	    return;
	}
	
	// ���x���̃t�H���g�ݒ���s�����\�b�h
	public static void setLabelFont(JLabel label,Color clr, 
	                                int x_pos,int y_pos,
	                                int x_size,int y_size,
	                                int strSize,boolean opq ){
	    label.setBackground(clr);        // �w�i�F��ݒ�
	    label.setLocation(x_pos, y_pos); // �\���ʒu��ݒ�
	    label.setSize(x_size, y_size);   // �\���T�C�Y��ݒ�
	    label.setFont( new Font("�l�r �S�V�b�N", Font.PLAIN, strSize) ); // �����A�����T�C�Y��ݒ�
	    label.setHorizontalAlignment(JLabel.CENTER); // ����������������
	    label.setVerticalAlignment(JLabel.CENTER);   // ����������������
	    label.setOpaque(opq); // ���x���̓�������ݒ�(true���s�����Afalse������)
	 
	    return;
	}
	
	// �{�^���̐ݒ���s�����\�b�h
	public static void setButton(JButton btn, ActionListener al, int x_size, int y_size, int strSize ){
	    btn.setPreferredSize(new Dimension(x_size, y_size));      // �\���T�C�Y��ݒ�
	    btn.setFont( new Font("�l�r �S�V�b�N", Font.PLAIN, strSize) ); // �����A�����T�C�Y��ݒ�
	    btn.addActionListener(al); // �{�^���������ꂽ���̃C�x���g���󂯎���悤�ɐݒ�
	 
	    return;
	}
	
	// �}�[�N�ɉ������A�C�R���I�u�W�F�N�g���擾���郁�\�b�h
	public static ImageIcon getSuitIcon( int suit ){
	    ImageIcon icon;
	 
	    // �}�[�N�ɉ������摜��ǂݍ���Ń��^�[������
	    switch(suit)
	    {
	        case 0: // �X�y�[�h
	            icon = new ImageIcon("./src/game/highandlow/img/spade.jpg");
	            return icon;
	 
	        case 1: // �n�[�g
	            icon = new ImageIcon("./src/game/highandlow/img/heart.jpg");
	            return icon;
	 
	        case 2: // �_�C��
	            icon = new ImageIcon("./src/game/highandlow/img/diamond.jpg");
	            return icon;
	 
	        case 3: // �N���u
	            icon = new ImageIcon("./src/game/highandlow/img/clover.jpg");
	            return icon;
	 
	        default: // �}�[�N���s���̏ꍇ
	            return null;
	    }
	}
	
	// �����ɉ������\����������擾���郁�\�b�h
	public static String getNoStr( int no ){
	    switch(no)
	    {
	        case 1: // �G�[�X
	            return "A";
	 
	        case 11: // �W���b�N
	            return "J";
	 
	        case 12: // �N�C�[��
	            return "Q";
	 
	        case 13: // �L���O
	            return "K";
	 
	        default: // ��L�ȊO�͐��������̂܂ܕ�����Ƃ��ďo�͂���
	            return String.valueOf(no);
	    }
	}
}