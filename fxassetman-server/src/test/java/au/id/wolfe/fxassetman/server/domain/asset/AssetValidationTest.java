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

package au.id.wolfe.fxassetman.server.domain.asset;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.id.wolfe.fxassetman.server.domain.fixtures.AssetFixture;

/**
 * Verify the asset domain object works as intended.
 */
public class AssetValidationTest {

    private static Validator validator;

    private static final Logger log = LoggerFactory
            .getLogger(AssetValidationTest.class);

    @BeforeClass
    public static void setUp() {
        log.info("setUp");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void validateShouldFailWithNullAssetTag() {
        Asset asset = AssetFixture.createValidAsset();

        asset.setAssetTag(null);

        Set<ConstraintViolation<Asset>> constraintViolations;

        constraintViolations = validator.validate(asset);

        assertEquals(1, constraintViolations.size());

        assertEquals("may not be null", constraintViolations.iterator().next()
                .getMessage());
    }

    @Test
    public void validateShouldFailWithNullSerialNo() {
        Asset asset = AssetFixture.createValidAsset();

        asset.setSerialNo(null);

        Set<ConstraintViolation<Asset>> constraintViolations;

        constraintViolations = validator.validate(asset);

        assertEquals(1, constraintViolations.size());

        assertEquals("may not be null", constraintViolations.iterator().next()
                .getMessage());
    }

    @Test
    public void validateShouldFailWithNullAssetType() {
        Asset asset = AssetFixture.createValidAsset();

        asset.setAssetType(null);

        Set<ConstraintViolation<Asset>> constraintViolations;

        constraintViolations = validator.validate(asset);

        assertEquals(1, constraintViolations.size());

        assertEquals("may not be null", constraintViolations.iterator().next()
                .getMessage());
    }

    @Test
    public void validateShouldFailWithNullSupplier() {
        Asset asset = AssetFixture.createValidAsset();

        asset.setSupplier(null);

        Set<ConstraintViolation<Asset>> constraintViolations;

        constraintViolations = validator.validate(asset);

        assertEquals(1, constraintViolations.size());

        assertEquals("may not be null", constraintViolations.iterator().next()
                .getMessage());
    }

    @Test
    public void validateShouldFailWithNullClient() {
        Asset asset = AssetFixture.createValidAsset();

        asset.setClient(null);

        Set<ConstraintViolation<Asset>> constraintViolations;

        constraintViolations = validator.validate(asset);

        assertEquals(1, constraintViolations.size());

        assertEquals("may not be null", constraintViolations.iterator().next()
                .getMessage());
    }

    @Test
    public void validateShouldFailWithNullModelName() {
        Asset asset = AssetFixture.createValidAsset();

        asset.setModelName(null);

        Set<ConstraintViolation<Asset>> constraintViolations;

        constraintViolations = validator.validate(asset);

        assertEquals(1, constraintViolations.size());

        assertEquals("may not be null", constraintViolations.iterator().next()
                .getMessage());
    }
}
