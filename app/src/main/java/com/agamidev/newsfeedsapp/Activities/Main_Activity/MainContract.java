package com.agamidev.newsfeedsapp.Activities.Main_Activity;

import com.agamidev.newsfeedsapp.Models.NewsModel;

import java.util.ArrayList;

public interface MainContract {
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<NewsModel> newsArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface GetNewsInteractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<NewsModel> newsArrayList);
            void onFailure(Throwable t);
        }

        void getNewsArrayList(OnFinishedListener onFinishedListener);
    }
}
