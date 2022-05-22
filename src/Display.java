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
	//画面全体
	private JFrame disp;
	
	//パネルオブジェクト(上、中、下）
	private JPanel topPanel, midPanel, bottomPanel;
	
	//メッセージ表示ラベルオブジェクト
	private JLabel msgLbl;
	
	//親のカード情報ラベルオブジェクト
	private JLabel parentLbl, parentSuitLbl, parentNoLbl;
	
	//子のカード情報ラベルオブジェクト
	private JLabel childLbl, childSuitLbl, childNoLbl;
	
	//ボタンオブジェクト
	private JButton btnHigh, btnLow;
	
	//プレイヤーオブジェクト
	private Player parent, child;
	
	public Display(Player prn, Player chl) {
		parent = prn;
		child = chl;
		
		//画面全体の表示設定
		disp = new JFrame("High&Low"); //画面生成
		disp.setSize(480, 280); //表示サイズ
		disp.setLocationRelativeTo(null); //画面表示位置の中央を設定
		disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×ボタンで画面を閉じるよう設定
		disp.setResizable(false); //画面サイズを変えられないように設定
		
		//トップパネルの表示設定
		topPanel = new JPanel(); //パネル生成
		setPanel(topPanel, Color.ORANGE, null, new Dimension(480, 50)); // パネルの背景色、レイアウト、サイズを設定
		disp.add(topPanel, BorderLayout.NORTH);//画面上部にパネルを設定
		
		//メッセージラベルを表示
		msgLbl = new JLabel("一発勝負！HighかLowかを当ててください。");//ラベル生成
		topPanel.add(msgLbl);//トップパネルに追加
		setLabelFont(msgLbl, Color.BLACK, 0, 15, 480, 20, 20, false);//ラベルのフォント設定
		
		//ミドルパネルの表示設定
		midPanel = new JPanel();//パネル生成
		setPanel(midPanel, Color.CYAN, null, new Dimension(480, 180)); //パネルの背景
		disp.add(midPanel, BorderLayout.CENTER); //画面中央部にパネルを追加
		
		/**親カードの情報 */
		parentLbl = new JLabel("私のカード");
		parentSuitLbl = new JLabel(getSuitIcon(parent.GetSuit())); //マークを表示
		parentNoLbl = new JLabel(getNoStr(parent.GetNo()));//数字を表示
		//ミドルパネルに追加
		midPanel.add(parentLbl);
		midPanel.add(parentSuitLbl);
		midPanel.add(parentNoLbl);
		//ラベルのフォント設定
		setLabelFont(parentLbl, Color.WHITE, 90, 10,100,20,14,false);
		setLabelFont(parentSuitLbl, Color.WHITE, 100,10,80,100,16,false);
		setLabelFont(parentNoLbl, Color.WHITE, 100,35,80,100,16, true);
		
		/** 子カードの情報を表示 */
		childLbl = new JLabel("あなたのカード");
		childSuitLbl = new JLabel("");
		childNoLbl = new JLabel("？");
		// ミドルパネルに追加
		midPanel.add(childLbl);
		midPanel.add(childSuitLbl);
		midPanel.add(childNoLbl);
		// ラベルのフォント設定
		setLabelFont(childLbl, Color.WHITE, 265, 10, 150, 20, 14, false );
		setLabelFont(childSuitLbl, Color.LIGHT_GRAY, 300, 10, 80, 100, 16, false );
		setLabelFont(childNoLbl, Color.LIGHT_GRAY, 300, 35, 80, 100, 16, true );
		
		// ボトムパネルの表示設定
		bottomPanel = new JPanel();
		setPanel( bottomPanel, Color.LIGHT_GRAY, new BorderLayout(), new Dimension(480, 50) ); // パネルの背景色、レイアウト、サイズを設定
		disp.add( bottomPanel, BorderLayout.SOUTH ); // 画面下部にパネルを追加
		
		// HIGHボタンを表示
		btnHigh = new JButton("HIGH");
		setButton( btnHigh, this, 240, 50, 20 );        // ボタンのフォントやイベント設定
		bottomPanel.add( btnHigh, BorderLayout.WEST ); // ボトムパネル左側にボタンを追加
		
		// LOWボタンを表示
		btnLow = new JButton("LOW");
		setButton( btnLow, this, 240, 50, 20 );        // ボタンのフォントやイベント設定
		bottomPanel.add( btnLow, BorderLayout.EAST ); // ボトムパネル右側にボタンを追加
		
		// ゲーム画面を表示
		disp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		String cmd     = e.getActionCommand(); // アクションコマンド(どのボタンが押されたか)
		
		int parentNo  = parent.GetNo();   // 親カードの数字
		int childNo   = child.GetNo();    // 子カードの数字
		int childSuit = child.GetSuit();  // 子カードのマーク
		 
		// 子のカードをオープン
		childNoLbl.setBackground(Color.WHITE);            // 数字の背景色
		childNoLbl.setText( getNoStr( child.GetNo() ) );  // 数字の表示データ
		childSuitLbl.setBackground(Color.WHITE);          // マークの背景色
		childSuitLbl.setIcon( getSuitIcon( childSuit ) );// マークの表示データ
		
		// 押されたボタンに応じた処理を行う
		if( cmd.equals("HIGH") ) { 
			// HIGHボタンが押された時の処理
			// ボタンの色を変える
			btnHigh.setBackground(Color.GREEN);
			
			// 結果を判定してメッセージ更新
			if( parentNo < childNo ) // 子の方が大きい
			    msgLbl.setText("大正解、あなたの勝ちです！");
			else if( childNo < parentNo ) // 親の方が大きい
			    msgLbl.setText("不正解、あなたの負けです！");
			else // 親と子の数字が同じ
			    msgLbl.setText("奇遇ですね。引き分けです！");
		}
	}
	// パネルのフォント設定を行うメソッド
	public static void setPanel(JPanel panel, Color color, BorderLayout layout, Dimension dimension ){
	    panel.setBackground(color);        // 背景色を設定
	    panel.setLayout(layout);           // レイアウトを設定
	    panel.setPreferredSize(dimension); // 表示サイズを設定
	 
	    return;
	}
	
	// ラベルのフォント設定を行うメソッド
	public static void setLabelFont(JLabel label,Color clr, 
	                                int x_pos,int y_pos,
	                                int x_size,int y_size,
	                                int strSize,boolean opq ){
	    label.setBackground(clr);        // 背景色を設定
	    label.setLocation(x_pos, y_pos); // 表示位置を設定
	    label.setSize(x_size, y_size);   // 表示サイズを設定
	    label.setFont( new Font("ＭＳ ゴシック", Font.PLAIN, strSize) ); // 書式、文字サイズを設定
	    label.setHorizontalAlignment(JLabel.CENTER); // 水平方向中央揃え
	    label.setVerticalAlignment(JLabel.CENTER);   // 垂直方向中央揃え
	    label.setOpaque(opq); // ラベルの透明性を設定(true＝不透明、false＝透明)
	 
	    return;
	}
	
	// ボタンの設定を行うメソッド
	public static void setButton(JButton btn, ActionListener al, int x_size, int y_size, int strSize ){
	    btn.setPreferredSize(new Dimension(x_size, y_size));      // 表示サイズを設定
	    btn.setFont( new Font("ＭＳ ゴシック", Font.PLAIN, strSize) ); // 書式、文字サイズを設定
	    btn.addActionListener(al); // ボタンが押された時のイベントを受け取れるように設定
	 
	    return;
	}
	
	// マークに応じたアイコンオブジェクトを取得するメソッド
	public static ImageIcon getSuitIcon( int suit ){
	    ImageIcon icon;
	 
	    // マークに応じた画像を読み込んでリターンする
	    switch(suit)
	    {
	        case 0: // スペード
	            icon = new ImageIcon("./src/game/highandlow/img/spade.jpg");
	            return icon;
	 
	        case 1: // ハート
	            icon = new ImageIcon("./src/game/highandlow/img/heart.jpg");
	            return icon;
	 
	        case 2: // ダイヤ
	            icon = new ImageIcon("./src/game/highandlow/img/diamond.jpg");
	            return icon;
	 
	        case 3: // クラブ
	            icon = new ImageIcon("./src/game/highandlow/img/clover.jpg");
	            return icon;
	 
	        default: // マークが不正の場合
	            return null;
	    }
	}
	
	// 数字に応じた表示文字列を取得するメソッド
	public static String getNoStr( int no ){
	    switch(no)
	    {
	        case 1: // エース
	            return "A";
	 
	        case 11: // ジャック
	            return "J";
	 
	        case 12: // クイーン
	            return "Q";
	 
	        case 13: // キング
	            return "K";
	 
	        default: // 上記以外は数字をそのまま文字列として出力する
	            return String.valueOf(no);
	    }
	}
}