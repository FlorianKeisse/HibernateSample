package com.demo.hibernate.school.service;

import com.demo.hibernate.school.model.Exam;
import com.demo.hibernate.school.model.Module;

import java.util.Set;

public interface ModuleService {

    void addModule(Module module);

    Module getModuleById(long id);

    Set<Module> getAllModules();

    void updateModule(Module module);

    void deleteModule(Module module);
    
}
