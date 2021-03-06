package com.example.numerica;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hanheng.a53.beep.BeepClass;
//import com.example.numerica.dip.DipClass;
import com.hanheng.a53.dotarray.FontClass;
import com.example.aircondition.R;
import com.hanheng.a53.seg7.Seg7Class;
import com.hanheng.a53.led.LedClass;
import com.example.numerica.MainActivity;
import com.example.numerica.MyAdapter;
//import com.hanheng.matrix.FillContent;
//import com.hanheng.matrix.FillContent;
//import com.hanheng.matrix.MyAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;



@SuppressLint({ "ShowToast", "HandlerLeak" }) 
public class MainActivity extends Activity implements OnClickListener {
	private View display;
	//private EditText text;
	//private EditText text2;
	private TextView counterText;
	//private TextView textview1;
	//private TextView textview2;
	private Button count_btn;
	private Button count_front;
	private Button count_back;
	private Button count_stop;
	private Button count_in;
	private Button count_out;
	private Button lock;
	private Button count_power;
	private int[] icon;
	private MyAdapter adapter;
	private List<FillContent> data_List=new ArrayList<FillContent>();
//	定义标志位控制启动线程
	private boolean flag;
	
	public static final int BEEP_ON = 0;
    public static final int BEEP_OFF = 1;
    
    public static int is_lock = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		初始化标签
		init();
		
	}

	// 初始化获取每个视图和控件,并添加按钮点击事件
	public void init() {
		this.display = (View) findViewById(R.id.display1);
		this.count_btn = (Button) display.findViewById(R.id.count);
		//this.text = (EditText) display.findViewById(R.id.editText1);
		//this.text2 = (EditText) display.findViewById(R.id.editText2);
	    this.counterText = (TextView) display.findViewById(R.id.counterText);
		//this.textview1 = (TextView) display.findViewById(R.id.textView1);
		//textview2.setText("100");
		//this.textview2 = (TextView) display.findViewById(R.id.textView2);
		//textview2.setText("油量：");
		this.count_front = (Button) display.findViewById(R.id.front);
		this.count_back = (Button) display.findViewById(R.id.back);
		this.count_power = (Button)display.findViewById(R.id.power);
		this.count_stop = (Button) display.findViewById(R.id.stop);
		this.count_in = (Button) display.findViewById(R.id.in);
		this.count_out = (Button) display.findViewById(R.id.out);
		this.lock = (Button) display.findViewById(R.id.lock);
		this.count_front.setOnClickListener(this);
		this.count_back.setOnClickListener(this);
		this.count_btn.setOnClickListener(this);
		this.count_stop.setOnClickListener(this);
		this.lock.setOnClickListener(this);
		this.count_power.setOnClickListener(this);
		this.count_in.setOnClickListener(this);
		this.count_out.setOnClickListener(this);
		int err = Seg7Class.Init();
		LedClass.Init();
		BeepClass.Init();
		System.out.println("测试:"+err);
		
	}

	// 定义handler接收线程回传内容
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				updateText(msg.arg1);
				
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
//	初始化数据
	public void initData(int[] icon){
		for(int i=0;i<icon.length;i++){
			FillContent con=new FillContent(icon[i]);
			data_List.add(con);
		}
	}
	//private Handler uiHandler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==1){	
				Log.i("鑾峰彇鏁版嵁", ""+msg.arg1);
				computed(msg.arg1);
			}
		}
	//};
		public String addZero(int b){
			String val = Integer.toBinaryString(b&0xFF);
			String str="";
			if(val.length()<8){
				for(int i=0;i<8-val.length();i++){
					str+=0;
				}			
				return str+=val;
			}
			return val;
		}
		public void computed(int val){
			String str=addZero(val);
			char[] cr=str.toCharArray();
			int tag;
			for(int i=0;i<cr.length;i++){
				if(cr[i]=='0'){
					tag=0;
					changeState(i,tag);
				}else{		
					changeState(i,1);				
				}
			}
			test=str;
			
		}
	public void changeState(int i,int tag){
		if(tag==0){
			switch (i) {
			case 0:
				//if(tag1==true)
				
				{
				String str1 ="常欢熊";
				if(str1.length()!=0){
					byte[][] data = FontClass.getInstance().setContent(str1,this.getAssets());
					icon = getIcon(data[0]);
//					清空数据
					adapter.clear();
//					初始化数据
					initData(icon);
//					通知数据改变 
					adapter.notifyDataSetChanged();
					//show(1);
					//mediaPlayer.start();
					}
				}
				//tag1=false;
				//tb8.setChecked(true);				
				break;
			default:
				break;
			}
		}/*else{
			switch (i) {
			case 0:
				tag1=true;
				//tb8.setChecked(false);				
				break;
			case 1:
				tag2=true;
				//tb7.setChecked(false);				
				break;
			case 2:
				tag3=true;
				//tb6.setChecked(false);				
				break;
			case 3:
				tag4=true;
				//tb5.setChecked(false);				
				break;
			case 4:
				//tb4.setChecked(false);	
				tag5=true;
				break;
			case 5:
				tag6=true;
				//tb3.setChecked(false);				
				break;
			case 6:
				tag7=true;
				//tb2.setChecked(false);				
				break;
			case 7:
				//tb1.setChecked(false);				
				break;
			default:
				break;
			}
		}
	}
	
*/
	
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.power:
			if (!this.flag) {
				BeepClass.IoctlRelay(BEEP_ON);
				try{
					Thread.sleep(500);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				BeepClass.IoctlRelay(BEEP_OFF);
				
				
				//String str1 ="常欢熊";
				//if(str1.length()!=0){
					//byte[][] data = FontClass.getInstance().setContent(str1,this.getAssets());
					//icon = getIcon(data[0]);
//					清空数据
					//adapter.clear();
//					初始化数据
					//initData(icon);
//					通知数据改变
					//adapter.notifyDataSetChanged();
					//show(1);
					//mediaPlayer.start();
				//}
				
				
				
			}
			break;
		/*case R.id.submit:
			String content = text.getText().toString();
			Air.tent=content;
			flag = false;
			if (content.length() > 4) {
				Log.i("监听", content);
				Toast.makeText(getApplication(), "请输入4位数", Toast.LENGTH_LONG);
			} else {
				this.updateText(Integer.valueOf(content));
			}
			break;*/
		/*case R.id.okoil:
			String content1 = text2.getText().toString();
			Air.oil=content1;
			//flag = false;
			if (content1.length() > 4) {
				Log.i("监听", content1);
				Toast.makeText(getApplication(), "请输入4位数", Toast.LENGTH_LONG);
			} else {
			    textview1.setText(Air.oil);
			}
			break;*/
		case R.id.count:
			if (!this.flag) {
				//MyThread thread = new MyThread();
				this.flag = false;
				LedClass.IoctlLed(0, 1);
			    BeepClass.IoctlRelay(BEEP_ON);
				try{
					Thread.sleep(500);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				BeepClass.IoctlRelay(BEEP_OFF);
				//String start = text.getText().toString();
				String start = String.valueOf(0);
				long total = Integer.valueOf(start).intValue();
				String temp;
				while(total < 80)
				{
					total = total + 10;
					temp = String.valueOf(total);
					this.updateText(Integer.valueOf(temp));
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				LedClass.IoctlLed(0, 0);
				//thread.start();
			}
			break;
		case R.id.stop:
			this.flag = false;
			LedClass.IoctlLed(3, 1);
			//String stop = text.getText().toString();
			String stop = String.valueOf(50);
			long total = Integer.valueOf(stop).intValue();
			String temp;
			while(total > 0)
			{
				total = total - 10;
				temp = String.valueOf(total);
				this.updateText(Integer.valueOf(temp));
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			LedClass.IoctlLed(3, 0);
			//this.updateText(Integer.valueOf(stop));
			
			break;
		case R.id.front:
			this.flag = false;
			LedClass.IoctlLed(1, 0);
			LedClass.IoctlLed(2, 0);
			//String stop = text.getText().toString();
			String front = String.valueOf(80);
			long front1 = Integer.valueOf(front).intValue();
			String front2;
			while(front1 > 40)
			{
				front1 = front1 - 10;
				front2 = String.valueOf(front1);
				this.updateText(Integer.valueOf(front2));
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			while(front1 < 80)
			{
				front1 = front1 + 10;
				front2 = String.valueOf(front1);
				this.updateText(Integer.valueOf(front2));
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			LedClass.IoctlLed(1, 1);
			LedClass.IoctlLed(2, 1);
			//this.updateText(Integer.valueOf(stop));
			
			break;
		case R.id.back:
			this.flag = false;
			LedClass.IoctlLed(1, 0);
			LedClass.IoctlLed(2, 0);
			//String stop = text.getText().toString();
			String back = String.valueOf(80);
			long back1 = Integer.valueOf(back).intValue();
			String back2;
			while(back1 < 120)
			{
				back1 = back1 + 10;
				back2 = String.valueOf(back1);
				this.updateText(Integer.valueOf(back2));
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			while(back1 > 80)
			{
				back1 = back1 - 10;
				back2 = String.valueOf(back1);
				this.updateText(Integer.valueOf(back2));
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			LedClass.IoctlLed(1, 1);
			LedClass.IoctlLed(2, 1);
			//this.updateText(Integer.valueOf(stop));
			
			break;
		case R.id.in:
			this.flag = false;
			LedClass.IoctlLed(0, 1);
			//String stop = text.getText().toString();
			try{
				Thread.sleep(3000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			LedClass.IoctlLed(1, 1);
			LedClass.IoctlLed(2, 1);
			LedClass.IoctlLed(0, 0);
			//this.updateText(Integer.valueOf(stop));
			
			break;
		case R.id.out:
			this.flag = false;
			LedClass.IoctlLed(1, 0);
			LedClass.IoctlLed(2, 0);
			LedClass.IoctlLed(3, 1);
			//String stop = text.getText().toString();
			String out = String.valueOf(80);
			long out1 = Integer.valueOf(out).intValue();
			String out2;
			while(out1 > 50)
			{
				out1 = out1 - 10;
				out2 = String.valueOf(out1);
				this.updateText(Integer.valueOf(out2));
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			LedClass.IoctlLed(3, 0);
			//this.updateText(Integer.valueOf(stop));
			
			break;
		case R.id.lock:
			if(is_lock==0){
				count_btn.setEnabled(false);
				count_front.setEnabled(false);
				count_stop.setEnabled(false);
				count_back.setEnabled(false);
				count_in.setEnabled(false);
				count_out.setEnabled(false);
				count_power.setEnabled(false);
				is_lock=1;
			}
			else{
				count_btn.setEnabled(true);
				count_front.setEnabled(true);
				count_stop.setEnabled(true);
				count_back.setEnabled(true);
				count_in.setEnabled(true);
				count_out.setEnabled(true);
				count_power.setEnabled(true);
				is_lock=0;
			}
		default:
			break;
		}
	}
	
	
	
	public int[] getIcon(byte[] data) {
		int[] arr = new int[256];
		int n = 0;
		for(int k=0; k<16; k++){
	        for(int j=0; j<2; j++){
	        	char[] cs = Integer.toBinaryString(data[k*2+j]&0xFF).toCharArray();
	            for(int i=0; i<8; i++){
	            	int len = cs.length;	
	            	n++;
	            }	
	        }        
	    }
		return arr;
	}
	
	public void updateText(final int arg){
		String str = addZero(String.valueOf(arg));
		this.counterText.setText(str);
	
		//this.textview1.setText(Air.oil);
	
		new Thread(new Runnable() {
			public void run() {
				Seg7Class.Seg7Show(arg);
			}
		}).start();
	} 

	// 不足4位进行补零
	public String addZero(String content) {
		int count = 4 - content.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append("0");
		}
		StringBuffer str = sb.append(content);
		return str.toString();
	}

	// 定义内部线程类
	class MyThread extends Thread {
		public void run() {
			int num = 0;
			int current;
			int aim;
			int temp;
			int oilint=Integer.parseInt(Air.oil);
     		temp=Integer.parseInt(Air.tent);
		    current=temp/100;
		    aim=temp%100;
		    
		    BeepClass.IoctlRelay(BEEP_ON);
			try{
				Thread.sleep(500);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			BeepClass.IoctlRelay(BEEP_OFF);
		    
		    boolean isHot=true;
		    if(current>aim){
		    	isHot=false;//制冷
		    	current++;
		    }
		    else{
		    	isHot=true;//制热
		    	current--;
		    }
		  
			while (flag) {
				try {
					if(isHot)
						current++;
					else
						current--;
					
		    		temp=current*100+aim;
		    		num=temp;
		    		int speed = 1000;
		  		    
		             if(oilint>0&&oilint<=30){
		          	   speed=2000;
		          	   oilint=oilint-1;
		          	   //String str1="油量："+oilint;
		          	   LedClass.IoctlLed(1, 0);
		          	   LedClass.IoctlLed(2, 0);
		          	   LedClass.IoctlLed(3, 1);
		             }
		             if(oilint>30&&oilint<=70){
		          	   speed=1000;
		          	   oilint=oilint-2;
		          	   LedClass.IoctlLed(1, 0);
		          	   LedClass.IoctlLed(2, 1);
		          	   LedClass.IoctlLed(3, 1);
		          	   //String str1="油量："+oilint;
		          	   //textview1.setText(str1);
		          	   
		             }
		             if(oilint>70&&oilint<=100){
		          	   speed=500;
		          	   oilint=oilint-3;
		          	   LedClass.IoctlLed(1, 1);
		          	   LedClass.IoctlLed(2, 1);
		          	   LedClass.IoctlLed(3, 1);
		          	   //String str1="油量："+oilint;
		          	  // textview1.setText(str1);
		             }
		             
		            Air.oil=String.valueOf(oilint);
//					定义消息对象
					Message msg = new Message();
					msg.what = 1;
					msg.arg1 = num;
//					通过Handler发送消息
					uiHandler.sendMessage(msg);
		    		
					if(current==aim||oilint<=0){
						flag=false;
						BeepClass.IoctlRelay(BEEP_ON);
						Thread.sleep(500);
						BeepClass.IoctlRelay(BEEP_OFF);
					}
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event){  
		if (keyCode == KeyEvent.KEYCODE_BACK  ){  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            isExit.setTitle("系统提示");  
            isExit.setMessage("确定要退出吗");  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            isExit.show();    
        }  
		return false;  
	}  
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){  
		public void onClick(DialogInterface dialog, int which){  
			switch (which){  
			case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
				Seg7Class.Exit();
				finish();   
				break;  
			case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
				break;  
			default:  
				break;  
            }  
        }  
    };    
}
