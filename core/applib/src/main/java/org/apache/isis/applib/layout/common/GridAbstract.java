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
package org.apache.isis.applib.layout.common;

import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.collect.Maps;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.layout.bootstrap3.BS3Grid;
import org.apache.isis.applib.services.layout.GridService;

/**
 * All top-level page layout classes should implement this interface.
 *
 * <p>
 *     It is used by the {@link GridService} as a common based type for any layouts read in from XML.
 * </p>
 */
@XmlTransient // ignore this class
public abstract class GridAbstract implements Grid {

    private boolean normalized;

    @Programmatic
    @XmlTransient
    public boolean isNormalized() {
        return normalized;
    }

    @Programmatic
    public void setNormalized(final boolean normalized) {
        this.normalized = normalized;
    }


    /**
     * Convenience for subclasses.
     */
    protected void traverseActions(
            final ActionLayoutDataOwner actionLayoutDataOwner,
            final GridAbstract.Visitor visitor) {
        final List<ActionLayoutData> actionLayoutDatas = actionLayoutDataOwner.getActions();
        if(actionLayoutDatas == null) {
            return;
        }
        for (final ActionLayoutData actionLayoutData : actionLayoutDatas) {
            actionLayoutData.setOwner(actionLayoutDataOwner);
            visitor.visit(actionLayoutData);
        }
    }


    /**
     * Convenience for subclasses.
     */
    protected void traverseFieldSets(final FieldSetOwner fieldSetOwner, final GridAbstract.Visitor visitor) {
        final List<FieldSet> fieldSets = fieldSetOwner.getFieldSets();
        for (FieldSet fieldSet : fieldSets) {
            fieldSet.setOwner(fieldSetOwner);
            visitor.visit(fieldSet);
            traverseActions(fieldSet, visitor);
            final List<PropertyLayoutData> properties = fieldSet.getProperties();
            for (final PropertyLayoutData property : properties) {
                property.setOwner(fieldSet);
                visitor.visit(property);
                traverseActions(property, visitor);
            }
        }
    }


    /**
     * Convenience for subclasses.
     */
    protected void traverseCollections(
            final CollectionLayoutDataOwner owner, final GridAbstract.Visitor visitor) {
        final List<CollectionLayoutData> collections = owner.getCollections();
        for (CollectionLayoutData collection : collections) {
            collection.setOwner(owner);
            visitor.visit(collection);
            traverseActions(collection, visitor);
        }
    }


    @Programmatic
    @XmlTransient
    public LinkedHashMap<String, PropertyLayoutData> getAllPropertiesById() {
        final LinkedHashMap<String, PropertyLayoutData> propertiesById = Maps.newLinkedHashMap();
        visit(new BS3Grid.VisitorAdapter() {
            public void visit(final PropertyLayoutData propertyLayoutData) {
                propertiesById.put(propertyLayoutData.getId(), propertyLayoutData);
            }
        });
        return propertiesById;
    }


    @Programmatic
    @XmlTransient
    public LinkedHashMap<String, CollectionLayoutData> getAllCollectionsById() {
        final LinkedHashMap<String, CollectionLayoutData> collectionsById = Maps.newLinkedHashMap();

        visit(new BS3Grid.VisitorAdapter() {
            @Override
            public void visit(final CollectionLayoutData collectionLayoutData) {
                collectionsById.put(collectionLayoutData.getId(), collectionLayoutData);
            }
        });
        return collectionsById;
    }


    @Programmatic
    @XmlTransient
    public LinkedHashMap<String, ActionLayoutData> getAllActionsById() {
        final LinkedHashMap<String, ActionLayoutData> actionsById = Maps.newLinkedHashMap();

        visit(new BS3Grid.VisitorAdapter() {
            @Override
            public void visit(final ActionLayoutData actionLayoutData) {
                actionsById.put(actionLayoutData.getId(), actionLayoutData);
            }
        });
        return actionsById;
    }


    @Programmatic
    @XmlTransient
    public LinkedHashMap<String, FieldSet> getAllFieldSetsByName() {
        final LinkedHashMap<String, FieldSet> fieldSetsByName = Maps.newLinkedHashMap();

        visit(new BS3Grid.VisitorAdapter() {
            @Override
            public void visit(final FieldSet fieldSet) {
                fieldSetsByName.put(fieldSet.getName(), fieldSet);
            }
        });
        return fieldSetsByName;
    }


}
