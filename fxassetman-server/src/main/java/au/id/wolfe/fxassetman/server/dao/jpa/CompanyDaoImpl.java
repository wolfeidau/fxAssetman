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

package au.id.wolfe.fxassetman.server.dao.jpa;

import org.springframework.stereotype.Repository;

import au.id.wolfe.fxassetman.server.dao.CompanyDao;
import au.id.wolfe.fxassetman.server.domain.company.Company;

/**
 * The implementation of CompanyDao using JPA.
 */
@Repository
public class CompanyDaoImpl extends GenericDaoImpl<Company, Long> implements
		CompanyDao {

	public CompanyDaoImpl() {
		super(Company.class);
	}
}
