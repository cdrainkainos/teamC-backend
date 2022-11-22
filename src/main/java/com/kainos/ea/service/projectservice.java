package com.kainos.ea.service;

import com.kainos.ea.dao.ProjectDao;

import com.kainos.ea.model.employeerequest;
import com.kainos.ea.model.projectemployees;
import com.kainos.ea.model.projectrequest;
import com.kainos.ea.model.salesemployeerequest;

public class projectservice {

        public com.kainos.ea.dao.ProjectDao ProjectDao;

        public projectservice(ProjectDao emp){
            this.ProjectDao = emp;
        }

        public int AddEmployeeToProject (projectemployees req){
            return ProjectDao.assignEmployeeToProject(req);
        }

        public int AddProject (projectrequest req){
        return ProjectDao.createProject(req);
    }

        public String assignedemployees(){
            return ProjectDao.assignedemployees();
        }



}
