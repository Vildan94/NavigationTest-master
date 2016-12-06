package com.example.samir.navigationtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.samir.navigationtest.Modules.Route;
import java.util.ArrayList;

/**
 * Created by Samir on 06-Dec-16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "routesManager";

    // Routes table name
    private static final String TABLE_ROUTES = "routes";

    // Routes table columns names
    private static final String ROUTE_ID = "id";
    private static final String ROUTE_LOCATION_NAME = "location";
    private static final String ROUTE_DESTINATION_NAME = "destination";
    private static final String ROUTE_DEPARTURE_TIME = "departure_time";
    private static final String ROUTE_ARRIVAL_TIME = "arival_time";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ROUTES + "("
                + ROUTE_ID + " INTEGER PRIMARY KEY," + ROUTE_LOCATION_NAME + " TEXT,"
                + ROUTE_DESTINATION_NAME + " TEXT," + ROUTE_DEPARTURE_TIME + " TEXT,"
                + ROUTE_ARRIVAL_TIME + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTES);

        // Create tables again
        onCreate(db);
    }

    public void addRoute(Route route) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ROUTE_LOCATION_NAME, route.startAddress); // Route Location
        values.put(ROUTE_DESTINATION_NAME, route.endAddress); // Route Destination
        values.put(ROUTE_DEPARTURE_TIME,route.depTime);
        values.put(ROUTE_ARRIVAL_TIME,route.arrTime);

        // Inserting Row
        db.insert(TABLE_ROUTES, null, values);
        db.close(); // Closing database connection
    }
    
    public void addRoutes(ArrayList<Route> routes){
        for (Route route: routes) {
            addRoute(route);
        }
    }

    public Route getRoute(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ROUTES, new String[] { ROUTE_ID,
                        ROUTE_LOCATION_NAME, ROUTE_DESTINATION_NAME, ROUTE_DEPARTURE_TIME, ROUTE_ARRIVAL_TIME }, ROUTE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Route route = new Route(cursor.getString(3), cursor.getString(4),cursor.getString(1), cursor.getString(2));
        // return contact
        return route;
    }

    public ArrayList<Route> getAllRoutes() {
        ArrayList<Route> routeList = new ArrayList<Route>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ROUTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Route route = new Route();
                route.depTime = cursor.getString(3);
                route.arrTime = cursor.getString(4);
                route.startAddress = cursor.getString(1);
                route.endAddress = cursor.getString(2);
                routeList.add(route);
            } while (cursor.moveToNext());
        }
        // return contact list
        return routeList;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ROUTES,null,null);
        db.close();
    }
}
