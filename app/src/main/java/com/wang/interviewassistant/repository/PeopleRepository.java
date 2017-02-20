package com.wang.interviewassistant.repository;

import android.content.Context;

import com.wang.interviewassistant.greendao.PeopleDao;
import com.wang.interviewassistant.model.People;

import java.util.List;

/**
 * Created by wang
 * on 2017/2/14
 */

public class PeopleRepository extends BaseRepository {

    private volatile static PeopleRepository sInstance;

    public static PeopleRepository getInstance(){
        PeopleRepository repository = sInstance;
        if (repository == null) {
            synchronized (PeopleRepository.class) {
                repository = sInstance;
                if (repository == null) {
                    repository = new PeopleRepository();
                    sInstance = repository;
                }
            }
        }
        return repository;
    }

    public List<People> getList(int type) {
         return mPeopleDao.queryBuilder()
                .where(PeopleDao.Properties.Type.eq(type))
                .orderAsc(PeopleDao.Properties.InterviewTime)
                .list();
    }

    public void update(People people) {
        mPeopleDao.update(people);
    }

    public void delete(People people){
        mPeopleDao.delete(people);
    }

    public void deletList(List<People> peoples){
        mPeopleDao.deleteInTx(peoples);
    }

    public void insert(People people){
        mPeopleDao.insert(people);
    }
}
