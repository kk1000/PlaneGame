package com.example.planegame.model.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planegame.view.GameSurfaceView;

public class Explosion {
	private GameSurfaceView gameView;
	private Bitmap[] explosions;//��ը��ͼ
	private float nowX;//���ű�ը��ͼx����
	private float nowY;
	private int animIndex;//��ը�����±�����
	
	public Explosion(GameSurfaceView gameView,Bitmap[] explosions,float x,float y)
	{
		this.gameView=gameView;
		this.explosions = explosions;
		this.nowX = x;
		this.nowY = y;
	}
	//���Ʊ����ķ���
	public void drawSelf(Canvas canvas,Paint paint)
	{
		canvas.drawBitmap(explosions[animIndex], nowX, nowY,paint);
		if(animIndex < explosions.length-1)//���û�в�����
		{
			animIndex++;
		}
		else//�����ը�������
		{
			gameView.allExplosion.remove(this);//���б���ɾ���˱�ը
		}
	}
}
