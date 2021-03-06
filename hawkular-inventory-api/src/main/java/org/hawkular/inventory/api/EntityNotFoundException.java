/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hawkular.inventory.api;

import java.util.Arrays;

import org.hawkular.inventory.api.filters.Filter;
import org.hawkular.inventory.api.model.Entity;

/**
 * @author Lukas Krejci
 * @since 0.0.1
 */
public final class EntityNotFoundException extends InventoryException {

    private final Class<? extends Entity<?, ?>> entityType;
    private final Filter[][] filters;
    private final String msg;

    public EntityNotFoundException(Class<? extends Entity<?, ?>> entityClass, Filter[][] filters) {
        this.entityType = entityClass;
        this.filters = filters;
        this.msg = null;
    }

    public EntityNotFoundException(Filter[][] filters) {
        this(null, filters);
    }

    public EntityNotFoundException(String msg) {
        this.msg = msg;
        this.entityType = null;
        this.filters = null;
    }

    /**
     * @return the type of the entity that was not found.
     */
    public Class<? extends Entity<?, ?>> getEntityType() {
        return entityType;
    }

    /**
     * @return the considered paths to the entity that was not found.
     */
    public Filter[][] getFilters() {
        return filters;
    }

    @Override
    public String getMessage() {
        if (null != msg) {
            return msg;
        }
        return (entityType == null ? "Nothing" : ("No " + entityType.getSimpleName())) +
                " found on any of the following paths: " + Arrays.deepToString(filters);
    }
}
