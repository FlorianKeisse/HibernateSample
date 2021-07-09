package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.model.Exam;
import com.demo.hibernate.school.model.Module;

import java.util.Set;

public interface ModuleDAO {

    void addModule(Module module);

    Module getModuleById(long id);

    Set<Module> getAllModules();
    
    Set<Module> getAllModulesByCourse(long courseId);

    void updateModule(Module module);

    void deleteModule(Module module);
    
}
