package com.example.planegame.view;

import java.lang.reflect.Field;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.planegame.R;
import com.example.planegame.control.activitys.MainActivity;
import com.example.planegame.model.constants.Constants;
import com.example.planegame.model.game.MyButton;

public class SelectPlaneSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private MainActivity activity;
	private Bitmap bgBitmap;
	private Bitmap[] planeBitmaps;
	private Bitmap currentPlaneBitmap;

	private Paint paint;

	private MyButton btnAttack;
	private MyButton btnLast;
	private MyButton btnNext;

	private int screenWidth;
	private int screenHeight;
	private int currentPage;

	public SelectPlaneSurfaceView(MainActivity activity) {
		super(activity);
		this.activity = activity;
		paint = new Paint();
		paint.setAntiAlias(true);
		requestFocus();
		setFocusableInTouchMode(true);
		getHolder().addCallback(this);
	}

	public void initBitmap() {
		bgBitmap = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.bg_select);
		bgBitmap = Bitmap.createScaledBitmap(bgBitmap, screenWidth,
				screenHeight, false);

		planeBitmaps = new Bitmap[activity.userInfo.getOwnPlanes().size()];
		int i = 0;
		for (int planeNumber : activity.userInfo.getOwnPlanes()) {
			Field field;
			try {
				field = R.drawable.class.getField("player" + planeNumber);
				int resourceId = Integer.parseInt(field.get(null).toString());
				planeBitmaps[i++] = BitmapFactory.decodeResource(
						activity.getResources(), resourceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * for (int i = 0; i < Constants.GAME_ALLPLANE_SUM; i++) { try { Field
		 * field = R.drawable.class.getField("player" + i); int resourceId =
		 * Integer.parseInt(field.get(null).toString()); planeBitmaps[i] =
		 * BitmapFactory.decodeResource( activity.getResources(), resourceId); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
		currentPlaneBitmap = planeBitmaps[0];
	}

	public void initButton() {
		Bitmap btn_lastBitmap = BitmapFactory.decodeResource(
				activity.getResources(), R.drawable.btn_last);
		btnLast = new MyButton(btn_lastBitmap, screenWidth / 4
				- btn_lastBitmap.getWidth() / 2, screenHeight * 9 / 10
				- btn_lastBitmap.getHeight() / 2);

		Bitmap btn_attackBitmap = BitmapFactory.decodeResource(
				activity.getResources(), R.drawable.btn_attack);
		btnAttack = new MyButton(btn_attackBitmap, screenWidth / 2
				- btn_attackBitmap.getWidth() / 2, screenHeight * 9 / 10
				- btn_attackBitmap.getHeight() / 2);

		Bitmap btn_nextBitmap = BitmapFactory.decodeResource(
				activity.getResources(), R.drawable.btn_next);
		btnNext = new MyButton(btn_nextBitmap, screenWidth * 3 / 4
				- btn_lastBitmap.getWidth() / 2, screenHeight * 9 / 10
				- btn_nextBitmap.getHeight() / 2);
	}

	private void onDrawMyButton(Canvas canvas, Paint paint) {
		btnLast.drawSelf(canvas, paint);
		btnAttack.drawSelf(canvas, paint);
		btnNext.drawSelf(canvas, paint);
	}

	public void onDrawPlaneAttribute(Canvas canvas, int planeNumber) {
		String currentPlaneName = "";
		Bitmap currentPlaneAttributeBitmap = null;
		switch (planeNumber) {
		case Constants.PlaneNumber.PLANE_NUMBER_0:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_0;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute0);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_1:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_1;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute1);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_2:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_2;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute2);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_3:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_3;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute3);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_4:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_4;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute4);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_5:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_5;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute5);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_6:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_6;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute6);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_7:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_7;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute7);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_8:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_8;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute8);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_9:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_9;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute9);
			break;
		case Constants.PlaneNumber.PLANE_NUMBER_10:
			currentPlaneName = Constants.PlaneName.PLANE_NUMBER_10;
			currentPlaneAttributeBitmap = BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.text_plane_attribute10);
			break;
		}
		paint.setColor(Color.YELLOW);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(24);
		canvas.drawText(currentPlaneName, screenWidth / 2,
				screenHeight / 10 + 5, paint);
		canvas.drawBitmap(currentPlaneAttributeBitmap, screenWidth / 2
				- currentPlaneAttributeBitmap.getWidth() / 2,
				screenHeight * 7 / 24, paint);
	}

	public void onDrawSelf(Canvas canvas) {
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
		canvas.drawBitmap(currentPlaneBitmap, screenWidth / 2
				- currentPlaneBitmap.getWidth() / 2, screenHeight / 2
				- currentPlaneBitmap.getHeight() / 2, paint);
		onDrawPlaneAttribute(canvas,
				activity.userInfo.getOwnPlanes().get(currentPage));
		onDrawMyButton(canvas, paint);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenWidth = getWidth();
		screenHeight = getHeight();
		currentPage = 0;

		initBitmap();
		initButton();

		new Thread() {
			@Override
			public void run() {
				SurfaceHolder myholder = SelectPlaneSurfaceView.this
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
					Thread.sleep(100);// ˯��ָ��������
				} catch (Exception e) {
					e.printStackTrace();// ��ӡ��ջ��Ϣ
				}

			}

		}.start();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (btnLast.isActionOnButton(x, y)) {
				if(activity.soundFlag){
					activity.gameSound.playSound(Constants.SoundType.BUTTON, 0);
				}
				
				if (currentPage == 0) {
					Toast.makeText(activity, "���Ѿ��ǵ�һҳ��", Toast.LENGTH_SHORT)
							.show();
				} else {
					currentPage--;
					currentPlaneBitmap = planeBitmaps[currentPage];
					SurfaceHolder myholder = SelectPlaneSurfaceView.this
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
				}
			} else if (btnNext.isActionOnButton(x, y)) {
				if(activity.soundFlag){
					activity.gameSound.playSound(Constants.SoundType.BUTTON, 0);
				}
				
				if (currentPage == activity.userInfo.getOwnPlanes().size() - 1) {
					Toast.makeText(activity, "���Ѿ������һҳ��", Toast.LENGTH_SHORT)
							.show();
				} else {
					currentPage++;
					currentPlaneBitmap = planeBitmaps[currentPage];
					SurfaceHolder myholder = SelectPlaneSurfaceView.this
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
				}
			} else if (btnAttack.isActionOnButton(x, y)) {
				if(activity.soundFlag){
					activity.gameSound.playSound(Constants.SoundType.BUTTON, 0);
				}
				
				System.out.println("planeNumber"
						+ activity.userInfo.getOwnPlanes().get(currentPage));
				activity.currentPlaneNumber = activity.userInfo.getOwnPlanes().get(currentPage);
				activity.sendMessage(Constants.WhatMessage.GOTO_SELECT_MAP_VIEW);
			}
		}
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
