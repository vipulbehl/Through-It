package com.demonhunts.throughit.helpers;

public interface PlayServices
{
    public void signIn();
    public void signOut();
    public void rateGame();
    public void unlockAchievement(int score);
    public void submitScore(int highScore);
    public void showAchievement();
    public void showScore();
    public boolean isSignedIn();
}