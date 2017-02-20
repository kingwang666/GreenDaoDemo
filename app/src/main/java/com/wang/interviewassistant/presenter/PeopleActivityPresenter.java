package com.wang.interviewassistant.presenter;

import com.wang.interviewassistant.adapter.PeopleAdapter;
import com.wang.interviewassistant.model.People;
import com.wang.interviewassistant.repository.PeopleRepository;

/**
 * Created by wang
 * on 2017/2/14
 */

public class PeopleActivityPresenter extends BasePresenter {

    private IView mView;

    private PeopleRepository mRepository;

    public PeopleActivityPresenter() {
        super();
        mRepository = PeopleRepository.getInstance();
    }

    public void setView(IView view) {
        mView = view;
        super.setView(view);
    }

    public void insert(People people){
        mRepository.insert(people);
        mView.updateSuccess();
    }

    public void update(People people){
        mRepository.update(people);
        mView.updateSuccess();
    }

    public interface IView extends IBaseView{

        void updateSuccess();
    }

}
