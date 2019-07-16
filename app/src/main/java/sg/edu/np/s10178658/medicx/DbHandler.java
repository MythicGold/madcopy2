package sg.edu.np.s10178658.medicx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountDB.db";
    public static final String ACCOUNTS = "Accounts";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";

    public DbHandler(Context c,
                     String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Create Table Accounts (UserName (TEXT), Password (TEXT))
        //Only can create one table at a time
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS +
                " (" + COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addAccount(Account a)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, a.getPassword());
        values.put(COLUMN_USERNAME, a.getUsername());

        SQLiteDatabase db= this.getWritableDatabase();
        db.insert(ACCOUNTS, null, values);
        db.close();
    }

    public Account findAccount(String username)
    {
        String query = "SELECT * FROM " + ACCOUNTS + " WHERE "
                + COLUMN_USERNAME + " =\"" + username + "\"";

        Account a = new Account();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            a = new Account();
            a.setUsername(cursor.getString(0));
            a.setPassword(cursor.getString(1));
            cursor.close();
        }
        else {
            a = new Account();
            a.setUsername(null);
            a.setPassword(null);
        }

        db.close();
        return a;
    }
}
