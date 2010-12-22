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

package au.id.wolfe.fxassetman.server.domain.company;

import au.id.wolfe.fxassetman.server.domain.PersistableEntity;
import au.id.wolfe.fxassetman.server.domain.person.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents a company who has one of the following roles in the fxassetman system.
 * <ul>
 * <li>Owns assets stored in the system.</li>
 * <li>Supplies products which are stored as assets in the system.</li>
 * <li>Manufacturers products which are stored as assets in the system.</li>
 * </ul>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "companies")
public class Company extends PersistableEntity<Long> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    String name;

    @OneToMany(mappedBy = "company")
    Set<Employee> employees;

    public Company() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[] {
				"deleted", "dateUpdated", "dateAdded" });
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
