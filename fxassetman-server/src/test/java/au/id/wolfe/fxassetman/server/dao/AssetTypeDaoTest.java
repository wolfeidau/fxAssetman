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

package au.id.wolfe.fxassetman.server.dao;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import au.id.wolfe.fxassetman.server.domain.asset.AssetType;
import au.id.wolfe.fxassetman.server.domain.fixtures.AssetTypeFixture;

/**
 * Test which exercises the AssetTypeDao.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/data-context.xml" })
public class AssetTypeDaoTest {

	private final Logger log = LoggerFactory.getLogger(AssetTypeDaoTest.class);

	@Autowired
	private AssetTypeDao assetTypeDao;

	public void setAssetTypeDao(AssetTypeDao assetTypeDao) {
		this.assetTypeDao = assetTypeDao;
	}

	@Test
	public void createAssetType() {
		log.info("Creating assetType");

		log.info("Creating assetType");

		AssetType assetTypeOne = AssetTypeFixture.createValidAssetType();

		assetTypeDao.save(assetTypeOne);

		log.info("Created - " + assetTypeOne.getId());

	}

	@Test(expected = PersistenceException.class)
	public void verifyNameIsUnique(){
		
		AssetType assetTypeOne = AssetTypeFixture.createValidAssetType();
		
		assetTypeDao.save(assetTypeOne);
		
		AssetType assetTypeTwo = AssetTypeFixture.createValidAssetType();
		
		assetTypeDao.save(assetTypeTwo);
		
	}
	
	@Test(expected = PersistenceException.class)
	public void createAssetTypeDuplicateFail() {
		log.info("Creating assetType");

		AssetType assetTypeOne = AssetTypeFixture.createValidAssetType();

		assetTypeDao.save(assetTypeOne);

		log.info("Created - " + assetTypeOne.getId());

		AssetType assetTypeTwo = AssetTypeFixture.createValidAssetType();

		assetTypeTwo.setId(assetTypeOne.getId());
		assetTypeTwo.setVersion(22); // This MUST be set to trigger failure.

		assetTypeDao.save(assetTypeTwo);

		log.info("Created - " + assetTypeTwo.getId());
	}

	// NoResultException
	@Test(expected = NoResultException.class)
	public void testFindByIdNoResultExceptionExpected() {
		assetTypeDao.findById(Long.MAX_VALUE);
	}

}
