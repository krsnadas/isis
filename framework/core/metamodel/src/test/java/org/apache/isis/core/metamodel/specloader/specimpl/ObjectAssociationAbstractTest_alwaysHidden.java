/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.core.metamodel.specloader.specimpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.applib.annotation.When;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.core.commons.authentication.AuthenticationSession;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.consent.InteractionInvocationMethod;
import org.apache.isis.core.metamodel.facetapi.Facet;
import org.apache.isis.core.metamodel.facetapi.FeatureType;
import org.apache.isis.core.metamodel.facets.FacetedMethod;
import org.apache.isis.core.metamodel.facets.hide.HiddenFacet;
import org.apache.isis.core.metamodel.facets.mandatory.MandatoryFacet;
import org.apache.isis.core.metamodel.facets.notpersisted.NotPersistedFacet;
import org.apache.isis.core.metamodel.facets.properties.choices.PropertyChoicesFacet;
import org.apache.isis.core.metamodel.interactions.UsabilityContext;
import org.apache.isis.core.metamodel.interactions.VisibilityContext;
import org.apache.isis.core.metamodel.spec.Instance;
import org.apache.isis.core.metamodel.spec.ObjectSpecification;
import org.apache.isis.core.metamodel.spec.feature.ObjectMemberContext;
import org.apache.isis.core.testsupport.jmock.JUnitRuleMockery2;
import org.apache.isis.core.testsupport.jmock.JUnitRuleMockery2.Mode;

public class ObjectAssociationAbstractTest_alwaysHidden {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);

    private ObjectAssociationAbstract objectAssociation;
    private FacetedMethod facetedMethod;

    @Mock
    private ObjectSpecification objectSpecification;

    @Mock
    private HiddenFacet facet;


    public static class Customer {
        private String firstName;

        public String getFirstName() {
            return firstName;
        }
    }

    @Before
    public void setup() {
        facetedMethod = FacetedMethod.createForProperty(Customer.class, "firstName");
        
        objectAssociation = new ObjectAssociationAbstract(facetedMethod, FeatureType.PROPERTY, objectSpecification, new ObjectMemberContext(null, null, null, null, null)) {

            @Override
            public ObjectAdapter get(final ObjectAdapter fromObject) {
                return null;
            }

            @Override
            public boolean isEmpty(final ObjectAdapter adapter) {
                return false;
            }

            @Override
            public ObjectAdapter[] getChoices(final ObjectAdapter object) {
                return null;
            }

            @Override
            public ObjectAdapter getDefault(final ObjectAdapter adapter) {
                return null;
            }

            @Override
            public void toDefault(final ObjectAdapter target) {
            }

            @Override
            public UsabilityContext<?> createUsableInteractionContext(final AuthenticationSession session, final InteractionInvocationMethod invocationMethod, final ObjectAdapter target, Where where) {
                return null;
            }

            @Override
            public VisibilityContext<?> createVisibleInteractionContext(final AuthenticationSession session, final InteractionInvocationMethod invocationMethod, final ObjectAdapter targetObjectAdapter, Where where) {
                return null;
            }

            @Override
            public String debugData() {
                return null;
            }

            @Override
            public Instance getInstance(final ObjectAdapter adapter) {
                return null;
            }

            @Override
            public boolean containsDoOpFacet(final Class<? extends Facet> facetType) {
                // TODO Auto-generated method stub
                return false;
            }
        };

        context.checking(new Expectations() {
            {
                allowing(facet).facetType();
                will(returnValue(HiddenFacet.class));
                allowing(facet).when();
                will(returnValue(When.ALWAYS));
            }
        });

    }


    @Test
    public void alwaysHidden_forHiddenAlwaysEverywhere() throws Exception {
        context.checking(new Expectations() {
            {
                allowing(facet).where();
                will(returnValue(Where.ANYWHERE));
            }
        });
        facetedMethod.addFacet(facet);
        assertTrue(objectAssociation.isAlwaysHidden());
    }


    @Test
    public void alwaysHidden_forHiddenAlwaysObjectForm() throws Exception {
        context.checking(new Expectations() {
            {
                allowing(facet).where();
                will(returnValue(Where.OBJECT_FORMS));
            }
        });
        facetedMethod.addFacet(facet);
        assertTrue(objectAssociation.isAlwaysHidden());
    }
    
    @Test
    public void alwaysHidden_forHiddenAlwaysNowhere() throws Exception {
        context.checking(new Expectations() {
            {
                allowing(facet).where();
                will(returnValue(Where.NOWHERE));
            }
        });
        facetedMethod.addFacet(facet);
        assertFalse(objectAssociation.isAlwaysHidden());
    }

}