package org.example.Model;

public class Teacher extends User{
    public Teacher(){super.departamentId = 2;}
    public void TeacherMain(Teacher teacher){
        System.out.println("Привет Учитель");
    }
}
