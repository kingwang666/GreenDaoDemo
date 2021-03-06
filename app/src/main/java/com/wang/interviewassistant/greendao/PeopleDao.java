package com.wang.interviewassistant.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wang.interviewassistant.model.People;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PEOPLE".
*/
public class PeopleDao extends AbstractDao<People, Long> {

    public static final String TABLENAME = "PEOPLE";

    /**
     * Properties of entity People.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type = new Property(1, int.class, "type", false, "TYPE");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Phone = new Property(3, String.class, "phone", false, "PHONE");
        public final static Property InterviewTime = new Property(4, String.class, "interviewTime", false, "INTERVIEW_TIME");
        public final static Property CallbackTime = new Property(5, String.class, "callbackTime", false, "CALLBACK_TIME");
        public final static Property Technology = new Property(6, int.class, "technology", false, "TECHNOLOGY");
        public final static Property Study = new Property(7, int.class, "study", false, "STUDY");
        public final static Property Fit = new Property(8, int.class, "fit", false, "FIT");
        public final static Property Desc = new Property(9, String.class, "desc", false, "DESC");
    }


    public PeopleDao(DaoConfig config) {
        super(config);
    }
    
    public PeopleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PEOPLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TYPE\" INTEGER NOT NULL ," + // 1: type
                "\"NAME\" TEXT," + // 2: name
                "\"PHONE\" TEXT," + // 3: phone
                "\"INTERVIEW_TIME\" TEXT," + // 4: interviewTime
                "\"CALLBACK_TIME\" TEXT," + // 5: callbackTime
                "\"TECHNOLOGY\" INTEGER NOT NULL ," + // 6: technology
                "\"STUDY\" INTEGER NOT NULL ," + // 7: study
                "\"FIT\" INTEGER NOT NULL ," + // 8: fit
                "\"DESC\" TEXT);"); // 9: desc
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PEOPLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, People entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
 
        String interviewTime = entity.getInterviewTime();
        if (interviewTime != null) {
            stmt.bindString(5, interviewTime);
        }
 
        String callbackTime = entity.getCallbackTime();
        if (callbackTime != null) {
            stmt.bindString(6, callbackTime);
        }
        stmt.bindLong(7, entity.getTechnology());
        stmt.bindLong(8, entity.getStudy());
        stmt.bindLong(9, entity.getFit());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(10, desc);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, People entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
 
        String interviewTime = entity.getInterviewTime();
        if (interviewTime != null) {
            stmt.bindString(5, interviewTime);
        }
 
        String callbackTime = entity.getCallbackTime();
        if (callbackTime != null) {
            stmt.bindString(6, callbackTime);
        }
        stmt.bindLong(7, entity.getTechnology());
        stmt.bindLong(8, entity.getStudy());
        stmt.bindLong(9, entity.getFit());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(10, desc);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public People readEntity(Cursor cursor, int offset) {
        People entity = new People( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // phone
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // interviewTime
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // callbackTime
            cursor.getInt(offset + 6), // technology
            cursor.getInt(offset + 7), // study
            cursor.getInt(offset + 8), // fit
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // desc
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, People entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.getInt(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setInterviewTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCallbackTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTechnology(cursor.getInt(offset + 6));
        entity.setStudy(cursor.getInt(offset + 7));
        entity.setFit(cursor.getInt(offset + 8));
        entity.setDesc(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(People entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(People entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(People entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
