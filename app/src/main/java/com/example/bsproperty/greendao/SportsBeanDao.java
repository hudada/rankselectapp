package com.example.bsproperty.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.bsproperty.bean.SportsBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SPORTS_BEAN".
*/
public class SportsBeanDao extends AbstractDao<SportsBean, Long> {

    public static final String TABLENAME = "SPORTS_BEAN";

    /**
     * Properties of entity SportsBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Start = new Property(1, Long.class, "start", false, "START");
        public final static Property End = new Property(2, Long.class, "end", false, "END");
        public final static Property Sid = new Property(3, Long.class, "sid", false, "SID");
        public final static Property Gift = new Property(4, String.class, "gift", false, "GIFT");
        public final static Property Action = new Property(5, int.class, "action", false, "ACTION");
    }


    public SportsBeanDao(DaoConfig config) {
        super(config);
    }
    
    public SportsBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SPORTS_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"START\" INTEGER," + // 1: start
                "\"END\" INTEGER," + // 2: end
                "\"SID\" INTEGER," + // 3: sid
                "\"GIFT\" TEXT," + // 4: gift
                "\"ACTION\" INTEGER NOT NULL );"); // 5: action
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SPORTS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SportsBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long start = entity.getStart();
        if (start != null) {
            stmt.bindLong(2, start);
        }
 
        Long end = entity.getEnd();
        if (end != null) {
            stmt.bindLong(3, end);
        }
 
        Long sid = entity.getSid();
        if (sid != null) {
            stmt.bindLong(4, sid);
        }
 
        String gift = entity.getGift();
        if (gift != null) {
            stmt.bindString(5, gift);
        }
        stmt.bindLong(6, entity.getAction());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SportsBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long start = entity.getStart();
        if (start != null) {
            stmt.bindLong(2, start);
        }
 
        Long end = entity.getEnd();
        if (end != null) {
            stmt.bindLong(3, end);
        }
 
        Long sid = entity.getSid();
        if (sid != null) {
            stmt.bindLong(4, sid);
        }
 
        String gift = entity.getGift();
        if (gift != null) {
            stmt.bindString(5, gift);
        }
        stmt.bindLong(6, entity.getAction());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SportsBean readEntity(Cursor cursor, int offset) {
        SportsBean entity = new SportsBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // start
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // end
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // sid
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // gift
            cursor.getInt(offset + 5) // action
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SportsBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStart(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setEnd(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setSid(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setGift(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAction(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SportsBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SportsBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SportsBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
