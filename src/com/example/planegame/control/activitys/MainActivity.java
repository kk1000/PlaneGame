package com.example.planegame.control.activitys;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.planegame.R;
import com.example.planegame.control.dialogs.MyDialog;
import com.example.planegame.model.UserInfo;
import com.example.planegame.model.constants.Constants;
import com.example.planegame.model.db.DBManager;
import com.example.planegame.model.game.GameSoundPool;
import com.example.planegame.view.GameSurfaceView;
import com.example.planegame.view.HelpSurfaceView;
import com.example.planegame.view.InfoSurfaceView;
import com.example.planegame.view.MainMenuSurfaceView;
import com.example.planegame.view.RecordSurfaceView;
import com.example.planegame.view.SelectMapSurfaceView;
import com.example.planegame.view.SelectPlaneSurfaceView;
import com.example.planegame.view.ShopSurfaceView;
import com.example.planegame.view.SoundControlSurfaceView;
import com.example.planegame.view.WellcomeSurfaceView;

public class MainActivity extends Activity {
	private int currentView;// ��ǵ�ǰ����
	private WellcomeSurfaceView wellcomeView;// ��ʾ��ӭ��������
	private MainMenuSurfaceView mainMenuView;// ���˵�����
	private GameSurfaceView gameView;// ��Ϸ����
	private ShopSurfaceView shopView;
	private RecordSurfaceView recordView;// ��¼����
	private SelectPlaneSurfaceView selectPlaneView;
	private SelectMapSurfaceView selectMapView;
	private HelpSurfaceView helpView;// ��������
	private SoundControlSurfaceView soundControlView;// ��Ч���ƽ���
	private InfoSurfaceView infoView;// ������Ϣ����

	public boolean backGroundMusicFlag = false;// ���������Ƿ����ı�־
	public boolean soundFlag = true;// ��Ч�Ƿ����ı�־
	public GameSoundPool gameSound;
	public MediaPlayer mMediaPlayer;
	// mMediaPlayer.setLooping(true);

	public int currentPlaneNumber;// ѡ��ķɻ����
	public int currentMap;// ѡ��ĵ�ͼ
	public int currentPattem;// ��ǰģʽ0��ͨģʽ��1��սģʽ
	public int currentScore;
	public int time;

	public DBManager dbManager;// ���ݿ⹤��
	public UserInfo userInfo;// ������Ϣ

	private Handler myHandler = new Handler() {// �������SurfaceView���͵���Ϣ
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.WhatMessage.GOTO_MAIN_MENU_VIEW:
				gotoMainMenuView();
				break;
			case Constants.WhatMessage.GOTO_GAME_VIEW:
				gotoGameView();
				break;
			case Constants.WhatMessage.GOTO_SELECT_PLANE_VIEW:
				gotoSelectPlaneView();
				break;
			case Constants.WhatMessage.GOTO_SELECT_MAP_VIEW:
				gotoSelectMapView();
				break;
			case Constants.WhatMessage.GOTO_SOUND_CONTORL_VIEW:
				gotoSoundControlView();
				break;
			case Constants.WhatMessage.GOTO_WIN_VIEW:
				gotoWinView();
				break;
			case Constants.WhatMessage.GOTO_FAIL_VIEW:
				// new MyDialog(MainActivity.this,2).show();
				gotoFailView();
				break;
			case Constants.WhatMessage.GOTO_WELLCOME_VIEW:
				gotoWellcomeView();
				break;
			case Constants.WhatMessage.GOTO_HELP_VIEW:
				gotoHelpView();
				break;
			case Constants.WhatMessage.GOTO_INFO_VIEW:
				gotoInfoView();
				break;
			case Constants.WhatMessage.GOTO_SHOP_VIEW:
				gotoShopView();
				break;
			case Constants.WhatMessage.GOTO_RECORD_VIEW:
				gotoRecordView();
				break;
			}
		}
	};

	// ��Handler������Ϣ�ķ���
	public void sendMessage(int what) {
		Message msg1 = myHandler.obtainMessage(what);
		myHandler.sendMessage(msg1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����ȫ��
		dbManager = new DBManager(this);
		gameSound = new GameSoundPool(this);
		gameSound.initSound();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		gotoWellcomeView();
		dbManager.deleteUserInfo();
		dbManager.deleteOwnPlanes();
		dbManager.deleteRecords();
		// ���ű�������
		mMediaPlayer = MediaPlayer.create(this, R.raw.back);
		mMediaPlayer.setLooping(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void gotoSelectMapView() {
		if (selectMapView == null) {
			selectMapView = new SelectMapSurfaceView(this);
		}
		this.setContentView(selectMapView);
		this.currentView = Constants.WhatMessage.GOTO_SELECT_MAP_VIEW;
	}

	private void gotoSelectPlaneView() {
		if (selectPlaneView == null) {
			selectPlaneView = new SelectPlaneSurfaceView(this);
		}
		this.setContentView(selectPlaneView);
		this.currentView = Constants.WhatMessage.GOTO_SELECT_PLANE_VIEW;
	}

	private void gotoShopView() {
		if (shopView == null) {
			shopView = new ShopSurfaceView(this);
		}
		this.setContentView(shopView);
		this.currentView = Constants.WhatMessage.GOTO_SHOP_VIEW;
	}

	private void gotoRecordView() {
		if (recordView == null) {
			recordView = new RecordSurfaceView(this);
		}
		this.setContentView(recordView);
		this.currentView = Constants.WhatMessage.GOTO_RECORD_VIEW;
	}

	private void gotoWellcomeView() {
		System.out.println("gotoWellcomeView");
		if (wellcomeView == null) {
			wellcomeView = new WellcomeSurfaceView(this);
		}
		this.setContentView(wellcomeView);
		currentView = Constants.WhatMessage.GOTO_WELLCOME_VIEW;
	}

	private void gotoInfoView() {
		if (infoView == null) {
			infoView = new InfoSurfaceView(this);
		}
		this.setContentView(infoView);
		this.currentView = Constants.WhatMessage.GOTO_INFO_VIEW;
	}

	private void gotoHelpView() {
		if (helpView == null) {
			helpView = new HelpSurfaceView(this);
		}
		this.setContentView(helpView);
		currentView = Constants.WhatMessage.GOTO_HELP_VIEW;
	}

	private void gotoFailView() {
		final MyDialog myDialog = new MyDialog(MainActivity.this,
				Constants.DialogType.FAIL);
		myDialog.show();
		Button btn_ok = (Button) myDialog.findViewById(R.id.btn_ok_fail);
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendMessage(Constants.WhatMessage.GOTO_MAIN_MENU_VIEW);
				myDialog.dismiss();
			}
		});
		// this.setContentView(winView);
		currentView = Constants.WhatMessage.GOTO_FAIL_VIEW;
	}

	private void gotoWinView() {
		final MyDialog myDialog = new MyDialog(MainActivity.this,
				Constants.DialogType.WIN);
		myDialog.show();
		Button btn_ok = (Button) myDialog.findViewById(R.id.btn_ok_win);
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendMessage(Constants.WhatMessage.GOTO_MAIN_MENU_VIEW);
				myDialog.dismiss();
			}
		});
		// this.setContentView(winView);
		currentView = Constants.WhatMessage.GOTO_WIN_VIEW;
	}

	private void gotoSoundControlView() {
		if (soundControlView == null) {
			soundControlView = new SoundControlSurfaceView(this);
		}
		this.setContentView(soundControlView);
		currentView = Constants.WhatMessage.GOTO_SOUND_CONTORL_VIEW;
	}

	private void gotoGameView() {
		if (gameView == null) {
			gameView = new GameSurfaceView(this);
		}
		this.setContentView(gameView);
		currentView = Constants.WhatMessage.GOTO_GAME_VIEW;
	}

	// ȥ���˵�����ķ���
	private void gotoMainMenuView() {
		System.out.println("gotoMainMenuView");
		if (mainMenuView == null) {
			mainMenuView = new MainMenuSurfaceView(this);
		}
		this.setContentView(mainMenuView);
		currentView = Constants.WhatMessage.GOTO_MAIN_MENU_VIEW;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent e) {
		switch (currentView) {
		case Constants.WhatMessage.GOTO_WELLCOME_VIEW:
			break;
		case Constants.WhatMessage.GOTO_MAIN_MENU_VIEW:
			System.exit(0);
			break;
		case Constants.WhatMessage.GOTO_GAME_VIEW:
		case Constants.WhatMessage.GOTO_SOUND_CONTORL_VIEW:
		case Constants.WhatMessage.GOTO_WIN_VIEW:
		case Constants.WhatMessage.GOTO_FAIL_VIEW:
		case Constants.WhatMessage.GOTO_INFO_VIEW:
		case Constants.WhatMessage.GOTO_RECORD_VIEW:
		case Constants.WhatMessage.GOTO_HELP_VIEW:
		case Constants.WhatMessage.GOTO_SHOP_VIEW:
		case Constants.WhatMessage.GOTO_SELECT_PLANE_VIEW:
		case Constants.WhatMessage.GOTO_SELECT_MAP_VIEW:
			gotoMainMenuView();
		}
		return true;
	}

	public boolean isBackGroundMusicFlag() {
		return backGroundMusicFlag;
	}

	public void setBackGroundMusicFlag(boolean backGroundMusicFlag) {
		this.backGroundMusicFlag = backGroundMusicFlag;
	}

	public boolean isSoundFlag() {
		return soundFlag;
	}

	public void setSoundFlag(boolean soundFlag) {
		this.soundFlag = soundFlag;
	}

	public int getCurrentView() {
		return currentView;
	}

	public void setCurrentView(int currentView) {
		this.currentView = currentView;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dbManager = null;
	}

}
