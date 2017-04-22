package com.demonhunts.throughit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.demonhunts.throughit.helpers.PlayServices;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements PlayServices {
	private GameHelper gameHelper;
	private final static int requestCode = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ThroughIt(this), config);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(true);
		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInFailed() {
			}

			@Override
			public void onSignInSucceeded() {
			}
		};
		gameHelper.setup(gameHelperListener);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}


	@Override
	public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.signOut();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame() {
		String str = "https://play.google.com/store/apps/details?id=com.demonhunts.throughit";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void unlockAchievement(int score) {
		if (isSignedIn()) {
			if (score == 5) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_5_points));
			} else if (score == 10) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_10_points));
			} else if (score == 20) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_20_points));
			} else if (score == 30) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_30_points));
			} else if (score == 40) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_40_points));
			} else if (score == 50) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_50_points));
			} else if (score == 75) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_75_points));
			} else if (score == 100) {
				Games.Achievements.unlock(gameHelper.getApiClient(),
						getString(R.string.achievement_score_100_points));
			}
		}
	}

	@Override
	public void submitScore(int highScore) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_highscore), highScore);
		}
	}

	@Override
	public void showAchievement() {
		if (isSignedIn() == true) {
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), requestCode);
		} else {
			signIn();
		}
	}

	@Override
	public void showScore() {
		if (isSignedIn() == true) {
			startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()), requestCode);
		} else {
			signIn();
		}
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}

}