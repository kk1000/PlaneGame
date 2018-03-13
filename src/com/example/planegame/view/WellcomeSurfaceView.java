package com.example.planegame.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.planegame.R;
import com.example.planegame.control.activitys.MainActivity;
import com.example.planegame.model.constants.Constants;

public class WellcomeSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private MainActivity activity;
	private Paint paint;
	private Bitmap[] logoBitmaps = new Bitmap[2];
	private Bitmap currentLogo;
	private int currentAlpha = 0;

	private int screenWidth;// ��Ļ���
	private int screenHeight;// ��Ļ�߶�

	public WellcomeSurfaceView(MainActivity activity) {
		super(activity);
		this.activity = activity;
		paint = new Paint();// ��������
		paint.setAntiAlias(true);// �򿪿����
		
		// this.requestFocus();//��ý���
		// this.setFocusableInTouchMode(true);//���ÿɴ���
		getHolder().addCallback(this);// ע��ص��ӿ�

	}

	public void initBitmap() {
		logoBitmaps[0] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.wellcome1);
		logoBitmaps[0] = Bitmap.createScaledBitmap(logoBitmaps[0], screenWidth,
				screenHeight, false);
		logoBitmaps[1] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.wellcome2);
		logoBitmaps[1] = Bitmap.createScaledBitmap(logoBitmaps[1], screenWidth,
				screenHeight, false);
	}

	public void onDrawSelf(Canvas canvas) {
		/*
		 * //���ƺ��������屳�� canvas.save();//���滭��״̬ paint.setXfermode(new
		 * PorterDuffXfermode(Mode.SRC));//�ָ�����
		 * paint.setColor(Color.BLACK);//���û�����ɫ paint.setAlpha(255);
		 * canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		 * paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));//����Ի��ʵ�����
		 * canvas.restore(); //�ָ�����״̬
		 */
		// ����ƽ����ͼ
		if (currentLogo == null)
			return;
		canvas.save();
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
		paint.setAlpha(currentAlpha);
		canvas.drawBitmap(currentLogo, 0, 0, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		canvas.restore();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// �����Ļ���
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		initBitmap();
		// System.out.println("H:"+screenHeight +" W:"+screenWidth);
		new Thread() {
			public void run() {
				for (Bitmap bm : logoBitmaps) {
					currentLogo = bm;
					// ����ͼƬλ��
					// currentX=screenWidth/2-bm.getWidth()/2;
					// currentY=screenHeight/2-bm.getHeight()/2;

					for (int i = 255; i > -10; i = i - 10) {// ��̬����ͼƬ��͸����ֵ�������ػ�
						currentAlpha = i;
						if (currentAlpha < 0) {
							currentAlpha = 0;
						}
						SurfaceHolder myholder = WellcomeSurfaceView.this
								.getHolder();
						Canvas canvas = myholder.lockCanvas();// ��ȡ����
						try {
							synchronized (myholder) {
								onDrawSelf(canvas);// ����
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (canvas != null) {
								myholder.unlockCanvasAndPost(canvas);
							}
						}
						try {
							if (i == 255) {// ������ͼƬ����ȴ�һ��
								Thread.sleep(1000);
							}
							Thread.sleep(50);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// ����������Ϻ�ȥ���˵�����
				activity.sendMessage(Constants.WhatMessage.GOTO_MAIN_MENU_VIEW);
			}
		}.start();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
