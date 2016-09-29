/*
 * 鍔熻兘锛氬疄鐜伴棯灞忕晫闈�
 * 鏃ユ湡锛�2010.10.11
 * */
package com.partys.login;
import java.awt.*;
import javax.swing.*;
public class Index extends JWindow implements Runnable{
	//瀹氫箟涓庤繘搴︽潯鐩稿叧鐨勭粍浠�
	private JProgressBar jpb;//瀹氫箟杩涘害鏉�
	private JLabel jl1;//鐢ㄤ簬鍦ㄧ獥浣撶殑鍖楅儴鏀句竴寮犲浘鐗囷紝鍗楅儴鏄繘搴︽潯
	private int width,height;//鐢ㄤ簬鑾峰彇鏄剧ず灞忓垎杈ㄧ巼澶у皬
	public static void main(String []args){
		Index index=new Index();
		//鍒涘缓index绾跨▼
		Thread t=new Thread(index);
		//鍚姩绾跨▼
		t.start();
	}
	//鏋勯�犲嚱鏁�
	public Index()
	{	
		//鍒涘缓鏍囩,骞跺湪鏍囩涓婃斁缃竴寮犲浘鐗�
		jl1=new JLabel(new ImageIcon("image/index/index.png"));
		
		//鍒涘缓杩涘害鏉�
		jpb=new JProgressBar();
		//璁剧疆杩涘害鏉″睘鎬�
		jpb.setStringPainted(true);//鏄剧ず褰撳墠杩涘害鍊间俊鎭�
		jpb.setIndeterminate(false);//纭畾杩涘害鏉℃墽琛屽畬鎴愬悗涓嶆潵鍥炴粴鍔�
		jpb.setBorderPainted(false);//璁剧疆杩涘害鏉¤竟妗嗕笉鏄剧ず
		jpb.setBackground(Color.pink);//璁剧疆杩涘害鏉＄殑鑳屾櫙鑹�
		
		//娣诲姞缁勪欢
		this.add(jl1,BorderLayout.NORTH);
		this.add(jpb,BorderLayout.SOUTH);
		
		//璁剧疆绐椾綋灞炴��
		this.setSize(450,350);
		//璁剧疆绐椾綋鏄剧ず鐨勪綅缃�
		width=Toolkit.getDefaultToolkit().getScreenSize().width;
		height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200,height/2-150);
		//璁剧疆绐楀彛鏄剧ず
		this.setVisible(true);
	}

	public void run() {
		//瀹氫箟涓�涓暟缁勶紝瀛樻斁杩涘害鏉℃樉绀烘椂闇�瑕佺殑鏁版嵁
		int []progressValue={0,1,5,8,14,17,26,35,38,43,49,56,65,71,75,78,86,94,98,99,100};
		for(int i=0;i<progressValue.length;i++)
		{
			try {
				//浼戠湢1绉掞紝鍐嶆墽琛�
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jpb.setValue(progressValue[i]);//鍙栧緱鏁扮粍涓殑杩涘害鍊�
			if(i==progressValue.length-1){
				new UserLogin();
				//鍏抽棴杩涘害鏉＄獥鍙�
					this.dispose();
			}
		}
	}
}
