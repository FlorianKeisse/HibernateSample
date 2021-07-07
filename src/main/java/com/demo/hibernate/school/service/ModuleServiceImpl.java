package com.demo.hibernate.school.service;

import com.demo.hibernate.school.dao.ModuleDAO;
import com.demo.hibernate.school.dao.ModuleDAOimp;
import com.demo.hibernate.school.model.Exam;
import com.demo.hibernate.school.model.Module;

import java.util.Set;

public class ModuleServiceImpl implements ModuleService {

    ModuleDAO moduleDAO;

    public ModuleServiceImpl() {
        this.moduleDAO = ModuleDAOimp.getInstance();
    }

    @Override
    public void addModule(Module module) {
        moduleDAO.addModule(module);
    }

    @Override
    public Module getModuleById(long id) {
        return moduleDAO.getModuleById(id);
    }

    @Override
    public Set<Module> getAllModules() {
        return moduleDAO.getAllModules();
    }

    @Override
    public void updateModule(Module module) {
        moduleDAO.updateModule(module);
    }

    @Override
    public void deleteModule(Module module) {
        moduleDAO.deleteModule(module);
    }
}
