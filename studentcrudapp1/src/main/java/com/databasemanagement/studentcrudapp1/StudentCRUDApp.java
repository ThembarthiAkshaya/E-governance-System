package com.databasemanagement.studentcrudapp1;
import java.io.*;
import com.databasemanagement.studentcrudapp.controller.StudentController;
import com.databasemanagement.studentcrudapp1.utils.DatabaseConnectionUtils;
public class StudentCRUDApp 
{
    public static void main( String[] args )throws IOException
    {
    	/*----- Creating object of Student controller ------*/
    	StudentController studentController =new StudentController();
    	studentController.studentOperation();
    	/*---- Closing connection -----*/
    	DatabaseConnectionUtils.closeConnection();
    }
}