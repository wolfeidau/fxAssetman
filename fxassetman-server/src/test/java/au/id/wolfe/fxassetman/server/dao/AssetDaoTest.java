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

import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import au.id.wolfe.fxassetman.server.domain.asset.Asset;
import au.id.wolfe.fxassetman.server.domain.fixtures.AssetFixture;

/**
 * Test which exercises the AssetDao.
 */
@Transactional 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/data-context.xml"})
public class AssetDaoTest {
    
    private final Logger log = LoggerFactory.getLogger(AssetDaoTest.class);
    
    @Autowired
    AssetDao assetDao;
    
    @Test
    public void testCreate(){
        log.info("testCreate");
        assetDao.save(AssetFixture.createValidAsset());
    }
    
    @Test
    public void testFindAll(){
        Set<Asset> assets = assetDao.findAll();
        
        assertNotNull(assets);
    }

    
    //NoResultException
    @Test(expected=NoResultException.class)
    public void testFindByIdNoResultExceptionExpected(){
        assetDao.findById(Long.MAX_VALUE);
    }
}
