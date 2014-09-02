package com.topcsa.test_android;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageView mIv;
	private TextView mTv1, mTv2, mTv3;
	private ViewPager mVp;
	private List<View> pagers;
	int currentIndex = 0;
	private int width = 0; // 屏幕宽度
	private int line_width = 0; // line宽度
	private int offset = 0; // 偏移量
	int i;
	// 初始化操作
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Init();
		

	}

	


	private void Init() {
		mIv = (ImageView) findViewById(R.id.line);
		mTv1 = (TextView) findViewById(R.id.textview1);
		mTv2 = (TextView) findViewById(R.id.textview2);
		mTv3 = (TextView) findViewById(R.id.textview3);
		mTv1.setOnClickListener(new TvonClick(1));
		mTv2.setOnClickListener(new TvonClick(2));
		mTv3.setOnClickListener(new TvonClick(3));
		InitViewpager();
		InitmIvPosition();

	}

	private void InitViewpager() {
		mVp = (ViewPager) findViewById(R.id.ViewPager);
		mVp.setCurrentItem(0);
		mVp.setOnPageChangeListener(new MyOnClickPagerChange());
		LayoutInflater inflater = getLayoutInflater();
		pagers = new ArrayList<View>();
		pagers.add(inflater.inflate(R.layout.part1, null));
		pagers.add(inflater.inflate(R.layout.part2, null));
		pagers.add(inflater.inflate(R.layout.part3, null));
		mVp.setAdapter(new ViewPagerAdapter(pagers));

	}

	class MyOnClickPagerChange implements
			android.support.v4.view.ViewPager.OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			TranslateAnimation animation = null;
			switch (arg0) {
			case 0:
				if (currentIndex == 1) {
					animation = new TranslateAnimation(2 * offset + line_width,
							0, 0, 0);
					System.out.println("1-------------------->0");
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(4 * offset + 2
							* line_width, 0, 0, 0);
					System.out.println("2-------------------->0");
				}
				break;
			case 1:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 2 * offset
							+ line_width, 0, 0);
					System.out.println("0-------------------->1");
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(4 * offset + 2
							* line_width, 2 * offset + line_width, 0, 0);
					System.out.println("2-------------------->1");
				}
				break;
			case 2:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(0, 4 * offset + 2
							* line_width, 0, 0);
					System.out.println("0-------------------->2");
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(2 * offset + line_width,
							4 * offset + 2 * line_width, 0, 0);
					System.out.println("1-------------------->2");
				}
				break;
			}
	     
			
			currentIndex = arg0;
			animation.setFillAfter(true);// 保留在动画结束的位置
			animation.setDuration(300);
			mIv.startAnimation(animation);

		}

	}

	/**
	 * 点击TextView
	 * 
	 * @author af74776
	 * 
	 */
	class TvonClick implements View.OnClickListener {
		int mark;

		public TvonClick(int index) {
			mark = index - 1;
		}

		@Override
		public void onClick(View v) {

			mVp.setCurrentItem(mark);
		}

	}

	private void InitmIvPosition() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽的像素
		line_width = BitmapFactory.decodeResource(getResources(),
				R.drawable.line).getWidth();

		offset = ((width / 3) - line_width) / 2;// 计算出偏移量

		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);// 将matrix平移到(offset,0)
		mIv.setImageMatrix(matrix);

	}
}
