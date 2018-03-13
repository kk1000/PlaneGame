package com.example.planegame.control.dialogs;

import java.lang.reflect.Field;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planegame.R;
import com.example.planegame.control.activitys.MainActivity;
import com.example.planegame.model.constants.Constants;
import com.example.planegame.model.game.MyButton;

public class MyDialog extends Dialog {
	private MainActivity activity;
	private int theme;

	public MyDialog(MainActivity activity, int theme) {
		super(activity);
		this.activity = activity;
		this.theme = theme;
	}

	public MyDialog(MainActivity activity, int planeNumber, MyButton[] button) {
		super(activity);
		this.activity = activity;
		createBuyPlaneDialog(planeNumber, button);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (theme) {
		case Constants.DialogType.INPUT_USERNAME:
			createWellcomeDialog();
			break;
		case Constants.DialogType.WIN:
			createWinDialog();
			break;
		case Constants.DialogType.FAIL:
			createFailDialog();
			break;
		}
	}

	public void createWellcomeDialog() {
		setContentView(R.layout.dialog_input_name);
		Button ok = (Button) this.findViewById(R.id.btn_ok_input_name);
		final EditText et = (EditText) this
				.findViewById(R.id.editText_input_name);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = et.getText().toString();
				if (name == null || "".equals(name)) {
					Toast.makeText(activity, "���ֲ���Ϊ�գ�����", Toast.LENGTH_SHORT)
							.show();
					et.requestFocus();
				} else if (name.length() > 6) {
					Toast.makeText(activity, "����̫��������", Toast.LENGTH_SHORT)
							.show();
					et.requestFocus();
				} else {
					activity.userInfo.setName(name);
					activity.dbManager.insertUserInfo(activity.userInfo);
					activity.dbManager.insertRecords(1, 0, 0);
					//activity.dbManager.insertRecords(2, 0, 0);
					//activity.dbManager.insertRecords(3, 0, 0);
					//activity.dbManager.insertRecords(4, 0, 0);
					dismiss();
				}
			}
		});
	}

	public void createBuyPlaneDialog(final int planeNumber,
			final MyButton[] button) {
		this.setTitle("����ɻ���");
		setContentView(R.layout.dialog_buy_plane);
		final ImageView iv = (ImageView) this.findViewById(R.id.iv_buy_plane);
		Field field;
		try {
			field = R.drawable.class.getDeclaredField("shop_plane"
					+ planeNumber);
			int resourceId = Integer.parseInt(field.get(null).toString());
			Bitmap b = BitmapFactory.decodeResource(activity.getResources(),
					resourceId);
			iv.setImageBitmap(b);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createWinDialog() {
		setContentView(R.layout.dialog_win);
		int record = 0;
		TextView tv = (TextView) this.findViewById(R.id.textView_win_dialog);
		tv.setText("");
		if (activity.currentPattem == 0) {
			this.setTitle("������ɣ�");
			record = activity.dbManager.queryRecords(activity.currentMap)
					.get("general");
			if (activity.currentScore > record) {
				tv.append("�¼�¼!\n");
				activity.dbManager.updateRecordsGaneral(
						activity.currentMap, activity.currentScore);
			}
			tv.append("��ý��:" + activity.currentScore + "\n");
			activity.userInfo.setMoney(activity.userInfo.getMoney()
					+ activity.currentScore);
			if (activity.currentMap  == activity.userInfo.getMap()) {
				tv.append("����������\n");
				tv.append("��һ���ѽ�����\n");
				activity.userInfo
						.setMilitary(activity.userInfo.getMilitary() + 1);
				activity.userInfo.setMap(activity.userInfo.getMap() + 1);
				activity.dbManager.insertRecords(
						activity.userInfo.getMap(), 0, 0);
			} else {
				tv.append("��ý��:" + activity.currentScore + "\n");
				activity.userInfo.setMoney(activity.userInfo.getMoney()
						+ activity.currentScore);
			}
		} else {
			this.setTitle("��ս�ɹ���");
			System.out.println("��ս�ɹ���");
			tv.append("�Õr:" + activity.time + "\n");
			tv.append("��ý��:" + activity.currentScore + "\n");
			activity.userInfo.setMoney(activity.userInfo.getMoney()
					+ activity.currentScore);
			if (activity.currentMap  == activity.userInfo.getMap()) {
				tv.append("����������\n");
				activity.userInfo.setMilitary(activity.userInfo.getMilitary() + 1);
			}
			
			record = activity.dbManager.queryRecords(activity.currentMap)
					.get("challenge");
			//System.out.println("record"+record);
			
			if (record == 0||activity.time < record) {
				tv.append("�¼�¼!\n");
				activity.dbManager.updateRecordsChallenge(
						activity.currentMap, activity.time);
			}
		}
		activity.dbManager.updateUserInfo(activity.userInfo);

	}

	public void createFailDialog() {
		setContentView(R.layout.dialog_fail);
		TextView tv = (TextView) this.findViewById(R.id.textView_fail_dialog);
		tv.setText("");
		if (activity.currentPattem == 0) {
			this.setTitle("����ʧ�ܣ�");
			tv.append("��ý��:" + activity.currentScore + "\n");
			activity.userInfo.setMoney(activity.userInfo.getMoney()
					+ activity.currentScore);

		} else {
			this.setTitle("��սʧ�ܣ�");
		}
		activity.dbManager.updateUserInfo(activity.userInfo);

	}

}
