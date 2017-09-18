package generic.realm.sample.com.newrealmsamplejava;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by sandeep.singh on 9/15/2017.
 */

public class RealmController{

    private static RealmController instance;
    private final Realm realm;


    private static final String TAG = "RealmController";

    public RealmController() {
        realm = Realm.getDefaultInstance();
    }

    public RealmController(Application application) {
        Realm.init(application.getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        if (instance == null) {
            instance = new RealmController();
        }
        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    public void refresh() {
        realm.refresh();
    }

    @UiThread
    public  <E extends RealmObject>  void insertOrUpdate(E dbObject){

        try {
            if (!realm.isInTransaction())
                realm.beginTransaction();

            realm.insertOrUpdate(dbObject);
            realm.commitTransaction();
        } catch (Exception e) {
            Log.e(TAG, "Failed to add download: " + e.getMessage());
        }
    }

    @UiThread
    public  <E extends RealmObject>  RealmObject getDBObjectById(Class<E> eClass,String id){
        try{
          return  realm.where(eClass).equalTo("id",id).findFirst();

        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
        return null;
    }

    @UiThread
    public  <E extends RealmObject> RealmResults<E> getAllRecords(Class<E> eClass){
        try{
            return  realm.where(eClass).findAll();

        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
        return null;
    }

    @UiThread
    public  <E extends RealmObject>  void deleteRowById(Class<E> eClass,String id){

        try {
            if (!realm.isInTransaction())
                realm.beginTransaction();

            realm.where(eClass).equalTo("id",id).findFirst().deleteFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
            Log.e(TAG, "Failed to add download: " + e.getMessage());
        }
    }


}
