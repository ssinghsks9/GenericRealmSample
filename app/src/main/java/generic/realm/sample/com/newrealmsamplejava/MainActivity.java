package generic.realm.sample.com.newrealmsamplejava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import generic.realm.sample.com.newrealmsamplejava.models.BookModel;
import generic.realm.sample.com.newrealmsamplejava.models.StudentModel;

public class MainActivity extends AppCompatActivity {

    int idVal=0;
    RealmController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller= RealmController.with(this);
    }

    public void addBooks(View view){
        idVal++;
        BookModel bookModel=new BookModel();
        bookModel.id="id :"+idVal;
        bookModel.name="Name :"+idVal;
        bookModel.auther="Auther :"+idVal;
        controller.insertOrUpdate(bookModel);
    }
    public void addStudent(View view){
        idVal++;
        StudentModel studentModel=new StudentModel();
        studentModel.id="id :"+idVal;
        studentModel.name="Name :"+idVal;
        studentModel.address="Address :"+idVal;
        controller.insertOrUpdate(studentModel);
    }

    public void listAllBooks(View view){
        List<BookModel> books= controller.getAllRecords(BookModel.class);
        for (BookModel bookModel:books){
            System.out.println(bookModel.id+" : "+bookModel.name +" : "+bookModel.auther);
        }
    }


    public void listAllStudents(View view){
        List<StudentModel> studentModels= controller.getAllRecords(StudentModel.class);
        for (StudentModel studentModel:studentModels){
            System.out.println(studentModel.id+" : "+studentModel.name +" : "+studentModel.address);
        }
    }
}
