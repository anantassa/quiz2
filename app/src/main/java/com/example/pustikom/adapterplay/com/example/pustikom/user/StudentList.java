package com.example.pustikom.adapterplay.com.example.pustikom.user;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by pustikom on 04/11/16.
 */

public class StudentList implements List<Student> {
    private ArrayList<Student> studentList;
    public StudentList(){
        studentList=new ArrayList<>();
    }

    @Override
    public int size() {
        return studentList.size();
    }

    @Override
    public boolean contains(Object o) {
        //loop through all of the content to check if the o
        return studentList.contains(o);
    }

    @NonNull
    @Override
    public Iterator<Student> iterator() {
        return studentList.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return studentList.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] a) {
        return studentList.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return studentList.remove(o);
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return studentList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Student> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Student> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        if(studentList.size()==0)
            return true;
        return false;
    }

    /**
     *
     * @param student
     * @return
     */
    @Override
    public boolean add(Student student) {
        int id = this.size();
        studentList.add(student);
        return true;
    }

    /**
     *
     */
    @Override
    public void clear(){
        studentList.clear();
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public Student get(int index) {
        return studentList.get(index);
    }

    /**
     *
     * @param index
     * @param element
     * @return previous student before replaced
     */
    @Override
    public Student set(int index, Student element) {
        element.setId(index);
        return studentList.set(index, element);
    }

    /**
     *
     * Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     * @param index
     * @param element
     */
    @Override
    public void add(int index, Student element) {
        element.setId(index);
        studentList.add(index, element);
        recomputeId(index+1,size());
    }

    private void recomputeId(int startIndex, int finalIndex){
        for (int i = startIndex; i < finalIndex; i++) {
            Student student = get(i);
            student.setId(i);
        }
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public Student remove(int index) {
        Student student = studentList.remove(index);
        recomputeId(index,size());
        return student;
    }

    @Override
    public int indexOf(Object o) {
        return studentList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return studentList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Student> listIterator() {
        return studentList.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<Student> listIterator(int index) {
        return studentList.listIterator(index);
    }

    @NonNull
    @Override
    public List<Student> subList(int fromIndex, int toIndex) {
        return studentList.subList(fromIndex,toIndex);
    }
}