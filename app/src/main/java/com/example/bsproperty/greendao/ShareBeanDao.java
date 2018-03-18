package com.example.bsproperty.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.bsproperty.bean.ShareBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SHARE_BEAN".
*/
public class ShareBeanDao extends AbstractDao<ShareBean, Long> {

    public static final String TABLENAME = "SHARE_BEAN";

    /**
     * Properties of entity ShareBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Uid = new Property(1, Long.class, "uid", false, "UID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Time = new Property(3, Long.class, "time", false, "TIME");
        public final static Property Score = new Property(4, Double.class, "score", false, "SCORE");
        public final static Property Score2 = new Property(5, Double.class, "score2", false, "SCORE2");
        public final static Property Info = new Property(6, String.class, "info", false, "INFO");
        public final static Property Action = new Property(7, int.class, "action", false, "ACTION");
    }


    public ShareBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ShareBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SHARE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"UID\" INTEGER," + // 1: uid
                "\"TITLE\" TEXT," + // 2: title
                "\"TIME\" INTEGER," + // 3: time
                "\"SCORE\" REAL," + // 4: score
                "\"SCORE2\" REAL," + // 5: score2
                "\"INFO\" TEXT," + // 6: info
                "\"ACTION\" INTEGER NOT NULL );"); // 7: action
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SHARE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ShareBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long uid = entity.getUid();
        if (uid != null) {
            stmt.bindLong(2, uid);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(4, time);
        }
 
        Double score = entity.getScore();
        if (score != null) {
            stmt.bindDouble(5, score);
        }
 
        Double score2 = entity.getScore2();
        if (score2 != null) {
            stmt.bindDouble(6, score2);
        }
 
        String info = entity.getInfo();
        if (info != null) {
            stmt.bindString(7, info);
        }
        stmt.bindLong(8, entity.getAction());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ShareBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long uid = entity.getUid();
        if (uid != null) {
            stmt.bindLong(2, uid);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(4, time);
        }
 
        Double score = entity.getScore();
        if (score != null) {
            stmt.bindDouble(5, score);
        }
 
        Double score2 = entity.getScore2();
        if (score2 != null) {
            stmt.bindDouble(6, score2);
        }
 
        String info = entity.getInfo();
        if (info != null) {
            stmt.bindString(7, info);
        }
        stmt.bindLong(8, entity.getAction());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ShareBean readEntity(Cursor cursor, int offset) {
        ShareBean entity = new ShareBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // uid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // time
            cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4), // score
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // score2
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // info
            cursor.getInt(offset + 7) // action
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ShareBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUid(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTime(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setScore(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
        entity.setScore2(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setInfo(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAction(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ShareBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ShareBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ShareBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
