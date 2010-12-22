/**
 *
 * Copyright (C) 2010 markw <mark@wolfe.id.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.id.wolfe.fxassetman.server.domain.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import au.id.wolfe.fxassetman.server.domain.PersistableEntity;
import au.id.wolfe.fxassetman.server.domain.company.Company;

/**
 * Represents an employee who will interact with the fxassetman system.
 */
@Entity
@Table(name = "employees")
public class Employee extends PersistableEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "DEPARTMENT", length = 100)
    private String department;

    @Column(name = "EMPLOYEE_STATUS")
    private EmployeeStatus employeeStatus;

    @ManyToOne
    @JoinColumn(name = "SUPERVISOR_ID")
    private Employee supervisor;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Valid
    PersonDetails personDetails;

    public Employee() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Transient
    public String getDisplayName() {

        StringBuilder sb = new StringBuilder();

        sb.append(personDetails.getFirstName());

        if (personDetails.getLastName() != null) {
            sb.append(" ").append(personDetails.getLastName());
        }

        return sb.toString();
    }

}
