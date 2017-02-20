package com.wang.interviewassistant.presenter;

import com.wang.interviewassistant.adapter.PeopleAdapter;
import com.wang.interviewassistant.model.People;
import com.wang.interviewassistant.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang
 * on 2017/2/14
 */

public class PeopleFragmentPresenter extends BasePresenter{

    private IView mView;

    private PeopleRepository mRepository;

    public PeopleFragmentPresenter() {
        super();
        mRepository = PeopleRepository.getInstance();
    }

    public void setView(IView view) {
        mView = view;
        super.setView(view);
    }

    public void getNoInterview(){
        mItemArray.clear();
        mItemArray.addAll(mRepository.getList(PeopleAdapter.TYPE_NO_INTERVIEW));
        mView.notifyDataSetChanged();
        mView.stopLoading();
    }

    public void getGood(){
        mItemArray.clear();
        mItemArray.addAll(mRepository.getList(PeopleAdapter.TYPE_GOOD));
        mView.notifyDataSetChanged();
        mView.stopLoading();
    }

    public void getBad(){
        mItemArray.clear();
        mItemArray.addAll(mRepository.getList(PeopleAdapter.TYPE_BAD));
        mView.notifyDataSetChanged();
        mView.stopLoading();
    }

    public void getTypeList(int type){
        mItemArray.clear();
        mItemArray.addAll(mRepository.getList(type));
        mView.notifyDataSetChanged();
        mView.stopLoading();
    }

    public void insert(People people){
        mRepository.insert(people);
    }

    public void update(People people, int position){
        mRepository.update(people);
        mItemArray.remove(position);
    }

    public void delete(People people, int position){
        mRepository.delete(people);
        mItemArray.remove(position);
    }

    public interface IView extends IBaseView{

    }
}
