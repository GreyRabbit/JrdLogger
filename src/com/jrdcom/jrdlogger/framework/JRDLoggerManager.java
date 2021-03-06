package com.jrdcom.jrdlogger.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.jrdcom.jrdlogger.framework.IJRDLoggerManager;

public class JRDLoggerManager {
	private static final String TAG = "JRDLogger/JRDLoggerManager";
	private Context mContext = null;
	private IJRDLoggerManager mService = null;
	ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.e(TAG, "Bind to service successfully");
			mService = IJRDLoggerManager.Stub.asInterface(service);
		}

		public void onServiceDisconnected(ComponentName paramComponentName) {
			Log.e(TAG, "onServiceDisconnected");
			mService = null;
		}
	};

	public JRDLoggerManager(Context paramContext) {
		this.mContext = paramContext;
		initService();
	}

	private void initService() {
		Log.e(TAG, "initService()");
		Intent localIntent = new Intent(
				"com.jrdcom.jrdlogger.JRDLoggerService");
		this.mContext.startService(localIntent);
		if (this.mContext.bindService(localIntent, this.mServiceConnection, 1))
			return;
		Log.e(TAG, "Fail to bind to JRDLoggerService");
		this.mContext.unbindService(this.mServiceConnection);
		this.mContext.startService(localIntent);
	}

	public boolean clearLog() {

		return false;
	}

	public void free() {
		this.mContext.unbindService(this.mServiceConnection);
	}

	public int getCurrentRunningStage() {

		return 0;
	}

	public boolean runCommand(String paramString) {

		return false;
	}

	public boolean setAutoStart(int paramInt, boolean paramBoolean) {

		return false;
	}

	public boolean setLogSize(int paramInt1, int paramInt2) {

		return false;
	}

	public boolean setTotalLogSize(int paramInt1, int paramInt2) {

		return false;
	}

	public boolean startLog(int paramInt) {
		return startLog(paramInt, null);
	}

	public boolean startLog(int paramInt, String paramString) {
		if (this.mService == null) {
			Log.e(TAG, "Service has not been bind to yet.");
			return false;
		}
		try {
			boolean bool = this.mService.startLog(paramInt, paramString);
			return bool;
		} catch (RemoteException localRemoteException) {
			Log.e(TAG, "Fail to call service API.", localRemoteException);
		}
		return false;
	}

	public boolean stopCommand(String paramString) {

		return false;
	}

	public boolean stopLog(int paramInt) {
		return stopLog(paramInt, null);
	}

	public boolean stopLog(int paramInt, String paramString) {
		if (this.mService == null) {
			Log.e(TAG, "Service has not been bind to yet.");
			return false;
		}
		try {
			boolean bool = this.mService.stopLog(paramInt, paramString);
			return bool;
		} catch (RemoteException localRemoteException) {
			Log.e(TAG, "Fail to call service API.", localRemoteException);
		}
		return false;
	}

}
