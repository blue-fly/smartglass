package com.capacity.smartglass;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public abstract class BaseSetupActivity extends Activity {
	
	
	public abstract void showPref();
	public abstract void showNext();
	private GestureDetector detector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		detector=new GestureDetector(this, new SimpleOnGestureListener(){

			/**
			 * 手势滑动
			 */
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {

					//如果上下滑动幅度太大就忽略
					if(Math.abs(e2.getRawY()-e1.getRawY())>100){
						Toast.makeText(getApplicationContext(), "滑动无效", 0).show();		
						return true;
					}
					//如果滑动速度太慢就忽略
					if(Math.abs(velocityX)<200){
						Toast.makeText(getApplicationContext(), "滑动无效", 0).show();		
						return true;
					}
					
					
					if (e2.getRawX() - e1.getRawX() > 200) {
						//显示上一个页面
						showPref();
						return true;
					}
					if(e1.getRawX()-e2.getRawX()>200){
						//显示下一个页面
						showNext();
						return true;
					}
				return super.onFling(e1, e2, velocityX, velocityY);
			}
			
		});
	}
	
	/**
	 * 使用手势识别器
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
}
