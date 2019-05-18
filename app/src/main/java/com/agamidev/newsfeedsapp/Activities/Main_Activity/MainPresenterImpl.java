package com.agamidev.newsfeedsapp.Activities.Main_Activity;

import com.agamidev.newsfeedsapp.Models.NewsModel;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetNewsInteractor.OnFinishedListener {


    private MainContract.MainView mainView;
    private MainContract.GetNewsInteractor getNoticeInteractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNewsInteractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeInteractor = getNoticeIntractor;
    }
    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getNoticeInteractor.getNewsArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        getNoticeInteractor.getNewsArrayList(this);
    }


    @Override
    public void onFinished(ArrayList<NewsModel> newsArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(newsArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }

}
